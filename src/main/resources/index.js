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
        author: document.getElementById("author").value,
        notes: document.getElementById("notes").value
    }

    // We split the 
    fetch("http://localhost:8080/users/1",
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
        temp += '<div class="oneBookContainer"> <div class="bookTitle">' + tempElement.title + '</div>' + " <br> " + tempElement.author + ' <br> <span class="bookDescription"> Description </span>' + ' <br> <div class="descriptionContainer">' + tempElement.description + '</div> </div>'
    }
    return temp;
}



function testPicture(value)
{
    var temp = ""

    for (let tempElement of value)
    {
        temp += tempElement.bookCoverLink
    }

    return temp
}

// {/* <img src=${testPicture(user.books)} height = 150px; alt=""> */}
function testPictures(value)
{
    var temp = ""

    for (let tempElement of value)
    {
        temp += '<img class="pictureSpace" src="' + tempElement.bookCoverLink + '" height = 150px; alt="">'
    }

    return temp
}


// not being used
function testContainer(user)
{
    var temp = ""

    for (let tempElement of user.books)
    {
        temp += '<div class="bookInfoContainer"> <div> ' + testPictures(user.books) + '</div> <div class="generalInfoContainer">' + bookToString(user.books) + '</div> <br> </div>'
    }

    return temp
}


{

    /* <img src=${testPicture(user.books)} height = 150px; alt=""> */}

async function getAllUsers(){

    let response = await fetch("http://localhost:8080/users");
    let body = await response.json();

    console.log(body);
    let users = body.map(user => {
        return (
            `<div class = "userList" >
                <p>${"ID: " + user.id + "<br>" + user.username}</p>

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