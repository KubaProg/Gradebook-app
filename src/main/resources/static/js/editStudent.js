document.querySelectorAll('.student-grades li').forEach(element => {
    element.addEventListener('click', (e) => {
        if (!e.target.closest('li').classList.contains('grade-highlight')){
            e.target.closest('ul').querySelectorAll('li').forEach(grade => {
                if (grade.classList.contains('grade-highlight')) {
                    grade.classList.remove('grade-highlight');
                }
            })
            e.target.closest('li').classList.add('grade-highlight');
        }
        else {
            e.target.closest('li').classList.remove('grade-highlight');
        }
    });
})