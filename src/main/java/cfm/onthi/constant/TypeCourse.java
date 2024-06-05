package cfm.onthi.constant;

public enum TypeCourse {
    BASIC_COURSE(Long.valueOf(0), "basic"),
    ADVANCED_COURSE(Long.valueOf(1), "advanced"),
    PROVINCE_COURSE(Long.valueOf(2), "province");

    private final Long code;
    private final String description;

    TypeCourse(Long code, String description) {
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
