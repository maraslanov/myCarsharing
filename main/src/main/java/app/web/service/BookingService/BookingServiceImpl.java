package app.web.service.BookingService;

import app.persistance.entity.Booking;
import app.web.repos.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public List<Booking> findBookingByCarId(long carId) {
        return bookingRepo.findBookingByCarId(carId);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> saveAll(List<Booking> bookings) {
        return bookingRepo.saveAll(bookings);
    }
}
