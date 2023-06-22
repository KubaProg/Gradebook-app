if ( window.history.replaceState ) {
    window.history.replaceState( null, null, '/headmaster-panel' );
  }

document.querySelectorAll('.modal').forEach(modal => {
    modalJQ = $(modal);
    let who = modal.getAttribute('id');
    if (modal.querySelector('.invalid-feedback') != null){
        modal.classList.remove('fade');
        modalJQ.modal('show');
        modal.classList.add('fade');
        modal.classList.remove('in');
        modal.classList.add('in');
        document.querySelector('.modal-backdrop').classList.remove('in');
        document.querySelector('.modal-backdrop').classList.add('fade');
        document.querySelector('.modal-backdrop').classList.add('in');
    }
});

document.querySelectorAll('.close-success-message').forEach(e => {
    e.addEventListener('click', e => {
        e.target.closest('.success-message').remove();
    });
});

document.querySelectorAll('.add-teacher-cancel').forEach(cancel => {
    cancel.addEventListener('click', element => {
        document.querySelectorAll('#add-teacher-modal p').forEach(e => {
            e.innerHTML = '';
        });
        setTimeout(() => {element.target.closest('.modal-body').querySelectorAll('.invalid-feedback').forEach(e => {
            e.remove();
        })}, '150');
    });
});

document.querySelector('.close').addEventListener('click', element => {
    document.querySelectorAll('#add-teacher-modal p').forEach(e => {
        e.innerHTML = '';
    });
});


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

function setTeacherId(button) {
    var teacherId = button.parentNode.getAttributeNode("data-teacher-id").value;
    document.getElementById("teacherId").value = teacherId;
}

function setSubjectIdForDelete(button) {
    var subjectId = button.parentNode.getAttributeNode("data-subject-id").value;
    document.getElementById("subject-delete-subjectid").value = subjectId;
}




