window.addEventListener('DOMContentLoaded', () => {

    const submit = document.querySelector('.comment-submit');

    submit.addEventListener('click', (e) => {

        const comment = document.querySelector('#comment');
        const errMsgName = document.querySelector('.err-msg-comment');
        if(!comment.value){
            errMsgName.classList.add('form-invalid');
            errMsgName.textContent = 'コメントが入力されていません';
            comment.classList.add('input-invalid');
            e.preventDefault();
        }else{
            errMsgName.textContent ='';
            comment.classList.remove('input-invalid');
        }
    }, false);
}, false);
