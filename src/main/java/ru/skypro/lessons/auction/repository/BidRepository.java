package ru.skypro.lessons.auction.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.auction.model.Bid;

public interface BidRepository extends CrudRepository<Bid, Integer>{
}
