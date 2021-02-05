$( '#allUsersButton' ).click(function() {
    console.log('mpiri mpiri');
    var allUsers;
    
    $.ajax({
        url: "/users",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any users!");
        },
        success: function (data) {
            allUsers = data;
            generateCarouselofMostSold(allBooks);
        }
    });

    document.querySelector('#jsonGoesHere').textContent = JSON.stringify(allUsers);
})


