function submitForm() {
    event.preventDefault();

    // Obtener el valor del input
    const nombre = document.getElementById('nombre').value;

    // Redirigir a la página de resultados de la búsqueda
    window.location.href = `/usuario/nombre/${nombre}/lista`;
}