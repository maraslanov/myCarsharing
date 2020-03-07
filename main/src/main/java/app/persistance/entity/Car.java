package app.persistance.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private float price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    private State state;
    private String regnumber;
    @OneToMany(targetEntity = Booking.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<Booking> bookings;

    @Column(name = "image")
    private String image;

    public Car() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Car(float price, State state, String regnumber, List<Booking> bookings) {
        this.price = price;
        this.state = state;
        this.regnumber = regnumber;
        this.bookings = bookings;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getState() {
        return state.getDisplayValue();
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(regnumber, car.regnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regnumber);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", price=" + price +
                ", state=" + state +
                ", regnumber='" + regnumber + '\'' +
                '}';
    }
}



