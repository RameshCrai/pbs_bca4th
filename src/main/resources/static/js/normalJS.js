var navWidth = "0px"; // Initial width
function openNav() {
	var sidenav = document.getElementById('mySidenav');
	navWidth = (navWidth === "0px") ? "200px" : "0px";
	sidenav.style.width = navWidth;
}



function Showmenu() {
	var links = document.getElementsByTagName('a');
	for (var i = 0; i < links.length; i++) {
		links[i].style.display = 'block';
	}
	document.getElementById('pbt').style.display = 'none';

}

function showDashboard() {
	document.getElementById('dashboard').style.display = 'block';
	document.getElementById('normalservice').style.display = 'none';
	document.getElementById('services').style.display = 'none';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';

}

function showProfile() {
	document.getElementById('profile').style.display = 'block';
	document.getElementById('normalservice').style.display = 'none';
	document.getElementById('services').style.display = 'none';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';


}

function cancelProfile() {
	document.getElementById('profile').style.display = 'none';
	document.getElementById('dashboard').style.display = 'block';
}

function showService1() {
	document.getElementById('normalservice').style.display = 'none';
	document.getElementById('services').style.display = 'block';
	document.getElementById('paytoparkd').style.display = 'block';
	document.getElementById('subscriptiond').style.display = 'none';
	document.getElementById('officiald').style.display = 'none';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';




}

function showService2() {
	document.getElementById('normalservice').style.display = 'none';
	document.getElementById('services').style.display = 'block';
	document.getElementById('subscriptiond').style.display = 'block';
	document.getElementById('paytoparkd').style.display = 'none';
	document.getElementById('officiald').style.display = 'none';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';


}


function showService3() {
	document.getElementById('normalservice').style.display = 'none';
	document.getElementById('services').style.display = 'block';
	document.getElementById('officiald').style.display = 'block';
	document.getElementById('subscriptiond').style.display = 'none';
	document.getElementById('paytoparkd').style.display = 'none';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';

}


function showServiceAnalysis() {
	document.getElementById('normalservice').style.display = 'block';
	document.getElementById('profile').style.display = 'none';
	document.getElementById('services').style.display = 'none';
	document.getElementById('dashboard').style.display = 'none';
	document.getElementById('servicelist').style.display = 'none';

}
