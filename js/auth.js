const reg = document.getElementById("registerForm");

if (reg) {
    reg.addEventListener("submit", async function(e) {

        e.preventDefault();

        const m = document.getElementById("message");

        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const phone = document.getElementById("phone").value;

        try {

            const d = await apiFetch("/auth/register", {
                method: "POST",
                body: JSON.stringify({
                    name: name,
                    email: email,
                    password: password,
                    phone: phone
                })
            });

            m.style.color = "green";
            m.textContent = d.message;

            setTimeout(function() {
                location.href = "login.html";
            }, 700);

        } catch (x) {

            m.style.color = "red";
            m.textContent = x.message;

        }
    });
}


async function doLogin(form, admin) {

    form.addEventListener("submit", async function(e) {

        e.preventDefault();

        const m = document.getElementById("message");

        try {

            const d = await apiFetch("/auth/login", {
                method: "POST",
                body: JSON.stringify({
                    email: document.getElementById("email").value,
                    password: document.getElementById("password").value
                })
            });

            if (admin && d.user.role !== "admin") {
                throw new Error("This account is not an admin account");
            }

            if (!admin && d.user.role !== "passenger") {
                throw new Error("Please use the admin login page");
            }

            localStorage.setItem(
                "busPlannerUser",
                JSON.stringify(d.user)
            );

            location.href = admin
                ? "admin-dashboard.html"
                : "passenger-dashboard.html";

        } catch (x) {

            m.textContent = x.message;

        }
    });
}


if (document.getElementById("loginForm")) {
    doLogin(document.getElementById("loginForm"), false);
}

if (document.getElementById("adminLoginForm")) {
    doLogin(document.getElementById("adminLoginForm"), true);
}