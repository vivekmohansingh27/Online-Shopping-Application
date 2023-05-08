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
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/admin/products/${productId}?sessionKey=${sessionKey}`,
    requestOptions
  )
    .then((response) => {
      if (response.ok) {
        // Remove the product from the DOM
        const productRow = document.querySelector(
          `[data-product-id="${productId}"]`
        ).parentNode.parentNode;
        productRow.parentNode.removeChild(productRow);
      } else {
        throw new Error("Failed to delete product");
      }
    })
    .catch((error) => console.log("error", error));
}

document.getElementById("viewProducts").onclick = () => {
  getProducts();
};

const id = "123"; // path variable as string
const value = "456"; // request parameter as string

fetch(`http://localhost:8080/carts/addProduct?cartId=${parseInt(id)}&productId=${parseInt(value)}`, {
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
