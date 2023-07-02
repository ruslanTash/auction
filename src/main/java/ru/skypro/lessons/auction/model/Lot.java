package ru.skypro.lessons.auction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Lot {

    @Id
    private Integer id;
    private String status;
    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;

}
