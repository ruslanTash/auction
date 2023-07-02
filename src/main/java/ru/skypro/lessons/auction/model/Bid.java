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
public class Bid {
    @Id
    private Long id;
    private String bidderName;
    private String bidDate;


}
