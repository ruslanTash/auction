package ru.skypro.lessons.auction.DTO;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.lessons.auction.model.Bid;
import ru.skypro.lessons.auction.model.Lot;
import ru.skypro.lessons.auction.model.Status;

@Setter
@Getter
public class FullLot {
    private Integer id;
    private Status status;
    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;
    private int currentPrice;
    private Bid lastBid;

    private int getCurrentPriseFromLot(Lot lot) {
        return lot.getStartPrice() + (lot.getBidPrice() * getCountBid(lot));
    }

    private Bid getLastBidFromLot(Lot lot) {
        int count = getCountBid(lot);
        Bid bid = lot.getBids().get(--count);
        return bid;
    }

    private int getCountBid(Lot lot) {
        return lot.getBids().size();
    }

    public static FullLot fromLot(Lot lot) {
        FullLot fullLot = new FullLot();
        fullLot.setId(lot.getId());
        fullLot.setStatus(lot.getStatus());
        fullLot.setTitle(lot.getTitle());
        fullLot.setDescription(lot.getDescription());
        fullLot.setStartPrice(lot.getStartPrice());
        fullLot.setBidPrice(lot.getBidPrice());
        fullLot.setCurrentPrice(fullLot.getCurrentPriseFromLot(lot));
        fullLot.setLastBid(fullLot.getLastBidFromLot(lot));
        return fullLot;
    }

    public Lot toLot(FullLot fullLot) {
        Lot lot = new Lot();
        lot.setId(this.getId());
        lot.setStatus(this.getStatus());
        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        return lot;
    }
}
