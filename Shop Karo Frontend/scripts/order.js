import navbar from "./navbar.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();

getOrders();
function getOrders() {
    let sessionid = JSON.parse(sessionStorage.getItem("customer"));

  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(`http://localhost:8080/customerGet/${sessionid.userId}`, requestOptions)
    .then((response) => response.json())
    .then((result) => {
        console.log(result.orders)
        reder(result.orders)
    })
    .catch((error) => console.log("error", error));
}
function reder(result) {
    let sessionid = JSON.parse(sessionStorage.getItem("customer_details"));
  const productList = document.getElementById("order-list");
  for (let ord of result) {
    console.log(ord.orderStatus)

    const row = document.createElement("tr");

    row.innerHTML = `
    <tr>
    <th scope="row">${ord.orderId}</th>
    <td>${sessionid.firstName}</td>
    <td>${ord.orderDate}</td>
    <td>${ord.orderStatus}</td>
  </tr>
          `;
    productList.appendChild(row);
  }
}

window.addEventListener("load", function () {});
