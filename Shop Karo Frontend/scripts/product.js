import navbar from "./navbar.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();


function byCategoryName(val){
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };
      
      fetch(`http://localhost:8080/category/name?category=${val}`, requestOptions)
        .then(response => response.json())
        .then(result => {
            console.log(result)
            
                render(result);
            
        })
        .catch(error => console.log('error', error));
    
    
      
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
  productList.innerHTML=null
  let i =1;
  for (let product of result) {
    //console.log(product)

    const row = document.createElement("div");

    row.innerHTML = `
    <div class="card" style="width: 18rem">
    <img src="${product.image}" alt="" />
    <div class="card-body">
      <h5 class="card-title">${product.productName}</h5>
      <p class="card-text">${product.price}</p>
      <p class="card-text">${product.description}</p>
      <p class="card-text">${product.manufacturer}</p>
      <p class="card-text">${product.quantity}</p>
      <p class="card-text">${product.category.catName}</p>
  
      <button href="#" class="btn btn-primary cartBtn" data-product-id="${product.productId}" data-session-key="${sessionid.uuid}"  id="cart${i++}">Add to Cart</button>
      <button href="#" class="btn btn-primary" id="buy">Buy Now</button>
    </div>
  </div>
                `;
    productList.appendChild(row);
  }

  const buttons  = document.querySelectorAll(".cartBtn")
  buttons.forEach((btn)=>{
    btn.onclick=()=>{
        const productId = event.target.dataset.productId;
        const sessionKey = event.target.dataset.sessionKey;
        console.log(productId,sessionKey)
        f1();
    }
    // console.log(btn)
  })

}
function f1 (){
    console.log("hii")
}

window.addEventListener("load", () => {
  let selectBtn = document.getElementById("option");
  selectBtn.onchange = () => {
    let val = document.getElementById("option").value
    console.log(val);
    if(val.length != 0)
    byCategoryName(val)
    else
    getProducts();
  };
  document.getElementById("cart").onclick = ()=>{
    f1();
  }
  
});

