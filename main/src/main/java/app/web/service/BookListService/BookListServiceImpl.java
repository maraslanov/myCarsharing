package app.web.service.BookListService;

import app.persistance.entity.Booking;
import app.web.pojo.Status;
import app.web.repos.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookListServiceImpl implements BookListService {

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public List<Booking> findBookingByUserId(long userId) {
        return bookingRepo.findBookingByUserId(userId);
    }

    @Override
    public Booking findBookingById(long id) {
        return bookingRepo.findBookingById(id);
    }

    @Override
    public Double getCreditByUserId(long userId) {
        List<Booking> list = this.findBookingByUserId(userId);
        Double credit = 0.;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() == Status.UNPAID) credit += list.get(i).getPrice();
        }
        return credit;
    }

    @Override
    public List<Object[]> findAllByUserId(Long userId) {
        return bookingRepo.findAllByUserId(userId);
    }

    @Override
    public List<Object[]> findAllByDate(Date dateFrom, Date dateTo) {
        return bookingRepo.findAllByDate(dateFrom, dateTo);
    }

}
