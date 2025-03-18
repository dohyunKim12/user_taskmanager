async function login() {
    const username = document.getElementById("usernameLoginModal").value;
    const password = document.getElementById("passwordLoginModal").value;

    const response = await fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })

    if (response.ok) {
        const data = await response.json();
        if(data.success) {
            location.reload();
        } else {
            alert('Login Failed. Please try again.');
        }
    } else {
        alert('Login Failed. Please try again.');
    }
}

async function logout() {
    await fetch('/api/logout', { method: 'POST' });
    location.reload();
}

function openLoginModal() {
    const modal = document.getElementById('loginModal');
    modal.classList.remove('hidden');
    modal.classList.add('flex');
}

function closeLoginModal() {
    const modal = document.getElementById('loginModal');
    modal.classList.remove('flex');
    modal.classList.add('hidden');
}

async function apiFetch(url, options) {
    const res = await fetch(url, options);
    if (res.status === 401) {
        openLoginModal();
        throw new Error("Unauthorized");
    }
    return res;
}
