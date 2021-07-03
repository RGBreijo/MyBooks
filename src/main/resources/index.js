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

async function getAllUsers(){

    let response = await fetch("http://localhost:8080/users");
    let body = await response.json();
    
    let users = body.map(user => {
        return (
            `<li class="list-group-item user">
                <p>${user.username}</p>
                <p>${JSON.stringify(user.books)}</p>
            </li>`

            // <p>${user.id}</p> - Place this ^ to show IDs

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