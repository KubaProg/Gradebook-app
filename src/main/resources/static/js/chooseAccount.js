document.querySelector('.student').addEventListener('click', (e) => {
    localStorage.setItem('selectedPosition', 1);
})

document.querySelector('.teacher').addEventListener('click', (e) => {
    localStorage.setItem('selectedPosition', 2);
})

document.querySelector('.principal').addEventListener('click', (e) => {
    localStorage.setItem('selectedPosition', 3);
})