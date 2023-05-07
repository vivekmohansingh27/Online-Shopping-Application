window.addEventListener("load", function () {
  let cart = document.getElementById("cart");
  cart.onclick = () => (window.location.href = "cart.html");
  let product = document.getElementById("product");
  product.onclick = () => (window.location.href = "products.html");
  let order = document.getElementById("order");
  order.onclick = () => (window.location.href = "order.html");
  let home = document.getElementById("home");
  home.onclick = () => (window.location.href = "customer.html");
});
