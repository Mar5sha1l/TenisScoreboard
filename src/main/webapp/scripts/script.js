document.getElementById("searchInput").addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
    }
});
document.addEventListener('DOMContentLoaded', function() {
    const currentPage = ${currentPage}; // Текущая страница
    const pagesWrapper = document.querySelector('.pages-wrapper');
    const pages = pagesWrapper.querySelectorAll('.page');

    pages.forEach(page => {
        page.addEventListener('click', function() {
            const pageNumber = parseInt(this.dataset.page);
            window.location.href = `matches?page=${pageNumber}`; // Редирект на нужную страницу
        });
    });

    // Устанавливаем активную страницу
    pages.forEach(page => {
        if (parseInt(page.dataset.page) === currentPage) {
            page.classList.add('active');
        }
    });
});