import navbar from "./adminNav.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();

// ///////////////////////////// ADD PRODUCT ////////////////////////////////

function addProduct() {
  let sessionid = JSON.parse(localStorage.getItem("admin")) || null;

  console.log(sessionid.uuid);

  const productName = document.getElementById("product_name").value;
  const productImage = document.getElementById("product_image").value;
  const productPrice = document.getElementById("product_price").value;
  const productDesc = document.getElementById("product_desc").value;
  const productManufac = document.getElementById("product_manufac").value;
  const productQuantity = document.getElementById("product_quantity").value;
  const productCategory = document.getElementById("validationCustom04").value;
  const productCategoryId = document.getElementById("validationCustom05").value;

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    productId: 0,
    productName: productName,
    image: productImage,
    price: productPrice,
    description: productDesc,
    manufacturer: productManufac,
    quantity: productQuantity,
    category: {
      catId: productCategoryId,
      catName: productCategory,
    },
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/admin/products?sessionKey=${sessionid.uuid}`,
    requestOptions
  )
    .then((response) => response.text())
    .then((response) => {
      if (response.status >= 200 && response.status <= 299) {
        response.json().then((data) => {
          sessionStorage.setItem("customer", JSON.stringify(data));
        });
      }

      console.log(response);
    })
    .catch((error) => console.log("error", error));
}

//////////////////////////// ====  UPDATE PRODUCT   ===////////////////////////////////////////

function updateProduct() {
  let sessionid = JSON.parse(localStorage.getItem("admin")) || null;

  console.log(sessionid.uuid);

  const productName = document.getElementById("update_product_name").value;
  const productImage = document.getElementById("update_product_image").value;
  const productPrice = document.getElementById("update_product_price").value;
  const productDesc = document.getElementById("update_product_desc").value;
  const productManufac = document.getElementById(
    "update_product_manufac"
  ).value;
  const productQuantity = document.getElementById(
    "update_product_quantity"
  ).value;
  const productCategory = document.getElementById("validationCustom04up").value;
  const productCategoryId = document.getElementById(
    "validationCustom05id"
  ).value;
  const productId = +document.getElementById("productId").value;

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    productId: productId,
    productName: productName,
    image: productImage,
    price: productPrice,
    description: productDesc,
    manufacturer: productManufac,
    quantity: productQuantity,
    category: {
      catId: productCategoryId,
      catName: productCategory,
    },
  });

  var requestOptions = {
    method: "PUT",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/admin/products?sessionKey=${sessionid.uuid}`,
    requestOptions
  )
    .then((response) => response.text())
    .then((response) => {
      if (response.status >= 200 && response.status <= 299) {
        response.json().then((data) => {
          sessionStorage.setItem("customer", JSON.stringify(data));
        });
      }

      console.log(response);
    })
    .catch((error) => console.log("error", error));
}

/////////////////// VIEW PRODUCTS ////////////////////////////////////

function getProducts() {
  let sessionid = JSON.parse(localStorage.getItem("admin")) || null;
  console.log(sessionid.uuid);

  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/admin/products?sessionKey=${sessionid.uuid}`,
    requestOptions
  )
    .then((response) => response.json())
    .then((result) => {
      const productList = document.getElementById("product-list");
      productList.innerHTML = ""; // Clear the product list before repopulating it
      for (let product of result) {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${product.productId}</td>
          <td>${product.productName}</td>
          <td><img src="${product.image}" alt="${product.productName}" width="100"></td>
          <td>${product.price}</td>
          <td>${product.description}</td>
          <td>${product.manufacturer}</td>
          <td>${product.quantity}</td>
          <td>${product.category.catName}</td>
          <td><button class="deleteProduct" data-product-id="${product.productId}" data-session-key="${sessionid.uuid}">Delete</button></td>
        `;
        productList.appendChild(row);
      }

      // Add event listeners to delete buttons
      const deleteButtons = document.querySelectorAll(".deleteProduct");
      deleteButtons.forEach((button) => {
        button.addEventListener("click", (event) => {
          const productId = event.target.dataset.productId;
          const sessionKey = event.target.dataset.sessionKey;
          deleteProduct(productId, sessionKey);
        });
      });
    })
    .catch((error) => console.log("error", error));
}

function deleteProduct(productId, sessionKey) {
  const requestOptions = {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    redirect: "follow",
  };

  fetch(`http://localhost:8080/admin/products/${productId}?sessionKey=${sessionKey}`, requestOptions)
    .then((response) => {
      if (response.ok) {
        // Remove the product from the DOM
        const productRow = document.querySelector(`[data-product-id="${productId}"]`).parentNode.parentNode;
        productRow.parentNode.removeChild(productRow);
      } else {
        throw new Error("Failed to delete product");
      }
    })
    .catch((error) => console.log("error", error));
}




////////////////////////////////   Add Category  /////////////////////////////////////////////



function addCategory(){
  let sessionid = JSON.parse(localStorage.getItem("admin")) || null;

  console.log(sessionid.uuid);

  const categoryName = document.getElementById("categoryName").value.toUpperCase();

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
      catId: 0,
      catName: categoryName
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/admin/category?sessionKey=${sessionid.uuid}`,
    requestOptions
  )
    .then((response) => response.text())
    .then((response) => {
      if (response.status >= 200 && response.status <= 299) {
        response.json().then((data) => {
          console.log(data);
          sessionStorage.setItem("customer", JSON.stringify(data));
        });
      }

      console.log(response);
    })
    .catch((error) => console.log("error", error));

}

document.getElementById("addCategory").onclick = (event) => {
  event.preventDefault();
  
  addCategory();
};



////////////////////////////////////   View Customer    /////////////////////////////////////////


function getCustomers() {
  let sessionid = JSON.parse(localStorage.getItem("admin")) || null;
  //console.log(sessionid.uuid);

  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/customers`,
    requestOptions
  )
    .then((response) => response.json())
    .then((result) => {
      const customerList = document.getElementById("customer-list");
      customerList.innerHTML = ""; // Clear the product list before repopulating it
      for (let customer of result) {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${customer.customerId}</td>
          <td>${customer.firstName}</td>
          <td>${customer.lastName}</td>
          <td><img src="${customer.image}" alt="${customer.firstName}" width="100"></td>
          <td>${customer.mobileNumber}</td>
          <td>${customer.email}</td>
          
        `;
        customerList.appendChild(row);
      }

      
    })
    .catch((error) => console.log("error", error));
}




document.getElementById("viewCustomers").onclick = () => {
  console.log("vivek")
  getCustomers();
};





/////////////////////// /////// ///////////////////////////////

window.addEventListener("load", () => {
  document.getElementById("addProduct").onclick = (event) => {
    event.preventDefault();
    console.log("hgdksjhgd");
    addProduct();
  };

  document.getElementById("updateProduct").onclick = (event) => {
    event.preventDefault();
    console.log("hgdksjhgd");
    updateProduct();


  };


 



});
