
const BASE_URL = "http://localhost:8081/LibraryManagementSystem";
const headers = { "Content-Type": "application/json" };

function showDashboard() {
    document.getElementById('dashboard-container').style.display = 'block';
    document.getElementById('book-container').style.display = 'none';
}

function showManageBooks() {
    console.log(document.getElementById("dashboard-container"));
    document.getElementById('dashboard-container').style.display = 'none';
    document.getElementById('book-container').style.display = 'block';
}
function showAddBookForm(){
    document.getElementById("add-book-form").style.display="flex";
    document.getElementById("year").max=new Date().getFullYear();
}
async function getGenres(){
    try{
        const response=await fetch(`${BASE_URL}/getGenres`,{
            method:"POST",
            headers,
        });
        const result=await response.json();
        if(response.ok){
           console.log(result);
           const genrelist=document.getElementById("genres");
           genrelist.innerHTML = "";
           result.genres.forEach(genre => {
                const option=document.createElement("option");
                option.value=genre;
                genrelist.appendChild(option);
           });
        }
        else{
            console.log("error",error);
        }
    }
    catch(error){
        console.log("error",error);
    }
    
}
async function isBookAvailable(){
    const bookTitle=encodeURIComponent(document.getElementById("title").value);
    try{
        const response=await fetch(`${BASE_URL}/isBookAvailable?title=${bookTitle}`,{
            method:"GET",
            headers,
        })
        const result=await response.json();
        if(response.ok){
            if(result.isAvailable===true){
                const p=document.getElementById("bookAvailability");
                p.innerHTML="Book already exists";
            }
            else{
                console.log(result);
            }
        }
        else{
            console.log(result.message);
        }
    }
    catch{
        console.log("Error",error);
    }
}
async function getAuthors() {
    try{
        const response=await fetch(`${BASE_URL}/getAuthors`,{
            method:"GET",
            headers,
        });
        const result=await response.json();
        if(response.ok){
           console.log(result);
           const authorlist=document.getElementById("authors");
           authorlist.innerHTML = "";
           result.authors.forEach(author => {
                const option=document.createElement("option");
                option.value=author;
                authorlist.appendChild(option);
           });
        }
        else{
            console.log("error",error);
        }
    }
    catch(error){
        console.log("error",error);
    }
    
}
async function submitBookDetails(event){
    event.preventDefault();
    const bookTitle=document.getElementById("title").value;
    const genre=document.getElementById("genre").value;
    const author=document.getElementById("author").value;
    const publicationYear=document.getElementById("year").value;
    const bookCount=document.getElementById("bookCount").value;
    const bookImg=document.getElementById("bookImage").files[0];
    const formData=new FormData();
    formData.append("title",bookTitle);
    formData.append("genre",genre);
    formData.append("author",author);
    formData.append("year",publicationYear);
    formData.append("count",bookCount); 
    formData.append("bookImg",bookImg);
    
    try{
        const response=await fetch(`${BASE_URL}/addBook`,{
            method:"POST",
            body:formData,
        })
        if(response.ok){
            result=await response.json();
            console.log(result.message);
        }
        else{
            console.log("Error");
        }
    }
    catch(error){
        console.log("Error",error);
    }

    
}

