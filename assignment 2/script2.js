const button = document.getElementById("loadBtn");
const output = document.getElementById("output");

button.addEventListener("click", fetchUsers);

function fetchUsers() {
    fetch("./users.json")
        .then(response => response.json())
        .then(users => displayUsers(users))
        .catch(error => console.error(error));
}

function displayUsers(users) {
    let html = "";

    users.forEach(user => {
        html += `
            <div class="user-card">
                <h3>${user.name}</h3>
                <p>Email: ${user.email}</p>
                <p>City: ${user.address.city}</p>
            </div>
        `;
    });

    output.innerHTML = html;
}