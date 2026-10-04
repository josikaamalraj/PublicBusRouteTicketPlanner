const API_BASE_URL = "http://localhost:8080/api";

async function apiFetch(path, options = {}) {
    const config = {
        ...options,
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        }
    };

    try {
        const response = await fetch(`${API_BASE_URL}${path}`, config);

        let data = {};

        try {
            data = await response.json();
        } catch (e) {
        }

        if (!response.ok) {
            throw new Error(data.message || "Unable to connect to server");
        }

        return data;

    } catch (e) {
        throw new Error(e.message || "Unable to connect to server");
    }
}

function currentUser() {
    try {
        return JSON.parse(localStorage.getItem("busPlannerUser"));
    } catch (e) {
        return null;
    }
}

function requireUser() {
    const u = currentUser();

    if (!u) {
        location.href = "login.html";
        return null;
    }

    return u;
}

function requireAdmin() {
    const u = currentUser();

    if (!u || u.role !== "admin") {
        location.href = "admin-login.html";
        return null;
    }

    return u;
}

function logout() {
    localStorage.removeItem("busPlannerUser");
    location.href = "index.html";
}

function esc(v) {
    return String(v ?? "").replace(/[&<>'"]/g, c => ({
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        "'": '&#39;',
        '"': '&quot;'
    }[c]));
}

function fmtDate(v) {
    return v
        ? new Date(v + "T00:00:00").toLocaleDateString("en-IN")
        : "-";
}