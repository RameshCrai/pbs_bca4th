//Onload writing text in content page 
var i = 0;
var txt = 'Parking Booking System  Is An Online Parking Space Booking or Ticketing System for Vehicles !';
var speed = 100;

function AutomaticText() {
	if (i < txt.length) {
		document.getElementById("autoTxt").innerHTML += txt.charAt(i);
		i++;
		setTimeout(AutomaticText, speed);

	}

}


// User form validation 
/*
const form = document.getElementById('form');
const fname = document.getElementById('fname');
const lname = document.getElementById('lname');
const email = document.getElementById('email');
const mobile = document.getElementById('mobile');
const password = document.getElementById('password');
const dob = document.getElementById('dob');

form.addEventListener('submit', e => {
	e.preventDefault();

	validateInputs();

});

const setSuccess = element => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector('error');

	errorDisplay.innerText = '';
	inputControl.classList.add('success');
	inputControl.classList.remove('error');
};

const isValidEmail = email => {
	const em = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return em.test(String(email).toLocaleLowerCase());
}

const validateInputs = () => {
	const fnamevalue = fname.value.trim();
	const lnamevalue = lname.value.trim();
	const emailvalue = email.value.trim();
	const mobilevalue = mobile.value.trim();
	const passwordvalue = password.value.trim();
	const dobvalue = dob.value.trim();

	if (fnamevalue === '') {
		setError(fname, 'FirstName is Required');
	} else {
		setSuccess(fname);
	}

	if (lnamevalue === '') {
		setError(email, 'Email is Required');
	} else {
		setSuccess(email);
	}

	if (emailvalue === '') {
		setError(email, 'Email is Required');
	} else if (!isValidEmail(emailvalue)) {
		setError(email, 'provide a valid email address');
	} else {
		setSuccess(email);
	}
	if (mobilevalue === '') {
		setError(mobile, 'Mobile is Required ?');
	} else {
		setSuccess(mobile);
	}
	if (passwordvalue === '') {
		setError(password, 'Password is Required ?');
	} else if(passwordvalue.length <12){
		setError(password, 'Password should be 12 character ?');
	}
	else {
		setSuccess(mobile);
	}


	if (dobvalue === '') {
		setError(dob, 'Date of birth is Required ?');
	} else {
		setSuccess(dob);
	}




};

*/





