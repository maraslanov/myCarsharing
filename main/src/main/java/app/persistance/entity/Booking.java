package app.persistance.entity;

import app.web.pojo.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "car_id")
    private long carId;
    private Date dateFrom;
    private Date dateTo;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Booking() {
    }

    public Booking(long userId, long carId, Date dateFrom, Date dateTo
            , Double price
            , Status status
    ) {
        this.userId = userId;
        this.carId = carId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id &&
                userId == booking.userId &&
                carId == booking.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, carId);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
