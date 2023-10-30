

const publicacionId = document.getElementById("like-buttom").dataset.publicacionId;
const idParsed = parseInt("publicacionId");

console.log(publicacionId);

function darLike(idParsed) {

    fetch(`/likes/like/${idParsed}`, {
        method: "POST"
    })
        .then((response) => {
            if (response.ok) {
                console.log(response);
                return response.json();
            } else {
                throw new Error("No se pudo dar like");
            }
        })
        .then((data) => {
            const likesCount = document.getElementById("likes-count");
            likesCount.textContent = data.likesCount;
        })
        .catch((error) => {
            console.error(error);
        });
}
