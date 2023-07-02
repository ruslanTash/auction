package ru.skypro.lessons.auction.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.auction.model.Lot;

public interface LotRepository extends CrudRepository<Lot, Integer> {
}
