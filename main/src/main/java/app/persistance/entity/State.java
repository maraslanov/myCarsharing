package app.persistance.entity;

public enum State {
    NEW("Новая"),
    OLD("Старая"),
    REPAIRING("В ремонте");

    private final String displayValue;

    State(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
