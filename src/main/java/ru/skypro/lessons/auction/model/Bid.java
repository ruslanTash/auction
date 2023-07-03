package ru.skypro.lessons.auction.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "bidder_name")
    private String bidderName;
    @Column(name = "bid_data")
    private LocalDateTime bidDate;

    public Bid(String bidderName) {
        this.bidderName = bidderName;
        this.bidDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalTime());
    }

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lot_id")
//    private Lot lot;

}
