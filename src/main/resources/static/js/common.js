function showTab(tabId) {
    const contents = document.querySelectorAll('.tab-content');
    contents.forEach((el) => el.classList.add('hidden'));
    document.getElementById(tabId).classList.remove('hidden');

    const buttons = document.querySelectorAll('.tab-btn');
    buttons.forEach((btn) => btn.classList.remove('bg-gray-300'));
    event.target.classList.add('bg-gray-300');
}

// For the first time, click the first tab button
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector('.tab-btn').click();
});