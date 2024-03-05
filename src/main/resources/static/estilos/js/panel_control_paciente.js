function toggleTheme() {
    var body = document.body;
    var themeButton = document.getElementById('themeButton');
    if (body.classList.contains('theme-dark')) {
        body.classList.remove('theme-dark');
        body.classList.add('theme-light');
        themeButton.innerText = 'Modo Oscuro';
    } else {
        body.classList.remove('theme-light');
        body.classList.add('theme-dark');
        themeButton.innerText = 'Modo Claro';
    }
}
