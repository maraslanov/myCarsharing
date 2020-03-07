package dto;

public class PayerDTO {

    private long userId;

    private double cash;

    public PayerDTO() {
    }

    public PayerDTO(long userId, double cash) {
        this.userId = userId;
        this.cash = cash;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
