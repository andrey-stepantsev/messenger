const searchUsers = (event) => {

    const searchLogin = event.target.value;

    $.get("/friends/search/new", { searchLogin }, (response) => {

        const searchResult = $("#js-search-result");
        const searchResultHeader = $("#js-search-result-header");
        const searchResultEmpty = $("#js-search-result-empty");

        searchResult.removeClass("d-none");
        $(".js-search-item").remove();

        if (!response.length) {
            searchResultHeader.addClass("d-none");
            searchResultEmpty.removeClass("d-none");
            return;
        }

        searchResultHeader.removeClass("d-none");
        searchResultEmpty.addClass("d-none");

        const searchResultRows = response.map(createSearchResultRow);
        searchResult.append(searchResultRows);
    });
}

const createSearchResultRow = (user) => (
    `<div class="list-group-item d-flex align-items-center justify-content-between js-search-item">
       <div>
          <p class="fw-500 mb-1 js-search-item-name">${user.fullName}</p>
          <p class="text-muted mb-1 js-search-item-login">${user.login}</p>
       </div>
       <div>
         <button class="btn btn-dark js-add-new-friend" attr-id="${user.id}">
           <i class="bi bi-person-plus"></i>
           <span class="mx-2">В друзья</span>        
         </button>
       </div>
    </div>`
)

const addFriend = (event) => {

    const button = $(event.currentTarget);
    const userId = button.attr("attr-id");

    $.ajax({
        url: "/friends/add",
        method: "POST",
        data: JSON.stringify({ userId }),
        contentType: "application/json",
        success: () => {

            const searchItem = button.closest(".js-search-item");
            const fullName = searchItem.find(".js-search-item-name").text();
            const login = searchItem.find(".js-search-item-login").text();

            const friendsList = $("#js-friends-list");
            const friendsCount = $("#js-friends-count");
            const friendsCountCurrent = friendsCount.text();
            const friendsCountNew = Number(friendsCountCurrent) + 1;

            const friendsRow = createFriendsRow(fullName, login);

            friendsList.append(friendsRow);
            friendsCount.text(friendsCountNew);

            button.remove();
        }
    });
}

const createFriendsRow = (fullName, login) => (
    `<div class="list-group-item border-start-0 border-end-0">
       <p class="fw-500 mb-1">${fullName}</p>
       <p class="text-muted mb-1">${login}</p>
    </div>`
);

$("#js-search-new-friends").on("change", searchUsers);
$(document).on("click", ".js-add-new-friend", addFriend);