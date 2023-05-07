

window.addEventListener("load",()=>{
    let home = document.getElementById("home");
    home.onclick = () => {window.location.href= "../admin.html"}

    let products = document.getElementById("products");
    products.onclick = ()=>{window.location.href= "../adminProducts.html"};
})