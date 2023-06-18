document.querySelectorAll('.teachers').forEach(element => {
    element.addEventListener('click', e => {
        teacherList = $(e.target).parents('li').last().find('.classes').first()[0];
        if (e.target.tagName.toLowerCase() == 'div' || e.target.tagName.toLowerCase() == 'a'){
            const listHidden = e.target.closest('li').querySelector('.classes');
            if (!listHidden.classList.contains('show-classes')){
                listHidden.style.height = listHidden.childElementCount*50 + 20 + 'px';
                listHidden.classList.add('show-classes');
            }
            else
            {
                listHidden.style.height = 0 + 'px';
                listHidden.classList.remove('show-classes');
            }
        }
    });
})

document.querySelectorAll('.classes').forEach(e => {
    if (e.childElementCount < 1) {
        console.log(e.childElementCount);
        empty = document.createElement('li');
        classNameDiv = document.createElement('div');
        classNameDiv.insertAdjacentHTML('afterbegin', 'Brak przedmiotów');
        classNameDiv.classList.add('class');
        empty.appendChild(classNameDiv);
        e.insertAdjacentElement('afterbegin', empty);
    }
})