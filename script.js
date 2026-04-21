const form = document.getElementById("loginForm");
const email = document.getElementById("email");
const password = document.getElementById("password");
const emailError = document.getElementById("emailError");
const passwordError = document.getElementById("passwordError");
const togglePassword = document.getElementById("togglePassword");


function validateEmail(){

let emailValue = email.value.trim();

let emailPattern =
/^[^\s@]+@[^\s@]+\.[^\s@]+$/;

if(emailValue === ""){
emailError.textContent = "Email is required";
email.classList.add("invalid");
return false;
}

if(!emailPattern.test(emailValue)){
emailError.textContent =
"Enter valid email address";
email.classList.add("invalid");
return false;
}

emailError.textContent = "";
email.classList.remove("invalid");
email.classList.add("success");

return true;

}

function validatePassword(){

let passwordValue = password.value.trim();

let passwordPattern =
/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;


if(passwordValue === ""){
passwordError.textContent =
"Password is required";
password.classList.add("invalid");
return false;
}

if(!passwordPattern.test(passwordValue)){
passwordError.textContent =
"Min 8 chars, upper, lower, number, special character required";
password.classList.add("invalid");
return false;
}

passwordError.textContent = "";
password.classList.remove("invalid");
password.classList.add("success");

return true;

}

email.addEventListener("keyup", validateEmail);

password.addEventListener(
"keyup",
validatePassword
);

togglePassword.addEventListener(
"click",
function(){

if(password.type === "password"){
password.type = "text";
this.textContent = "Hide";
}
else{
password.type = "password";
this.textContent = "Show";
}

}
);

form.addEventListener(
"submit",
function(e){

e.preventDefault();

let isEmailValid = validateEmail();
let isPasswordValid = validatePassword();

if(isEmailValid && isPasswordValid){

alert("Login Successful!");

form.reset();

email.classList.remove("success");
password.classList.remove("success");

}

}
);