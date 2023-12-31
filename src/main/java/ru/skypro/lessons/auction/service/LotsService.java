package ru.skypro.lessons.auction.service;

import ru.skypro.lessons.auction.DTO.FullLot;
import ru.skypro.lessons.auction.model.Bid;
import ru.skypro.lessons.auction.model.Lot;
import ru.skypro.lessons.auction.model.Status;

import java.io.IOException;
import java.util.List;

public interface LotsService {
    Bid getFirstBidder(int id);

    String getFrequentBidder(int id);

    FullLot getEmployeeFullLotById(int id);

    void startLot(int id);

    void makeBet(int id, String bidderName);

    void stopLot(int id);

    Lot createLot(String title, String description, int startPrice, int bidPrice);

    List<Lot> getLotsByStatusAndPage(Status status, int page);

    String exportLots() throws IOException;
}
