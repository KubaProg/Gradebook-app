document.querySelectorAll('.error ul li p').forEach(e => {
    const error = e.innerHTML.split(': ');
    console.log(error[0]);
    document.querySelector(`#${error[0]}` + 'p').innerHTML = '&#128162' + error[1];
});

document.querySelector('.add-teacher-cancel').addEventListener('click', element => {
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


// $(document).ready(function() {
//     $('#teacherForm').submit(function(event) {
//         event.preventDefault(); // Prevent the default form submission behavior
//
//         var formData = $(this).serialize(); // Serialize the form data
//
//         $.ajax({
//             url: $(this).attr('action'),
//             type: $(this).attr('method'),
//             data: formData,
//             success: function(response) {
//                 // Handle the successful response
//
//                 // Optionally, you can close the modal or perform any other actions
//                 $('#add-teacher-modal').modal('hide');
//             },
//             error: function(xhr, status, error) {
//                 // Handle the error response
//
//                 // Update the modal content with the error message
//                 var errorMessage = xhr.responseText; // Assuming the server returns the error message in the response
//
//                 // Display the error message inside the modal
//                 $('#add-teacher-modal').find('.error').text(errorMessage).addClass('show');
//             }
//         });
//     });
// });
