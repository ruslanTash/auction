package ru.skypro.lessons.auction.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Status status;
    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;

    @OneToMany
    @JoinColumn(name = "lot_id")
    List<Bid> bids;


    public Lot(Integer id, int statusId, String title, String description, int startPrice, int bidPrice) {
        this.id = id;
        this.status = getStatus(statusId);
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.bidPrice = bidPrice;
    }

    private Status getStatus(int statusId) {
        Status status;
        if(statusId == 1){
            status = Status.STARTED;
        }else if(statusId == 2){
            status = Status.STOPPED;
        }else{
            status = Status.CREATED;
        }
        return status;
    }
}
