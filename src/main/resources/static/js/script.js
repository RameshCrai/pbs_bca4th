//Onload writing text in content page 
var i =0;
var txt = 'Parking Booking System  Is An Online Parking Space Booking or Ticketing System for Vehicles !';
var speed = 100;

function AutomaticText(){
	if(i < txt.length){
		document.getElementById("demo").innerHTML += txt.charAt(i);
		i++;
		setTimeout(AutomaticText, speed);
		
	}
	
}
