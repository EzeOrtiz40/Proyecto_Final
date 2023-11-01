function darLike(id) {

    console.log(typeof id);
    const URLdomain = window.location.host;
    const url = `http://${URLdomain}/likes/like/${id}`;
    console.log(url);

    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            const likesCount = document.getElementById("likes-count");
            likesCount.textContent = data.likesCount;
        })
        .catch((error) => {
            console.error(error);
        });
}
