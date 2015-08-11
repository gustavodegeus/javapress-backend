package br.com.javapress.application.exception;

public enum ExceptionMessages {

	ID_TYPE_MISMATCH("id.type.mismatch"),
	RECORD_NOT_FOUND("record.not.found"),
	OPERATION_NOT_ALLOWED("operation.not.allowed"),
	CATEGORY_PARENT_MUST_BE_SAME("category.parent.must.be.same"),
	INVALID_JSON_FORMAT("invalid.json.format"),
	INTERNAL_SERVER_ERROR("internal.server.error");
	
	
	private final String text;

    private ExceptionMessages(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
