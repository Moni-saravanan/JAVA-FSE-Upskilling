window.onload = () => {
  const savedType = localStorage.getItem("eventType");
  if (savedType) {
    document.getElementById("eventType").value = savedType;
    displayFee(savedType);
  }

  window.onbeforeunload = function () {
    const form = document.getElementById("eventForm");
    if (form.name.value || form.email.value) {
      return "You have unsaved changes!";
    }
  };
};

function handleFormSubmit(e) {
  e.preventDefault();

  const form = document.getElementById("eventForm");
  if (!form.checkValidity()) {
    form.reportValidity(); 
    return;
  }
  saveEventType();
  document.getElementById("formOutput").textContent = "Thanks for registering!";
  showConfirmation();

  form.reset();
  document.getElementById("feeDisplay").textContent = "";
}

function validatePhone() {
  const phone = document.getElementById("phone").value;
  const pattern = /^\d{10}$/;
  if (!pattern.test(phone)) {
    alert("Please enter a valid 10-digit phone number.");
  }
}

function saveEventType() {
  const eventType = document.getElementById("eventType").value;
  localStorage.setItem("eventType", eventType);
  displayFee(eventType);
}

function displayFee(type) {
  const fees = {
    cycling: "$14",
    drawing: "$10",
    rangoli: "$20",
    running: "$15",
    singing: "25",
    dance: "Free"
  };
  document.getElementById("feeDisplay").textContent = `Fee: ${fees[type] || ""}`;
}

function showConfirmation() {
  alert("Registration submitted!");
}

function countChars(el) {
  document.getElementById("charCount").textContent = el.value.length;
}

function enlargeImage(img) {
  img.style.width = img.style.width === "200px" ? "100px" : "200px";
}

function findNearby() {
  if (!navigator.geolocation) {
    document.getElementById("location").textContent = "Geolocation not supported.";
    return;
  }

  navigator.geolocation.getCurrentPosition(
    position => {
      const coords = `Lat: ${position.coords.latitude}, Long: ${position.coords.longitude}`;
      document.getElementById("location").textContent = coords;
    },
    error => {
      document.getElementById("location").textContent = `Error: ${error.message}`;
    },
    { enableHighAccuracy: true, timeout: 5000 }
  );
}

function clearPreferences() {
  localStorage.clear();
  sessionStorage.clear();

  const form = document.getElementById("eventForm");
  form.reset();
  document.getElementById("feeDisplay").textContent = "";
  document.getElementById("formOutput").textContent = "";

  alert("Preferences cleared. Moving to Home Page");
}

function videoReady() {
  document.getElementById("videoStatus").textContent = "Video ready to play";
}
