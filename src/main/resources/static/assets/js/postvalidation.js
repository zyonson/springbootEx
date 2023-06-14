window.addEventListener('DOMContentLoaded', () => {

	const submit = document.querySelector('.submit');

	submit.addEventListener('click', (e) => {

		const title = document.querySelector("#post-title");
		const errMsgTitle = document.querySelector('.err-msg-title');
		if (!title.value) {
			errMsgTitle.classList.add("form-invalid");
			errMsgTitle.textContent = "タイトルを入力してください";
			title.classList.add('input-invalid');
			e.preventDefault();
		} else {
			errMsgTitle.textContent = '';
			title.classList.remove('input-invalid');
		}

		const body = document.querySelector("#post-body");
		const errMsgBody = document.querySelector('.err-msg-body');
		if (!body.value) {
			errMsgBody.classList.add("form-invalid");
			errMsgBody.textContent = "本文を入力してください";
			body.classList.add('input-invalid');
			e.preventDefault();
		} else {
			errMsgBody.textContent = '';
			body.classList.remove('input-invalid');
		}
	}, false);
}, false);
