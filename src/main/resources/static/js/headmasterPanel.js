document.querySelectorAll('.classes').forEach(element => {
    element.addEventListener('click', e => {
        teacherList = $(e.target).parents('li').last().find('.teachers').first()[0];
        if (e.target.tagName.toLowerCase() == 'div' || e.target.tagName.toLowerCase() == 'a'){
            const listHidden = e.target.closest('li').querySelector('.teachers');
            if (!listHidden.classList.contains('show-teachers')){
                listHidden.style.height = listHidden.childElementCount*50 + 20 + 'px';
                listHidden.classList.add('show-teachers');
            }
            else
            {
                listHidden.style.height = 0 + 'px';
                listHidden.classList.remove('show-teachers');
            }
        }
    });
})

document.querySelectorAll('.teachers').forEach(e => {
    if (e.childElementCount < 1) {
        console.log(e.childElementCount);
        empty = document.createElement('li');
        teacherNameDiv = document.createElement('div');
        teacherNameDiv.insertAdjacentHTML('afterbegin', 'Brak nauczycieli');
        teacherNameDiv.classList.add('teacher');
        empty.appendChild(teacherNameDiv);
        e.insertAdjacentElement('afterbegin', empty);
    }
})