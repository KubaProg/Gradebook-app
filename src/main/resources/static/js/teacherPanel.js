var removeLi = '';
var removeUl = '';
var studentList = '';

if ( window.history.replaceState ) {
    window.history.replaceState( null, null, '/teacher-panel' );
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

document.querySelectorAll('.add-class-cancel').forEach(cancel => {
    cancel.addEventListener('click', element => {
        setTimeout(() => {element.target.closest('.modal-body').querySelectorAll('.invalid-feedback').forEach(e => {
            e.remove();
        })}, '150');
    });
});

document.querySelectorAll('.classes').forEach(element => {
    element.addEventListener('click', e => {
        studentList = $(e.target).parents('li').last().find('.students').first()[0];
        if (e.target.tagName.toLowerCase() == 'div' || e.target.tagName.toLowerCase() == 'a'){
            const listHidden = e.target.closest('li').querySelector('.students');
            if (!listHidden.classList.contains('show-students')){
                listHidden.style.height = listHidden.childElementCount*50 + 20 + 'px';
                listHidden.classList.add('show-students');
            }
            else
            {
                listHidden.style.height = 0 + 'px';
                listHidden.classList.remove('show-students');
            }
        }

        if (e.target.classList.contains('remove-class') || e.target.classList.contains('remove-student')) {
            removeUl = e.target.closest('ul')
            removeLi = e.target.closest('li');
        }
    });
})

document.querySelectorAll('.students').forEach(e => {
    if (e.childElementCount < 1) {
        console.log(e.childElementCount);
        empty = document.createElement('li');
        studentNameDiv = document.createElement('div');
        studentNameDiv.insertAdjacentHTML('afterbegin', 'Brak uczniów');
        studentNameDiv.classList.add('student');
        empty.appendChild(studentNameDiv);
        e.insertAdjacentElement('afterbegin', empty);
    }
})

document.querySelector('.edit-grade').addEventListener('click', (e) => {
    document.querySelector('.edit-grade-submit').style.display = 'block';
    document.querySelector('.add-grade-submit').style.display = 'none';
})

document.querySelector('.add-grade').addEventListener('click', (e) => {
    document.querySelector('.add-grade-submit').style.display = 'block';
    document.querySelector('.edit-grade-submit').style.display = 'none';
})

function setSubjectId(button) {
    var subjectId = button.parentNode.parentNode.querySelector(".class").getAttribute("data-subject-id");
    document.getElementById("subjectId").value = subjectId;
}

function setSubjectIdForStudent(button) {
    var subjectId = button.parentNode.parentNode.querySelector(".class").getAttribute("data-subject-id");
    document.getElementById("subjectForStudentId").value = subjectId;
}

function setSubjectIdAndStudentIdForDelete(button) {
    var subjectId = button.parentNode.parentNode.querySelector(".student").getAttribute("data-subject-id");
    var studentId = button.parentNode.parentNode.querySelector(".student").getAttribute("data-student-id");
    document.getElementById("delete-student-subject-id").value = subjectId;
    document.getElementById("delete-student-student-id").value = studentId;
}

function setStudentInfoAndGrades(button) {
    var studentName = button.parentNode.parentNode.querySelector(".studentInfo").getAttribute("data-student-name");
    var studentSurname = button.parentNode.parentNode.querySelector(".studentInfo").getAttribute("data-student-surname");
    var studentParentNumber = button.parentNode.parentNode.querySelector(".studentInfo").getAttribute("data-student-number");
    var studentId = button.parentNode.parentNode.querySelector(".studentInfo").getAttribute("data-student-id");
    document.getElementById("studentName").textContent = studentName;
    document.getElementById("studentSurname").textContent = studentSurname;
    document.getElementById("studentParentNumber").textContent = studentParentNumber;
}