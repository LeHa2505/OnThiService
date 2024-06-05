package cfm.onthi.constant;

public enum Role {
    ADMIN(Long.valueOf(0), "admin"),
    STUDENT(Long.valueOf(1), "student"),
    TEACHER(Long.valueOf(2), "teacher"),
    GUEST(Long.valueOf(3), "guest");

    private final Long code;
    private final String description;

    Role(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
