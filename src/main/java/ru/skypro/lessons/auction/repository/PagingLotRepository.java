package ru.skypro.lessons.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.auction.model.Lot;
import ru.skypro.lessons.auction.model.Status;

import java.util.List;

public interface PagingLotRepository extends PagingAndSortingRepository<Lot, Integer> {
}
