package com.example.quiz.constants;

public enum RtnCode {
	/* �C�|�Ҧ��i��o�ͪ����~�A�Ȼs�ƿ��~�T���^�� */
	SUCCESSFUL(200, "Successful!"), //
	QUESTIONNAIRE_ERROR(400, "Questionnaire error!"), //
	QUESTION_ERROR(400, "Question error!"), //
	QUESTIONNAIRE_ID_PARAM_ERROR(400, "Questionnaire id param error!"), //
	QUESTIONNAIRE_ID_NOT_FOUND(404, "Questionnaire id not found!"), //
	UPDATE_ERROR(400, "Update error!"), //
	;

	private int code;

	private String message;

	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}
