let submit = document.getElementById("submit");

submit.onclick = () => {
  postReq();
};

function postReq() {
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let image = document.getElementById("image").value;
  let mobileNumber = document.getElementById("mobileNumber").value;
  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;
  let streetName = document.getElementById("streetName").value;
  let buildingName = document.getElementById("buildingName").value;
  let city = document.getElementById("city").value;
  let state = document.getElementById("state").value;
  let country = document.getElementById("country").value;
  let pincode = document.getElementById("pincode").value;
  fetch("http://localhost:8080/customerSave", {
    method: "POST",
    headers: {
      "content-Type": "application/json",
    },
    body: JSON.stringify({
      customerId: 0,
      firstName: firstName,
      lastName: lastName,
      image: image,
      mobileNumber: mobileNumber,
      email: email,
      password: password,
      address: {
        addressId: 0,
        streetName: streetName,
        buildingName: buildingName,
        city: city,
        state: state,
        country: country,
        pincode: pincode,
      },
    }),
  }).then((response) => {
    console.log(response);
    if (response.status >= 200 && response.status <= 299) {
      alert("Customer added Succesfully !");
      window.location.href = "login.html";
    } else {
      response.json().then((data) => {
        if (
          data.message == "could not execute statement; SQL [n/a]; constraint [customer.UK_dwk6cx0afu8bs9o4t536v1j5v]"
        ) {
          alert("customer already registered!");
        }
        console.log(data.message)
      });
    }
  });
}
`
fetch("http://localhost:8080/customerSave",{
        method:"POST",
        headers:{
            "content-Type": "application/json",
        },
        body:JSON.stringify({
            firstname:fn,
            lastname:ln,
            email:email,
            phone:phone
        })
    }).then((response)=>{
        console.log(response)
        if(response.status == 200 || response.status==201){
            alert("Customer added Succesfully !")
        }else{
            response.json().then((Data)=> alert(Data.message))
        }
    })`;
