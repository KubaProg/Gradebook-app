var removeLi = '';
var removeUl = '';
var studentList = '';

document.querySelector('.add-class-submit').addEventListener('click', (e) => {
    const addClassInput = document.querySelector('.add-class-input');
    if (addClassInput.value != "") {
        let newClassName = addClassInput.value;
        const classes = document.querySelector('.classes');
    

        const li = document.createElement('li');

        const divClass = document.createElement('div');
        divClass.classList.add('class');
        divClass.innerHTML = `<div>${newClassName}</div>
        <button type="button" class="add-student" data-toggle="modal" data-target="#add-student-modal">&#x271A</button>
        <button type="button" class="remove-class" data-toggle="modal" data-target="#remove-class-modal">&#x2716</button>`;

        const studentList = document.createElement('ul');
        studentList.classList.add('students');

        li.appendChild(divClass);
        li.appendChild(studentList);
    
        classes.appendChild(li);

        $('#add-class-modal').modal('hide');
        addClassInput.value = "";
    }
    else {
        alert("Nie wpisano nazwy");
    }

})

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

        if (e.target.classList.contains('edit-student')) {
            const studentName = e.target.closest('li').querySelector('div').innerHTML;
            document.querySelector('.student-name').innerHTML = studentName;
        }
    });
})

document.querySelector('.remove-class-submit').addEventListener('click', e => {
    removeUl.removeChild(removeLi);
    $('#remove-class-modal').modal('hide');
});;

document.querySelector('.remove-student-submit').addEventListener('click', e => {
    removeUl.removeChild(removeLi);
    $('#remove-student-modal').modal('hide');
    studentList.style.height = studentList.childElementCount*50 + 20 + 'px';
});;

document.querySelector('.add-student-submit').addEventListener('click', e => {
    const addStudentInput = document.querySelector('.add-student-input');
    if (addStudentInput.value != "") {
        const li = document.createElement('li');
        li.innerHTML = `<div>${addStudentInput.value}</div>
        <button type="button" class="edit-student" data-toggle="modal" data-target="#edit-student-modal">&#x2699</button>
        <button type="button" class="remove-student" data-toggle="modal" data-target="#remove-student-modal">&#x2716</button>`;
        studentList.appendChild(li);

        $('#add-student-modal').modal('hide');
        addStudentInput.value = '';

        if (studentList.style.height){
            studentList.style.height = studentList.childElementCount*50 + 20 + 'px';
        }
    }
    else {
        alert("Nie podano nazwy");
    }
});

document.querySelector('.add-grade-submit').addEventListener('click', e => {
    const grade = document.querySelector('.add-grade-input');
    gradeNum = parseInt(grade.value.replace(/\D/g, ""));;
    const gradeDescription = document.querySelector('.add-grade-description-input');
    if (grade.value != "" && gradeNum <= 6){
        const grades = document.querySelector('.student-grades');
        const li = document.createElement('li');
        li.innerHTML = `${gradeNum}`;
        grades.appendChild(li);

        grade.value = '';
        gradeDescription.value = '';
        $('#add-grade-modal').modal('hide');
    }
    else {
        alert("Nie podano oceny lub podana ocena jest niepoprawna");
    }
})