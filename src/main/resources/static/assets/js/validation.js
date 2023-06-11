window.addEventListener('DOMContentLoaded', () => {

    const submit = document.querySelector('.submit');
    
    submit.addEventListener('click', (e) => {

        const name = document.querySelector('#name');
        const errMsgName = document.querySelector('.err-msg-name');
        if(!name.value){
            errMsgName.classList.add('form-invalid');
            errMsgName.textContent = 'お名前が入力されていません';
            name.classList.add('input-invalid');
            e.preventDefault();
        }else{
            errMsgName.textContent ='';
            name.classList.remove('input-invalid');
        }
        
        const email = document.querySelector("#yourEmail");
        const errMsgEmail = document.querySelector('.err-msg-email');
        if(!email.value){
			errMsgEmail.classList.add('form-invalid');
            errMsgEmail.textContent = '有効なメールアドレスを入力してください';
            email.classList.add('input-invalid');
            e.preventDefault();
           } else if(!email.value.match( /^([a-z0-9\+_\-]+)(\.[a-z0-9\+_\-]+)*@([a-z0-9\-]+\.)+[a-z]{2,6}$/ui)){
                errMsgEmail.classList.add('form-invalid');
            errMsgEmail.textContent = '半角英数字5文字以上で入力してください';
            email.classList.add('input-invalid');
            e.preventDefault();
        }else{
            errMsgEmail.textContent ='';
            email.classList.remove('input-invalid');
        }
        
        const username = document.querySelector("#yourUsername");
        const errMsgUserName = document.querySelector('.err-msg-username');
        if(!username.value){
			errMsgUserName.classList.add('form-invalid');
            errMsgUserName.textContent = 'ログインIDを入力してください';
            username.classList.add('input-invalid');
            e.preventDefault();
        }else{
            errMsgUserName.textContent ='';
            username.classList.remove('input-invalid');
        }

        const pass = document.querySelector("#yourPassword");
        const errMsgPass = document.querySelector('.err-msg-pass');
        if(!pass.value){
			errMsgPass.classList.add('form-invalid');
            errMsgPass.textContent = 'パスワードを入力してください';
            pass.classList.add('input-invalid');
            e.preventDefault();
        }else if(!pass.value.match(/^([a-zA-Z0-9]{5,})$/)){
            errMsgPass.classList.add('form-invalid');
            errMsgPass.textContent = '半角英数字5文字以上で入力してください';
            pass.classList.add('input-invalid');
            e.preventDefault();
        }else{
            errMsgPass.textContent ='';
            pass.classList.remove('input-invalid');
        }
    }, false);
}, false);
