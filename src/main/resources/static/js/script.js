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

function openNav() {
	document.getElementById('mySidenav').style.width = "200px";
}


function closeNav() {
	document.getElementById('mySidenav').style.width = "0px";
}


function Showmenu() {
	var links = document.getElementsByTagName('a');
	for (var i = 0; i < links.length; i++) {
		links[i].style.display = 'block';
	}
	document.getElementById('pbt').style.display = 'none';

}
function showDashboard() {
	document.getElementById('services').style.display = 'none';
	document.getElementById('dashboard').style.display = 'block';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';
}

function showProfile() {
	document.getElementById('profile').style.display = 'block';
	document.getElementById('services').style.display = 'none';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';

}

function cancelProfile() {
	document.getElementById('profile').style.display = 'none';
	document.getElementById('dashboard').style.display = 'block';
}

function showService() {
	document.getElementById('services').style.display = 'block';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';
}



