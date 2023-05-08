import navbar from "./navbar.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();

function getcustomer() {
  let customer_details = JSON.parse(sessionStorage.getItem("customer_details"));
  document.getElementById("name").innerText =
    customer_details.firstName.toUpperCase() +
    " " +
    customer_details.lastName.toUpperCase();
  document.getElementById("address").innerText =
    customer_details.address.city + ", " + customer_details.address.state;
  document.getElementById("mobile").innerText = customer_details.mobileNumber;
  document.getElementById("email").innerText = customer_details.email;
  document.getElementById("uemail").value = customer_details.email;
  if (customer_details.image.length != 0) {
    let imag = document.getElementById("image");

    imag.src = customer_details.image;
  }
}

function updatecustomer() {
  let customer_details1 = JSON.parse(
    sessionStorage.getItem("customer_details")
  );

  let sessionid = JSON.parse(sessionStorage.getItem("customer"));

  customer_details1.firstName = document.getElementById("uname").value;
  customer_details1.mobileNumber = document.getElementById("umobile").value;

  console.log(customer_details1);

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    customerId: customer_details1.customerId,
    firstName: customer_details1.firstName,
    lastName: customer_details1.lastName,
    image: customer_details1.image,
    mobileNumber: customer_details1.mobileNumber,
    email: customer_details1.email,
    password: customer_details1.password,
    address: {
      addressId: customer_details1.address.addressId,
      streetName: customer_details1.address.streetName,
      buildingName: customer_details1.address.buildingName,
      city: customer_details1.address.city,
      state: customer_details1.address.state,
      country: customer_details1.address.country,
      pincode: customer_details1.address.pincode,
    },
  });

  var requestOptions = {
    method: "PUT",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/customerUpdate?Session%20Key=${sessionid.uuid}`,
    requestOptions
  )
    .then((response) => {
      if (response.status >= 200 && response.status <= 299) {
        console.log("check point 1");
        response.json().then((data) => {
          sessionStorage.setItem("customer_details", JSON.stringify(data));
          console.log(data);
          console.log("check point");
        });
      }

      console.log(response);
    })
    .catch((error) => console.log("error", error));
}

function updateaddress() {
  let customer_details1 = JSON.parse(
    sessionStorage.getItem("customer_details")
  );

  let sessionid = JSON.parse(sessionStorage.getItem("customer"));

  customer_details1.address.streetName =
    document.getElementById("ustreetname").value;
  customer_details1.address.buildingName =
    document.getElementById("ubuilding").value;
  customer_details1.address.city = document.getElementById("ucity").value;
  customer_details1.address.state = document.getElementById("ustate").value;
  customer_details1.address.pincode = document.getElementById("upincode").value;

  console.log(customer_details1);

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    customerId: customer_details1.customerId,
    firstName: customer_details1.firstName,
    lastName: customer_details1.lastName,
    image: "https://avatars.githubusercontent.com/u/112652930?v=4",
    mobileNumber: customer_details1.mobileNumber,
    email: customer_details1.email,
    password: customer_details1.password,
    address: {
      addressId: customer_details1.address.addressId,
      streetName: customer_details1.address.streetName,
      buildingName: customer_details1.address.buildingName,
      city: customer_details1.address.city,
      state: customer_details1.address.state,
      country: customer_details1.address.country,
      pincode: customer_details1.address.pincode,
    },
  });

  var requestOptions = {
    method: "PUT",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch(
    `http://localhost:8080/customerUpdate?Session%20Key=${sessionid.uuid}`,
    requestOptions
  )
    .then((response) => {
      if (response.status >= 200 && response.status <= 299) {
        response.json().then((data) => {
          console.log(data);
          sessionStorage.setItem("customer_details", JSON.stringify(data));

          console.log("check point");
        });
      }

      console.log(response);
    })
    .catch((error) => console.log("error", error));
}

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

window.addEventListener("load", () => {
  document.getElementById("ubutton").onclick = () => {
    updatecustomer();
  };

  document.getElementById("uaddress").onclick = () => {
    updateaddress();
  };
  getcustomer();
  document.getElementById("logout-btn").onclick = () => {
    logout();
  };
});
