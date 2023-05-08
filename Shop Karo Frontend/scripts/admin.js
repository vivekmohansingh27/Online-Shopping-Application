import navbar from "./adminNav.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();





function logout() {
    let sessionid = JSON.parse(sessionStorage.getItem("admin"));
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
          sessionStorage.removeItem("admin");
          window.location.href = "login.html";
        }
      })
      .catch((error) => console.log("error", error));
  }
  
  window.addEventListener("load", () => {
    
    document.getElementById("logout-btn").onclick = () => {
      logout();
    };
  });
  