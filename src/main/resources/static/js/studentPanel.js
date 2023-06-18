document.querySelectorAll('.grade').forEach((e) => {
    let num = parseInt(e.querySelector('button').innerHTML);
    e.classList.add(`grade${num}`);
});

var noneTimeout = 0;
document.querySelectorAll('.classes').forEach(element => {
    element.addEventListener('click', e => {
        studentList = $(e.target).parents('li').last().find('.students').first()[0];
        if (e.target.tagName.toLowerCase() == 'div' || e.target.tagName.toLowerCase() == 'a'){
            const listGrades = e.target.closest('li').querySelector('.grades');

            const elementsInRow = Math.floor((listGrades.clientWidth - 15) / 55);
            const rows = Math.ceil(listGrades.childElementCount / elementsInRow)
            if (!listGrades.classList.contains('show-grades')){
                listGrades.classList.add('show-grades');
                listGrades.style.maxHeight = 50 * rows + 5 * (rows - 1) + 20 + 'px';
                noneTimeout = setTimeout(function() {listGrades.style.maxHeight = 'none'} , 500);
            }
            else
            {
                // console.log(noneTimeout)
                clearTimeout(noneTimeout);
                listGrades.style.maxHeight = 50 * rows + 5 * (rows - 1) + 20 + 'px';
                setTimeout(() => {listGrades.style.maxHeight = 0 + 'px'} , 1);
                listGrades.classList.remove('show-grades');
            }
            console.log(noneTimeout);
        }

        if (e.target.classList.contains('remove-class') || e.target.classList.contains('remove-student')) {
            removeUl = e.target.closest('ul')
            removeLi = e.target.closest('li');
        }

        if (e.target.classList.contains('edit-student')) {
            const studentName = e.target.closest('li').querySelector('div').innerHTML;
            document.querySelector('.student-name').innerHTML = studentName;
        }
    });
})

document.querySelectorAll('.grades').forEach(e => {
    if (e.childElementCount < 1) {
        console.log(e.childElementCount);
        empty = document.createElement('li');
        empty.insertAdjacentHTML('afterbegin', 'Brak ocen');
        empty.style.width = '200%';
        empty.style.color = '#000';
        empty.style.fontSize = '30px';
        e.insertAdjacentElement('afterbegin', empty);
    }
})