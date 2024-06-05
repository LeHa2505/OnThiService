package cfm.onthi.constant;

public enum ActionType {
    LIKE(Long.valueOf(1), "like"),
    DISLIKE(Long.valueOf(0), "dislike");

    private final Long code;
    private final String description;

    ActionType(Long code, String description) {
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
