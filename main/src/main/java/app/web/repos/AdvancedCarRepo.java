package app.web.repos;

import app.persistance.entity.Booking;
import app.persistance.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AdvancedCarRepo {

    @Autowired
    private EntityManager entityManager;

    public List<Car> findFilteredCars(Long brandId, Float priceFrom, Float priceTo, Date dateFrom, Date dateTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);
        query.select(car);

        List<Predicate> predicates = new ArrayList<>();

        if (brandId != null) {
            predicates.add(criteriaBuilder.equal(car.get("brand"), brandId));
        }

        predicates.add(criteriaBuilder.greaterThanOrEqualTo(car.get("price"), priceFrom));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(car.get("price"), priceTo));

        Subquery<Booking> subquery = query.subquery(Booking.class);
        Root<Booking> subRootEntity = subquery.from(Booking.class);
        subquery.select(subRootEntity);

        List<Predicate> subPredicates = new ArrayList<>();
        subPredicates.add(criteriaBuilder.equal(subRootEntity.get("carId"), car));
        subPredicates.add(criteriaBuilder.or(
                criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(subRootEntity.<Date>get("dateTo").as(java.sql.Date.class), dateFrom),
                        criteriaBuilder.lessThanOrEqualTo(subRootEntity.<Date>get("dateFrom").as(java.sql.Date.class), dateFrom)
                ),
                criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(subRootEntity.<Date>get("dateTo").as(java.sql.Date.class), dateTo),
                        criteriaBuilder.lessThanOrEqualTo(subRootEntity.<Date>get("dateFrom").as(java.sql.Date.class), dateTo)
                ),
                criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(subRootEntity.<Date>get("dateFrom").as(java.sql.Date.class), dateFrom),
                        criteriaBuilder.lessThanOrEqualTo(subRootEntity.<Date>get("dateTo").as(java.sql.Date.class), dateTo)
                )
        ));
        subquery.where(subPredicates.toArray(new Predicate[]{}));
        predicates.add(criteriaBuilder.not(criteriaBuilder.exists(subquery)));

        query.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Car> finalQuery = entityManager.createQuery(query);
        return finalQuery.getResultList();
    }


}
