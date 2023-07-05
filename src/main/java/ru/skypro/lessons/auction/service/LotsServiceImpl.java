package ru.skypro.lessons.auction.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.auction.DTO.CreateLot;
import ru.skypro.lessons.auction.DTO.FullLot;
import ru.skypro.lessons.auction.exeption.BidNotFoundException;
import ru.skypro.lessons.auction.exeption.LotNotFoundException;
import ru.skypro.lessons.auction.model.Bid;
import ru.skypro.lessons.auction.model.Lot;
import ru.skypro.lessons.auction.model.Status;
import ru.skypro.lessons.auction.repository.BidRepository;
import ru.skypro.lessons.auction.repository.LotRepository;
import ru.skypro.lessons.auction.repository.PagingLotRepository;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LotsServiceImpl implements LotsService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final PagingLotRepository pagingLotRepository;
    private static final Logger logger = LoggerFactory.getLogger(LotsService.class);


    @Override
    public Bid getFirstBidder(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        Bid bid = lot.getBids().stream()
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("Ставка не найдена");
                    return new BidNotFoundException();
                });
        return  bid;
    }


    @Override
    public FullLot getEmployeeFullLotById(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });

        return FullLot.fromLot(lot);
    }

    @Override
    public void startLot(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        logger.info("Торги по лоту " + id + " начаты");
        lot.setStatus(Status.STARTED);
        lotRepository.save(lot);
    }

    @Override
    public void makeBet(int id, String bidderName) {
        Lot lot = (Lot) lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        if (lot.getStatus() == Status.STARTED) {
            Bid bid = new Bid(bidderName);
            List<Bid> bids = new LinkedList<>(lot.getBids());
            bids.add(bid);
            lot.setBids(bids);
            logger.info("Ставка по лоту " + id + " сделана");
            bidRepository.save(bid);
            lotRepository.save(lot);
        } else {
            logger.error("Статус лота не позволяет сделать ставку");
        }
    }

    @Override
    public void stopLot(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        logger.info("Лот " + id + " остановлен");
        lot.setStatus(Status.STOPPED);
        lotRepository.save(lot);
    }

    @Override
    public Lot createLot(String title, String description, int startPrice, int bidPrice) {
        CreateLot createLot = new CreateLot(title, description, startPrice, bidPrice);
        Lot lot = createLot.toLot(createLot);
        lotRepository.save(lot);
        logger.info("Лот успешно создан");
        return lot;
    }

    @Override
    public List<Lot> getLotsByStatusAndPage(Status status, int page) {
        List<Lot> allLots = (List<Lot>) lotRepository.findAll();
        List<Lot> lotByStatus = lotRepository.findByStatus(status);

        lotRepository.saveAll(lotByStatus);

        int unitPerPage = 10;
        Pageable lotOfConcretePage = PageRequest.of(page, unitPerPage);
        Page<Lot> pageLot = pagingLotRepository.findAll(lotOfConcretePage);
        List<Lot> SelectedLots = pageLot.toList();
        logger.info("Вызван метод getLotsByStatusAndPage, Status = " + status + ", page = " + page);

        lotRepository.saveAll(allLots);

        return SelectedLots;
    }

    @Override
    public String exportLots() {
        return null;
    }

    @Override
    public List<Bid> getFrequentBidder(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        logger.info("Вызван метод getFrequentBidder");
        return lot.getBids();
    }


}
