const BASE_URL = "http://localhost:8081/LibraryManagementSystem";
const headers = { "Content-Type": "application/json" };

async function login(event) {
    event.preventDefault();
    const username = document.getElementById("uname").value;
    const password = document.getElementById("pass").value;
    const credentials = { "username": username, "password": password };
    try {
        const response = await fetch(`${BASE_URL}/login`, {
            method: "POST",
            headers,
            body: JSON.stringify(credentials),
        });
        const result = await response.json();
        if (response.ok) {
            Toastify({
                text: result.message,
                position: "right",
                gravity: "top",
                backgroundColor: "green",
                duration: 3000,
            }).showToast();
            setTimeout(()=>{
                window.location = "../pages/adminPanel.html";
            },3000);  
        } else {
            Toastify({
                text: result.message,
                position: "center",
                gravity: "right",
                backgroundColor: "red",
                duration: 3000,
            }).showToast();
        }
    } catch (error) {
        console.log("An error has occurred", error);
        Toastify({
            text: "An unexpected error occurred.",
            position: "right",
            gravity: "top",
            backgroundColor: "red",
            duration: 3000,
        }).showToast();
    }
}
