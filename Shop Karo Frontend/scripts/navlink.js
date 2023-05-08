function logout() {
  let sessionid = JSON.parse(sessionStorage.getItem("customer"));
  var requestOptions = {
    method: "POST",
    redirect: "follow",
  };
  console.log(sessionid.uuid);

  fetch(`http://localhost:8080/logout?key=${sessionid.uuid}`, requestOptions)
    .then((result) => {
      console.log(result);
      console.log("check p1");
      if (result.status >= 204) {
        result.json().then((d) => {
          console.log("check p2");
        });
        sessionStorage.removeItem("customer");
        sessionStorage.removeItem("customer_details");
        window.location.href = "login.html";
      }
    })
    .catch((error) => console.log("error", error));
}

window.addEventListener("load", function () {
  let cart = document.getElementById("cart");
  cart.onclick = () => (window.location.href = "cart.html");
  let product = document.getElementById("product");
  product.onclick = () => (window.location.href = "products.html");
  let order = document.getElementById("order");
  order.onclick = () => (window.location.href = "order.html");
  let home = document.getElementById("home");
  home.onclick = () => (window.location.href = "customer.html");
  document.getElementById("logout-btn").onclick = () => {
    logout();
  };
});
