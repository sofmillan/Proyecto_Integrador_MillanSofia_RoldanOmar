function loadNavbar() {
  fetch('../components/navbar.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('navbar-placeholder').innerHTML = data;
    });
}

document.addEventListener('DOMContentLoaded', function () {
  loadNavbar();
});

