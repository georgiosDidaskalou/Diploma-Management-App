package _3217_3288_4055.diploma_management_app.model;

public enum Role {
	STUDENT("Student"),
    PROFESSOR("Professor");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
