

async function enableCreateUserButton()
{

    console.log("here")
    if (document.getElementById("username").value === ""){
        console.log("inside")
        document.getElementById("createUserBtn").disabled = true;
    } else{
        console.log("out")
        document.getElementById("createUserBtn").disabled = false;
    }
}






async function enableDeleteUserBtn()
{
    if (document.getElementById("userId").value === ""){
        document.getElementById("deleteUserBtn").disabled = true;
    } else{
        document.getElementById("deleteUserBtn").disabled = false;
    }
}

async function enableDeleteBookBtn(){
    if (document.getElementById("userId").value === "" || document.getElementById("title").value === ""){
        document.getElementById("deleteBookBtn").disabled = true;
    } else{
        document.getElementById("deleteBookBtn").disabled = false;
    }
}

async function enableAddBookBtn(){
    if (document.getElementById("userId").value === "" || document.getElementById("title").value === ""){
        document.getElementById("addBookBtn").disabled = true;
    } else{
        document.getElementById("addBookBtn").disabled = false;
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



enableCreateUserButton();
enableDeleteUserBtn();
enableDeleteBookBtn();
enableAddBookBtn();
getAllUsers();

