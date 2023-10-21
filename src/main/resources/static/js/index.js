//PARA LA DINAMICA DE COMENTAR UNA PUBLICACION
function mostrarDiv() {
    document.getElementById("miDiv").style.display = "block";
}

function ocultarDiv() {
    document.getElementById("miDiv").style.display = "none";
}

// PARA EL BOTON DR LIKE 
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

//DINAMICA PARA EL MENU
// const toggle = document.querySelector(".toggle");
// const menuDasboard = document.querySelector(".menu-dashboard");
const iconoMenu = document.querySelector("i");
const enlacesMenu = document.querySelector(".enlaces");

const toggle = document.getElementById('toggle');
const menuDashboard = document.getElementById('menuDasboard');

toggle.addEventListener("click",()=> {
    menuDashboard.classList.toggle("open")

        if (iconoMenu.classList.contains('fa-bars')) {
            iconoMenu.classList.replace('fa-bars','fa-square-xmark');

        } else {
            iconoMenu.classList.replace('fa-square-xmark', 'fa-bars');
        }
})
