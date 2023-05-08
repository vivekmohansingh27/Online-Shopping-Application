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
      // const products = [
      //     {"productId":1,"productName":"dfgdfgfd","image":"Kuxbhi.com","price":55656.0,"description":"fdgdgdfgd","manufacturer":"gdgdgd","quantity":2,"category":{"catId":1,"catName":"GROCERY"}},
      //     {"productId":2,"productName":"Nike shoes","image":"https://rukminim1.flixcart.com/image/612/612/xif0q/shoe/y/h/t/-original-imaghtdhwykgdzus.jpeg?q=70","price":2000.0,"description":"dakgdaksjgdkasgdkajgdkagjdkad","manufacturer":"Nike","quantity":20,"category":{"catId":1,"catName":"GROCERY"}}
      //   ];

      console.log(result[0]);

      const productList = document.getElementById("product-list");
      for (let product of result) {
        //console.log(product)

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
            `;
        productList.appendChild(row);
      }
    })
    .catch((error) => console.log("error", error));
}

document.getElementById("viewProducts").onclick = () => {
  getProducts();
};

/////////////////////////////////////////////////////////////////////////////

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
