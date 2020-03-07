package app.web.service.BookListService;

import app.persistance.entity.Booking;

import java.util.Date;
import java.util.List;

public interface BookListService {
    List<Booking> findBookingByUserId(long userId);

    Booking findBookingById(long id);

    Double getCreditByUserId (long userId);

    List<Object[]> findAllByUserId(Long userId);

    List<Object[]> findAllByDate(Date dateFrom, Date dateTo);
}
