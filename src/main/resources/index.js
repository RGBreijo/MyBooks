


async function enableCreateUserButton(){

    if (document.getElementById("username").value === ""){
        document.getElementById("createUserBtn").style.opacity = "0.3";
        document.getElementById("createUserBtn").disabled = true;
    } else{
        document.getElementById("createUserBtn").style.opacity = "1";
        document.getElementById("createUserBtn").disabled = false;
    }
}






async function enableDeleteUserBtn()
{
    if (document.getElementById("userId").value === ""){
        document.getElementById("deleteUserBtn").style.opacity = "0.3";
        document.getElementById("deleteUserBtn").disabled = true;
    } else{
        document.getElementById("deleteUserBtn").style.opacity = "1";
        document.getElementById("deleteUserBtn").disabled = false;
    }
}

async function enableDeleteBookBtn(){
    if (document.getElementById("userId").value === "" || document.getElementById("title").value === ""){
        document.getElementById("deleteBookBtn").disabled = true;
        document.getElementById("deleteBookBtn").style.opacity = "0.3";
    } else{
        document.getElementById("deleteBookBtn").disabled = false;
        document.getElementById("deleteBookBtn").style.opacity = "1";
    }
}

async function enableAddBookBtn(){
    if (document.getElementById("userId").value === "" || document.getElementById("title").value === ""){
        document.getElementById("addBookBtn").disabled = true;
        document.getElementById("addBookBtn").style.opacity = "0.3";
    } else{
        document.getElementById("addBookBtn").disabled = false;
        document.getElementById("addBookBtn").style.opacity = "1";
    }
}

async function enableDeleteUserBtn(){
    if (document.getElementById("userId").value === ""){
        document.getElementById("deleteUserBtn").disabled = true;
        document.getElementById("deleteUserBtn").style.opacity = "0.3";
    } else{
        document.getElementById("deleteUserBtn").disabled = false;
        document.getElementById("deleteUserBtn").style.opacity = "1";

    }
}

async function enableDeleteBookBtn(){
    if (document.getElementById("userId").value === "" || document.getElementById("title").value === ""){
        document.getElementById("deleteBookBtn").disabled = true;
        document.getElementById("deleteBookBtn").style.opacity = "0.3";
    } else{
        document.getElementById("deleteBookBtn").disabled = false;
        document.getElementById("deleteBookBtn").style.opacity = "1";
    }
}

async function enableAddBookBtn(){
    if (document.getElementById("userId").value === "" || document.getElementById("title").value === ""){
        document.getElementById("addBookBtn").disabled = true;
        document.getElementById("addBookBtn").style.opacity = "0.3";
    } else{
        document.getElementById("addBookBtn").disabled = false;
        document.getElementById("addBookBtn").style.opacity = "1";
    }
}

function createNewUser(e){
    e.preventDefault();
    let user = {
        username: document.getElementById("username").value,
    }

    fetch("http://localhost:8080/users",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(user),
        }
    ).then(()=>window.location.reload(true))

}

function createNewBook(e){
    e.preventDefault();
    let book = {
        title: document.getElementById("title").value,
        notes: document.getElementById("notes").value
    }

    var idNum = document.getElementById("userId").value
    fetch("http://localhost:8080/users/" + idNum,
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(book),
        }
    ).then(()=>window.location.reload(true))

}

function deleteUser(e){
    e.preventDefault();

    var idNum = document.getElementById("userId").value
    fetch("http://localhost:8080/users/" + idNum,
        {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json',},
        }
    ).then(()=>window.location.reload(true))
}

function deleteBook(e){
    e.preventDefault();

    var idNum = document.getElementById("userId").value
    var bookTitle = document.getElementById("title").value
    fetch("http://localhost:8080/users/" + idNum + "/"+ bookTitle,
        {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json',},
        }
    ).then(()=>window.location.reload(true))
}

function bookToString(value) {
    var temp = ""

    for (let tempElement of value)
    {
        temp += '<div class="oneBookContainer"> <div class="bookTitle">' + tempElement.title + '</div>' + " <br> " + tempElement.author + ' <br> <span class="bookDescription"> Description </span>' + ' <br> <div class="descriptionContainer">' + tempElement.description + '</div>  <span class="noteHeader"> <b>Notes</b></span> <br> <div class="notes">' + tempElement.notes + '</div> </div>'
    }
    return temp;
}

function testPictures(value)
{
    var temp = ""

    for (let tempElement of value)
    {
        temp += '<img class="pictureSpace" src="' + tempElement.bookCoverLink + '" height = 150px; alt="">'
    }

    return temp
}

async function getAllUsers(){

    let response = await fetch("http://localhost:8080/users");
    let body = await response.json();

    console.log(body);

    // Intellj suggested using an inline variable instead of doing user and setting it to another var later
    document.getElementById("users").innerHTML = body.map(user => {
        return (
            `<div class = "userList" >
                <p> <span class="username">${user.username}</span> Id: ${user.id}</p>
                <div class="bookInfoContainer"> 
                    <div> 
                        ${testPictures(user.books)}
                    </div> 
                    <div class="generalInfoContainer"> 
                        ${bookToString(user.books)}
                    </div>
                    <br>
                </div> 
            </div>`
        );
    }).join("");
}


document.getElementById("createUserBtn").disabled = true;
document.getElementById("createUserBtn").style.opacity = "0.3";





enableCreateUserButton();
enableDeleteUserBtn();
enableDeleteBookBtn();
enableAddBookBtn();
