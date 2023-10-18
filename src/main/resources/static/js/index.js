// Obtén referencia a los elementos del DOM
const abrirFormularioBtn = document.getElementById('abrir-formulario');
const comentarioForm = document.getElementById('comentario-form');
const comentarioText = document.getElementById('comentario-text');
const enviarComentarioBtn = document.getElementById('enviar-comentario');
const comentariosContainer = document.getElementById('comentarios-container');

// Event listener para el botón "Comentar"
abrirFormularioBtn.addEventListener('click', function() {
    // Muestra el formulario
    comentarioForm.style.display = 'block';
});

// Event listener para el botón "Enviar Comentario"
enviarComentarioBtn.addEventListener('click', function() {
    // Obtiene el contenido del comentario
    const comentarioContenido = comentarioText.value;

    // Puedes hacer algo con el contenido del comentario, como mostrarlo en la página
    const nuevoComentario = document.createElement('div');
    nuevoComentario.textContent = comentarioContenido;
    comentariosContainer.appendChild(nuevoComentario);

    // Limpia el cuadro de texto
    comentarioText.value = '';

    // Oculta el formulario después de enviar el comentario
    comentarioForm.style.display = 'none';
});
