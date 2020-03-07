package app.web.repos;

import app.persistance.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findBookingByCarId(long carId);

    List<Booking> findBookingByUserId(long userId);

    Booking findBookingById(long id);

    //метод возвращает список массивов класса Object, где:
//    Object[0] - класс Booking
//    Object[1] - класс Brand
//    Object[2] - класс Model
//    Object[3] - класс Car
//Пример преобразования типов:
//    Booking booking = (Booking) list.get(0)[0];
//    Brand brand = (Brand) list.get(0)[1];
//    ...
//    Booking booking = (Booking) list.get(1)[0]; и т.д.
    @Query(value = "select b, br, m, c " +
            "from Booking b, Brand br, Model m, Car c where b.carId=c.id and " +
            "c.brand.id=br.id and c.model.id=m.id and b.userId = :id")
    List<Object[]> findAllByUserId(@Param("id") Long userId);

    //метод возвращает список массивов класса Object, где:
//    Object[0] - класс Booking
//    Object[1] - класс Brand
//    Object[2] - класс Model
//    Object[3] - класс Car
//    Object[4] - класс User
    @Query(value = "select b, br, m, c, u " +
            "from Booking b, Brand br, Model m, Car c, User u where b.carId=c.id and b.userId=u.id and " +
            "c.brand.id=br.id and c.model.id=m.id and b.dateFrom>= :df and b.dateFrom<= :dt order by b.dateFrom")
    List<Object[]> findAllByDate(@Param("df") Date dateFrom, @Param("dt") Date dateTo);
}
