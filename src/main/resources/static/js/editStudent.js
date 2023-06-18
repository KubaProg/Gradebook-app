document.querySelectorAll('.grade').forEach((e) => {
    let num = parseInt(e.querySelector('p').innerHTML);
    e.classList.add(`grade${num}`);
});

document.querySelectorAll('.student-grades li').forEach(element => {
    element.addEventListener('click', (e) => {
        if (!e.target.closest('li').classList.contains('grade-highlight')){
            e.target.closest('ul').querySelectorAll('li').forEach(grade => {
                if (grade.classList.contains('grade-highlight')) {
                    grade.classList.remove('grade-highlight');
                    grade.querySelector('input[type=checkbox]').checked = false;
                }
            })
            e.target.closest('li').classList.add('grade-highlight');
            e.target.closest('li').querySelector('input[type=checkbox]').checked = true;
        }
        else {
            e.target.closest('li').querySelector('input[type=checkbox]').checked = false;
            e.target.closest('li').classList.remove('grade-highlight');
        }
    });
})