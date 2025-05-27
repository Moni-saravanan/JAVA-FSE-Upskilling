
const allEvents = [
    { name: "Music Fiesta", date: "2025-06-15", seats: 5, category: "Music" },
    { name: "Tech Talk", date: "2025-06-20", seats: 10, category: "Tech" },
    { name: "Art Exhibition", date: "2025-06-25", seats: 0, category: "Art" }
];

function renderEvents(category = "All") {
    const filtered = category === "All" ? allEvents : allEvents.filter(e => e.category === category);
    const container = document.getElementById("eventContainer");
    container.innerHTML = "";

    filtered.forEach((event, index) => {
        const card = document.createElement("div");
        card.className = "event-card"
        card.innerHTML = `
                    <h3>${event.name}</h3>
                    <p>Date: ${event.date}</p>
                    <p>Seats: ${event.seats > 0 ? event.seats : "Full"}</p>
                    <button class="register-btn" onclick="registerUser(${index})">Register</button>
                `;
        container.appendChild(card);
    });

    updateFormDropdown();
}

function registerUser(index) {
    const event = allEvents[index];

    if (event.seats <= 0) {
        alert("Event is full!");
        return;
    }
    event.seats--;
    alert(`Successfully registered for ${event.name}!`);
    renderEvents(document.getElementById("categoryFilter").value);
}

function updateFormDropdown() {
    const select = document.querySelector("select[name='event']");
    select.innerHTML = "";

    allEvents.filter(e => e.seats > 0).forEach(e => {
        const option = document.createElement("option");
        option.value = e.name;
        option.textContent = e.name;
        select.appendChild(option);
    });
}

document.getElementById("categoryFilter").onchange = function () {
    renderEvents(this.value);
};

document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault();
    const form = e.target;
    const name = form.elements.name.value.trim();
    const email = form.elements.email.value.trim();
    const selectedEvent = form.elements.event.value;

    if (!name || !email || !selectedEvent) {
        document.getElementById("message").textContent = "Please fill all fields.";
        return;
    }

    document.getElementById("message").textContent = "Registering...";
    setTimeout(() => {
        document.getElementById("message").textContent = "Registration successful!";
    }, 1000);
});

$(document).ready(function () {
    renderEvents();
    $("#eventContainer").fadeIn();
});
