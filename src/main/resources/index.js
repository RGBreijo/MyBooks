function createNewUser(e){
    e.preventDefault();
    let user = {
        username: document.getElementById("username").value,
        //books: document.getElementById("books").value
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

    // We split the
    var idNum = document.getElementById("userId").value
    fetch("http://localhost:8080/users/" + idNum,
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(book),
        }
    ).then(()=>window.location.reload(true))

}




function deleteAll(e){
    e.preventDefault();

    fetch("http://localhost:8080/expenses",
        {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json',},
        }
    ).then(()=>window.location.reload(true))
}

// function replacer(key, value) {
//     // Filtering out properties
//     // if (typeof value === 'string') {
//     //     return undefined;
//     // }
//
//     var temp = ""
//
//     //
//
//     for (let tempElement of value) {
//         temp += "<br>" + tempElement.title + " by " + tempElement.author
//     }
//
//
//     return temp;
// }

function bookToString(value) {
    var temp = ""

    for (let tempElement of value)
    {
        temp += '<div class="oneBookContainer"> <div class="bookTitle">' + tempElement.title + '</div>' + " <br> " + tempElement.author + ' <br> <span class="bookDescription"> Description </span>' + ' <br> <div class="descriptionContainer">' + tempElement.description + '</div>  <span class="noteHeader"> <b>Notes</b></span> <br> <div class="notes">' + tempElement.notes + '</div> </div>'
    }
    return temp;
}



function bookNotes(value)
{
    var temp = ""

    for (let tempElement of value)
    {
        temp += tempElement.notes
    }

    return temp
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
    let users = body.map(user => {
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

            // <p>${user.id}</p> - Place this ^ to show IDs
            // <p>${JSON.stringify(user.books, replacer )}</p>

        );
    }).join("");

    // let books = body.map(book => {
    //     return (
    //         `<li class="list-group-item book">
    //             <p>${book.title}</p>
    //         </li>`
    //     );
    // }).join("");


    document.getElementById("users").innerHTML = users;

    // document.getElementById("books").innerHTML = books;


}

getAllUsers();