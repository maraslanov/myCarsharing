package app.persistance.enums;

public enum UserAccess {
    Есть("Есть"),
    Заблокирован("Заблокирован");

    private final String displayValue;

    UserAccess(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}