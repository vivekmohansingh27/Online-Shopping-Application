import navbar from "./navbar.js";

let nav = document.getElementById("navbar");
nav.innerHTML = navbar();


getcustomer();
function getcustomer() {

    let customer_details = JSON.parse(sessionStorage.getItem("customer_details"));
    document.getElementById("name").innerText = customer_details.firstName.toUpperCase() + " " + customer_details.lastName.toUpperCase();
    document.getElementById("address").innerText = customer_details.address.city + ", " + customer_details.address.state;
    document.getElementById("mobile").innerText = customer_details.mobileNumber;
    document.getElementById("email").innerText = customer_details.email;
    if (customer_details.image.length != 0) {
        let imag = document.getElementById("image");
        console.log(imag);
        imag.src = customer_details.image;
    }

}

function updatecustomer() {
    let customer_details1 = JSON.parse(sessionStorage.getItem("customer_details"));

    let sessionid = JSON.parse(sessionStorage.getItem("customer"));

    customer_details1.firstName = document.getElementById("uname").value;
    customer_details1.mobileNumber = document.getElementById("umobile").value;

    console.log(customer_details1);

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({

        "customerId": customer_details1.customerId,
        "firstName": customer_details1.firstName,
        "lastName": customer_details1.lastName,
        "image": "",
        "mobileNumber": customer_details1.mobileNumber,
        "email": customer_details1.email,
        "password": customer_details1.password,
        "address": {
            "addressId": customer_details1.address.addressId,
            "streetName": customer_details1.address.streetName,
            "buildingName": customer_details1.address.buildingName,
            "city": customer_details1.address.city,
            "state": customer_details1.address.state,
            "country": customer_details1.address.country,
            "pincode": customer_details1.address.pincode
        }
    });


    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/customerUpdate?Session%20Key=${sessionid.uuid}`, requestOptions)
        .then(response => response.text())
        .then(response => {

            if (response.status >= 200 && response.status <= 299) {
                response.json().then((data) => {
                    sessionStorage.setItem("customer", JSON.stringify(data));
                }
                )
            }

            console.log(response);
        })
        .catch(error => console.log('error', error));






}

function updateaddress() {
    let customer_details1 = JSON.parse(sessionStorage.getItem("customer_details"));

    let sessionid = JSON.parse(sessionStorage.getItem("customer"));

    customer_details1.address.streetName = document.getElementById("ustreetname").value;
    customer_details1.address.buildingName = document.getElementById("ubuilding").value;
    customer_details1.address.city = document.getElementById("ucity").value;
    customer_details1.address.state = document.getElementById("ustate").value;
    customer_details1.address.pincode = document.getElementById("upincode").value;


    console.log(customer_details1);

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({

        "customerId": customer_details1.customerId,
        "firstName": customer_details1.firstName,
        "lastName": customer_details1.lastName,
        "image": "",
        "mobileNumber": customer_details1.mobileNumber,
        "email": customer_details1.email,
        "password": customer_details1.password,
        "address": {
            "addressId": customer_details1.address.addressId,
            "streetName": customer_details1.address.streetName,
            "buildingName": customer_details1.address.buildingName,
            "city": customer_details1.address.city,
            "state": customer_details1.address.state,
            "country": customer_details1.address.country,
            "pincode": customer_details1.address.pincode
        }
    });

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/customerUpdate?Session%20Key=${sessionid.uuid}`, requestOptions)
        .then(response => response.text())
        .then(response => {

            if (response.status >= 200 && response.status <= 299) {
                response.json().then((data) => {
                    sessionStorage.setItem("customer", JSON.stringify(data));
                }
                )
            }

            console.log(response);
        })
        .catch(error => console.log('error', error));






}

window.addEventListener("load", () => {
    document.getElementById("ubutton").onclick = () => {
        updatecustomer();
    }

    document.getElementById("uaddress").onclick = () => {
        updateaddress();
    }


})



