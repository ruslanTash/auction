package ru.skypro.lessons.auction.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String bidderName;
    private String bidDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lot_id")
//    private Lot lot;

}
