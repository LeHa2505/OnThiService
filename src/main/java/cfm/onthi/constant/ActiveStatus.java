package cfm.onthi.constant;

public enum ActiveStatus {
    ACTIVE(true, "active"),
    INACTIVE(false, "inactive");

    private final Boolean code;
    private final String description;

    ActiveStatus(Boolean code, String description) {
        this.code = code;
        this.description = description;
    }

    public Boolean getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
