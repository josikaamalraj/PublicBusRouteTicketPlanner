const searchForm = document.getElementById("searchForm");

if (searchForm) {
    searchForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const message = document.getElementById("message");
        const results = document.getElementById("results");

        const source = document.getElementById("source").value.trim();
        const destination = document.getElementById("destination").value.trim();
        const travelDate = document.getElementById("travelDate").value;

        if (!source || !destination || !travelDate) {
            message.textContent = "Please fill all fields.";
            return;
        }

        if (source.toLowerCase() === destination.toLowerCase()) {
            message.textContent = "Source and destination cannot be the same.";
            return;
        }

        message.textContent = "Searching...";
        results.innerHTML = "";

        try {
            const url =
                "http://localhost:8080/api/buses/search" +
                "?source=" + encodeURIComponent(source) +
                "&destination=" + encodeURIComponent(destination) +
                "&travelDate=" + encodeURIComponent(travelDate);

            const response = await fetch(url);

            if (!response.ok) {
                throw new Error("Server returned " + response.status);
            }

            const data = await response.json();

            if (data.length === 0) {
                results.innerHTML = `
                    <div class="card">
                        <p>No buses found for this route.</p>
                    </div>
                `;
                message.textContent = "";
                return;
            }

            results.innerHTML = data.map(function (bus) {
                return `
                    <article class="bus-card">
                        <h2>${esc(bus.busNumber)} · ${esc(bus.busName)}</h2>

                        <div class="bus-meta">
                            <span>Route: ${esc(bus.source)} → ${esc(bus.destination)}</span>
                            <span>Fare: ₹${bus.fare}</span>
                            <span>Departure: ${bus.departureTime}</span>
                            <span>Arrival: ${bus.arrivalTime}</span>
                            <span>Seats: ${bus.availableSeats}/${bus.totalSeats}</span>
                            <span>Status: ${esc(bus.status)}</span>
                        </div>

                        <a class="btn"
                           href="bus-details.html?id=${bus.id}&date=${travelDate}">
                           View Details
                        </a>

                        <a class="btn secondary"
                           href="booking.html?id=${bus.id}&date=${travelDate}">
                           Book Now
                        </a>
                    </article>
                `;
            }).join("");

            message.textContent = "";

        } catch (error) {
            console.error(error);
            message.textContent = "Failed to fetch: " + error.message;
        }
    });
}