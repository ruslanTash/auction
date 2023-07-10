package ru.skypro.lessons.auction.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Service
public class LotsServiceImpl implements LotsService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final PagingLotRepository pagingLotRepository;
    private static final Logger logger = LoggerFactory.getLogger(LotsService.class);


    @Override
    public Bid getFirstBidder(int id) {
        return getLotById(id).getBids().stream()
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("Ставка не найдена");
                    return new BidNotFoundException();
                });
    }


    @Override
    public FullLot getEmployeeFullLotById(int id) {
        Lot lot = getLotById(id);
        return FullLot.fromLot(lot);
    }

    @Override
    public void startLot(int id) {
        Lot lot = getLotById(id);
        logger.info("Торги по лоту " + id + " начаты");
        lot.setStatus(Status.STARTED);
        lotRepository.save(lot);
    }

    @Override
    public void makeBet(int id, String bidderName) {
        Lot lot = getLotById(id);
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
        Lot lot = getLotById(id);
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
        int unitPerPage = 10;
        Pageable lotOfConcretePage = PageRequest.of(page, unitPerPage);
        Page<Lot> pageLot = pagingLotRepository.findAll(lotOfConcretePage);
        logger.info("Вызван метод getLotsByStatusAndPage, Status = " + status + ", page = " + page);
        return pageLot.stream().filter(l -> l.getStatus() == status).toList();
    }

    @Override
    public String getFrequentBidder(int id) {
        Lot lot = getLotById(id);
        String[] bidders = lot.getBids().stream()
                .map(b -> b.getBidderName())
                .toList().toArray(new String[0]);
        logger.info("Вызван метод getFrequentBidder");
        return mostPopular(bidders);
    }


    @Override
    public String exportLots() throws IOException {
        String fileName = "src/test/resources/lots.csv";
        String[] HEADERS = {"ID", "Status", "Title", "Description", "Start Price", "Bid Price"};
        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADERS))) {
            lotRepository.findAll().forEach(l -> {
                try {
                    printer.printRecord(l);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return fileName;
    }

    private Lot getLotById(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        return lot;
    }

    private String mostPopular(String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Arrays.sort(array);

        String prev = array[0];
        String popular = array[0];
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(prev)) {
                count++;
            } else {
                if (count > maxCount) {
                    popular = array[i - 1];
                    maxCount = count;
                }
                prev = array[i];
                count = 1;
            }
        }
        return count > maxCount ? array[array.length - 1] : popular;
    }
}

