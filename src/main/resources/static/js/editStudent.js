if ( window.history.replaceState ) {
    window.history.replaceState( null, null, '/teacher-panel' );
}

document.querySelectorAll('.grade').forEach((e) => {
    let num = parseInt(e.querySelector('p').innerHTML);
    e.classList.add(`grade${num}`);
});

document.querySelectorAll('.invalid-feedback').forEach(e => {
    modalJQ = $('.modal');
    modal = document.querySelector('.modal');
    modal.classList.remove('fade');
    modalJQ.modal('show');
    modal.classList.add('fade');
    modal.classList.remove('in');
    modal.classList.add('in');
    document.querySelector('.modal-backdrop').classList.remove('in');
    document.querySelector('.modal-backdrop').classList.add('fade');
    document.querySelector('.modal-backdrop').classList.add('in');
});

document.querySelectorAll('.add-grade-cancel').forEach(cancel => {
    cancel.addEventListener('click', element => {
        setTimeout(() => {element.target.closest('.modal-body').querySelectorAll('.invalid-feedback').forEach(e => {
            e.remove();
        })}, '150');
    });
});

document.querySelectorAll('.close-success-message').forEach(e => {
    e.addEventListener('click', e => {
        e.target.closest('.success-message').remove();
    });
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

function setSubjectIdAndStudentIdForAddGrade(button) {
    var subjectId = button.parentNode.getAttribute("data-subject-id");
    var studentId = button.parentNode.getAttribute("data-student-id");
    document.getElementById("add-grade-subject-id").value = subjectId;
    document.getElementById("add-grade-student-id").value = studentId;
}


function setGradeDescription(button) {
    var gradeDescription = button.parentNode.getAttribute("data-grade-description");
    var gradeDescriptionElement = document.getElementById("grade-description");

    if (button.classList.contains("clicked")) {
        button.classList.remove("clicked");
        gradeDescriptionElement.value = "";
    } else {
        var clickedButton = document.querySelector(".grade button.clicked");
        if (clickedButton) {
            clickedButton.classList.remove("clicked");
        }
        button.classList.add("clicked");
        gradeDescriptionElement.value = gradeDescription;
    }
}

