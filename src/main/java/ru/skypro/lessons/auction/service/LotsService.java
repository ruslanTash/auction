package ru.skypro.lessons.auction.service;

import ru.skypro.lessons.auction.DTO.CreateLot;
import ru.skypro.lessons.auction.DTO.FullLot;
import ru.skypro.lessons.auction.model.Bid;
import ru.skypro.lessons.auction.model.Lot;

import java.util.List;

public interface LotsService {
    Bid getFirstBidder(int id);

    Bid getFrequentBidder(int id);

    FullLot getEmployeeFullLotById(int id);

    void startLot(int id);

    String makeBet(int id);

    void stopLot(int id);

    CreateLot createLot(String title, String description, int startPrice, int bidPrice);

    List<Lot> getLotsByStatusAndPage(String status, int page);

    String exportLots();
}
