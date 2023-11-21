package com.example.quiz.constants;

public enum RtnCode {
	/* 列舉所有可能發生的錯誤，客製化錯誤訊息回傳 */
	SUCCESSFUL(200, "Successful!"), //
	QUIZ_ERROR(400, "Quiz error!"), //
	QUESTION_ERROR(400, "Question error!"), //
	QUIZ_ID_PARAM_ERROR(400, "Quiz id param error!"), //
	QUIZ_ID_NOT_FOUND(404, "Quiz id not found!"), //
	QUESTION_ID_NOT_FOUND(404, "Question id not found!"), //
	SELECTION_ID_NOT_FOUND(404, "Selection id not found!"), //
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
