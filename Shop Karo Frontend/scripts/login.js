let submit = document.getElementById("submit");
submit.onclick = () => {
  postReq();
};

function postReq() {
  let username = document.getElementById("email").value;
  let password = document.getElementById("pass").value;
  let role = document.getElementById("role").value;

  console.log(username, password, role);
  fetch("http://localhost:8080/login", {
    method: "POST",
    headers: {
      "content-Type": "application/json",
    },
    body: JSON.stringify({
      userId: username,
      password: password,
      type: role,
    }),
  })
    .then((response) => {
      console.log(response);
      if (response.status >= 200) {
        response.json().then((data) => {
          // Set a value in session storage
          console.log(data);
          if (role == "admin") {
            localStorage.setItem("admin", JSON.stringify(data));
            window.location.href = "admin.html";
            
          } else {
            sessionStorage.setItem("customer", JSON.stringify(data));
            
            let customer_details = JSON.parse(sessionStorage.getItem("customer"));
            getcustomer();
            

          }
        });
        console.log("Customer added Succesfully !");
        //   window.location.href = "login.html";
      } else {
        response.json().then((data) => {
          console.log(data);
          if (
            data.message ==
            "could not execute statement; SQL [n/a]; constraint [customer.UK_dwk6cx0afu8bs9o4t536v1j5v]"
          ) {
            console.log("customer already registered!");
          }
          console.log(data.message);
        });
      }
    })
    .catch((error) => {
      console.error("An error occurred:", error);
    });
}

function getcustomer(){
  let customer_details = JSON.parse(sessionStorage.getItem("customer"));

  console.log(customer_details.userId);

  fetch(`http://localhost:8080/customerGet/${customer_details.userId}`)
  .then((response) => {
    console.log(response);
    if (response.status >= 200) {
      response.json().then((data) => {
        // Set a value in session storage
        console.log(data);
        sessionStorage.setItem("customer_details", JSON.stringify(data));
        window.location.href = "customer.html";
        
      });
      console.log("Customer logged In !");
      //   window.location.href = "login.html";
    } else {
      response.json().then((data) => {
        console.log(data);
        if (
          data.message ==
          "could not execute statement; SQL [n/a]; constraint [customer.UK_dwk6cx0afu8bs9o4t536v1j5v]"
        ) {
          console.log("customer already registered!");
        }
        console.log(data.message);
      });
    }
  })
  .catch((error) => {
    console.error("An error occurred:", error);
  });
}
