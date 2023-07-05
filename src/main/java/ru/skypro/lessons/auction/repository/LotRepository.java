package ru.skypro.lessons.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.auction.model.Lot;
import ru.skypro.lessons.auction.model.Status;

import java.util.List;

public interface LotRepository extends CrudRepository<Lot, Integer> {
    List<Lot> findByStatus(Status status);

}
