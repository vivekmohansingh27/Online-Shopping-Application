import navbar from "./navbar.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();

function addProduct(val) {
  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/carts/viewProducts?cartId=${val}`,
    requestOptions
  )
    .then((response) => response.json())
    .then((result) => {
      console.log(result);
      render(result);
    })
    .catch((error) => console.log("error", error));
}

function render(result) {
  let sessionid = JSON.parse(sessionStorage.getItem("customer")) || null;
  console.log(sessionid);
  const productList = document.getElementById("productAppend");
  productList.innerHTML = null;
  let i = 1;
  for (let product of result) {
    //console.log(product)

    const row = document.createElement("productAppend");

    row.innerHTML = `
    <div class="card mb-3" style="max-width: 540px">
    <div class="row g-0">
      <div class="col-md-4">
        <img src="${product.image}" class="img-fluid rounded-start" alt="..." />
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h5 class="card-title">${product.productName}</h5>
          <p class="card-text">${product.price}</p>
          <p class="card-text"></p>
          <p>${product.description}</p>
          <p class="card-text">${product.manufacturer}</p>
          <p class="card-text">${product.quantity}</p>
          <p class="card-text">${product.category.catName}</p>
          <button type="button" class="btn btn-outline-dark">-</button>
          <button type="button" class="btn btn-outline-dark">+</button>
    
          <button type="button" class="btn btn-outline-danger">Remove</button>
        </div>
      </div>
    </div>
    
  </div> `;
    productList.appendChild(row);
  }

  const buttons = document.querySelectorAll(".cartBtn");
  buttons.forEach((btn) => {
    btn.onclick = () => {
      let productId = event.target.dataset.productId;
      let sessionKey = sessionid.userId;
      console.log(sessionKey);
      addTocart(productId, sessionKey);
    };
    // console.log(btn)
  });
}

function emptyCart() {
  let sessionid = JSON.parse(sessionStorage.getItem("customer")) || null;
  console.log(sessionid);
  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/carts/emptyCart?cartId=${sessionid.userId}`,
    requestOptions
  )
    // .then((response) => response.json())
    .then((result) => {
      console.log(result);
      if (result.status == 201) {
        const productList = document.getElementById("productAppend");
        productList.innerHTML = null;
      }
    })
    .catch((error) => console.log("error", error));
}

function placeOrder() {
  let sessionid = JSON.parse(sessionStorage.getItem("customer")) || null;
  console.log(sessionid);
  var requestOptions = {
    method: "POST",
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/orders?customerId=${sessionid.userId}`,
    requestOptions
  )
    // .then(response => response.text())
    .then((result) => {
      console.log(result);
      if (result.status == 200) {
        const productList = document.getElementById("productAppend");
        productList.innerHTML = null;
      }
    })
    .catch((error) => console.log("error", error));
}

window.addEventListener("load", () => {
  let sessionid = JSON.parse(sessionStorage.getItem("customer_details"));
  console.log(sessionid);
  if (sessionid.customerId != 0) addProduct(sessionid.customerId);

  document.getElementById("empty").onclick = () => {
    emptyCart();
  };
  document.getElementById("plcorder").onclick = () => {
    console.log("place order");
    placeOrder();
  };
});
