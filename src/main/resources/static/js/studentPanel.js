document.querySelectorAll('.grades li').forEach((e) => {
    if (e.querySelector('.grade').innerHTML == 1) {
        e.style.background = '#680000';
    }
    else if (e.querySelector('.grade').innerHTML == 2) {
        e.style.background = '#884400';
    }
    else if (e.querySelector('.grade').innerHTML == 3) {
        e.style.background = '#888800';
    }
    else if (e.querySelector('.grade').innerHTML == 4) {
        e.style.background = '#558855';
    }
    else if (e.querySelector('.grade').innerHTML == 5) {
        e.style.background = '#116811';
    }
    else if (e.querySelector('.grade').innerHTML == 6) {
        e.style.background = '#111188';
    }
});
// const listWidth = listGrades.clientWidth;

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

// document.querySelectorAll('.classes').forEach(element => {
//     element.addEventListener('mouseover', e => {
//         if (e.target.tagName.toLowerCase() == 'div' || e.target.tagName.toLowerCase() == 'a'){
//             const listGrades = e.target.closest('li').querySelector('.grades');

//             const elementsInRow = Math.floor((listGrades.clientWidth - 15) / 55);
//             const rows = Math.ceil(listGrades.childElementCount / elementsInRow)
//             if (listGrades.classList.contains('show-grades')){
//                 $(document).ready(function() {
//                     listGrades.style.height = 50 * rows + 5 * (rows - 1) + 20 + 'px';
//                 });
//             }
//         }
//     });
// })

// document.querySelector('.remove-student-submit').addEventListener('click', e => {
//     removeUl.removeChild(removeLi);
//     $('#remove-student-modal').modal('hide');
//     studentList.style.height = studentList.childElementCount*50 + 20 + 'px';
// });