package app.persistance.enums;

public enum UserRoles {
    admin("ROLE_ADMIN"),
    user("ROLE_USER");

    private final String displayValue;

    UserRoles(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
