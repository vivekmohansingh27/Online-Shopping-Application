import navbar from "./navbar.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();

function byCategoryName(val) {
  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(`http://localhost:8080/category/name?category=${val}`, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      console.log(result);

      render(result);
    })
    .catch((error) => console.log("error", error));
}

getProducts();

function getProducts() {
  let sessionid = JSON.parse(sessionStorage.getItem("customer")) || null;
  // console.log(sessionid);

  fetch(`http://localhost:8080/products`)
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

    const row = document.createElement("div");

    row.innerHTML = `
    <div class="card" style="width: 18rem">
    <img src="${product.image}" alt="" />
    <div class="card-body">
      <h5 class="card-title">${product.productName}</h5>
      <p class="card-text">Price - ${product.price}/-</p>
      <p class="card-text">Discription - ${product.description}</p>
      <p class="card-text">Brand - ${product.manufacturer}</p>
      <p class="card-text">Category - ${product.category.catName}</p>
  
      <button href="#" class="btn btn-primary cartBtn" data-product-id="${
        product.productId
      }" data-session-key="${
      sessionid.userId
    }"  id="cart${i++}">Add to Cart</button>
      <button href="#" class="btn btn-primary" id="buy">Buy Now</button>
    </div>
  </div>
                `;
    productList.appendChild(row);
  }

  const buttons = document.querySelectorAll(".cartBtn");
  buttons.forEach((btn) => {
    btn.onclick = () => {
      let productId = event.target.dataset.productId;
      let sessionKey = sessionid.userId;
      console.log(sessionKey)
      addTocart(productId, sessionKey);
    };
    // console.log(btn)
  });
}
console.log(parseInt("10")+1)
function addTocart(prod, cartid) {
  console.log(prod,cartid);
  // var requestOptions = {
  //   method: "POST",
  //   redirect: "follow",
  // };

  // fetch(`http://localhost:8080/carts/addProduct?cartId=${cartid}&productId=${prod}`, requestOptions)

  //   .then(result => {
  //     console.log(result)

  //     if(result.status == 200){

  //       // result.json().then((data)=>{
  //       //   console.log(data)
  //       //   console.log(data.message)
  //       // })
  //     }else
  //     result.json().then((data)=>{
  //       console.log(data)
  //       // console.log(data.message)
  //     })
  //   } )
  //   .catch(error => console.log('error', error));
 
  fetch(`http://localhost:8080/carts/addProduct?cartId=${cartid}&productId=${prod}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      // you can include a request body here, if needed
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      // do something with the response data
      console.log(data);
    })
    .catch((error) => {
      // handle errors
      console.error(error);
    });
}

window.addEventListener("load", () => {
  let selectBtn = document.getElementById("option");
  selectBtn.onchange = () => {
    let val = document.getElementById("option").value;
    console.log(val);
    if (val.length != 0) byCategoryName(val);
    else getProducts();
  };
  document.getElementById("cart").onclick = () => {
    f1();
  };
});
