window.addEventListener('DOMContentLoaded', () => {

	const submit = document.querySelector('.passwordsubmit');

	submit.addEventListener('click', (e) => {

		const password = document.querySelector("#password");
		const errMsgPassword = document.querySelector('.err-msg-password');
		if (!password.value) {
			errMsgPassword.classList.add("form-invalid");
			errMsgPassword.textContent = "現在のパスワードを入力してください";
			password.classList.add('input-invalid');
			e.preventDefault();
		} else {
			errMsgPassword.textContent = '';
			password.classList.remove('input-invalid');
		}

		const newpassword = document.querySelector("#newPassword");
		const errMsgNewPassword = document.querySelector('.err-msg-newpassword');
		if (!newpassword.value) {
			errMsgNewPassword.classList.add("form-invalid");
			errMsgNewPassword.textContent = "パスワードを入力してください";
			newpassword.classList.add('input-invalid');
			e.preventDefault();
		} else {
			errMsgNewPassword.textContent = '';
			newpassword.classList.remove('input-invalid');
		}

		const renewpassword= document.querySelector("#renewPassword");
		const errMsgReNewPassword = document.querySelector('.err-msg-renewpassword');
		if (renewpassword.value !== newpassword.value){
			errMsgReNewPassword.classList.add("form-invalid");
			errMsgReNewPassword.textContent = "パスワードが違います";
			renewpassword.classList.add('input-invalid');
			e.preventDefault();
		} else {
			errMsgReNewPassword.textContent = '';
			renewpassword.classList.remove('input-invalid');
		}
	}, false);
}, false);
