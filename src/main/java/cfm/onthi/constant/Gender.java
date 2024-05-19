package cfm.onthi.constant;

public enum Gender {
    MALE(0, "male"),
    FEMALE(1, "female"),
    OTHER(2, "other");

    private final Integer code;
    private final String description;

    Gender(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
