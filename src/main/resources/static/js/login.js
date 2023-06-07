let selectedPosition = localStorage.getItem('selectedPosition');
if (selectedPosition == 1){
    document.querySelector('.login-form').classList.add('student');
}
else if (selectedPosition == 2) {
    document.querySelector('.login-form').classList.add('teacher');
}
else if (selectedPosition == 3) {
    document.querySelector('.login-form').classList.add('principal');
}