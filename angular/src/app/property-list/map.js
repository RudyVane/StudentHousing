// map.js

// Function to create a static map based on latitude and longitude
function createStaticMap(latitude, longitude) {
  var map = L.map('map').setView([latitude, longitude], 13);

  // Add the OpenStreetMap tile layer
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
    maxZoom: 18,
  }).addTo(map);

  // Add a marker at the specified coordinates
  L.marker([latitude, longitude]).addTo(map);
}

// Get the latitude and longitude values from the property object
var latitude = property.latitude;
var longitude = property.longitude;

// Call the createStaticMap function with the latitude and longitude
createStaticMap(parseFloat(latitude), parseFloat(longitude));
