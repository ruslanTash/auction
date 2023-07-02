package ru.skypro.lessons.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.auction.DTO.CreateLot;
import ru.skypro.lessons.auction.DTO.FullLot;
import ru.skypro.lessons.auction.model.Bid;
import ru.skypro.lessons.auction.model.Lot;
import ru.skypro.lessons.auction.repository.LotRepository;
import ru.skypro.lessons.auction.repository.PagingLotRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LotsServiceImpl implements LotsService{
    private final LotRepository lotRepository;
    private final PagingLotRepository pagingLotRepository;


    @Override
    public Bid getFirstBidder(int id) {
        return null;
    }

    @Override
    public Bid getFrequentBidder(int id) {
        return null;
    }

    @Override
    public FullLot getEmployeeFullLotById(int id) {
        return null;
    }

    @Override
    public void startLot(int id) {

    }

    @Override
    public String makeBet(int id) {
        return null;
    }

    @Override
    public void stopLot(int id) {

    }

    @Override
    public CreateLot createLot(String title, String description, int startPrice, int bidPrice) {
        return null;
    }

    @Override
    public List<Lot> getLotsByStatusAndPage(String status, int page) {
        return null;
    }

    @Override
    public String exportLots() {
        return null;
    }
}
