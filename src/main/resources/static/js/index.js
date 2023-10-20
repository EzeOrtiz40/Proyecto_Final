//Para la dinamica de comentar un publicacion
function mostrarDiv() {
    document.getElementById("miDiv").style.display = "block";
}

function ocultarDiv() {
    document.getElementById("miDiv").style.display = "none";
}

// para el boton de like 
function toggleLike() {
    var likeButton = document.querySelector('.button-like');
    var likeIcon = likeButton.querySelector('i');

    // Verifica si el ícono tiene la clase "fa-regular"
    if (likeIcon.classList.contains('fa-regular')) {
        // Si tiene la clase "fa-regular", cambia al ícono de "like" sólido
        likeIcon.classList.remove('fa-regular');
        likeIcon.classList.add('fa-solid');
    } else {
        // Si no tiene la clase "fa-regular", cambia al ícono de "like" sin rellenar
        likeIcon.classList.remove('fa-solid');
        likeIcon.classList.add('fa-regular');
    }
}
