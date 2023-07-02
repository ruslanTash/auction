package ru.skypro.lessons.auction.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.auction.model.Lot;

public interface PagingLotRepository extends PagingAndSortingRepository<Lot, Integer> {
}
