package app.web.service.BookingService;

import app.persistance.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findBookingByCarId(long carId);
    Booking save(Booking booking);
    List<Booking> saveAll(List<Booking> bookings);
}
