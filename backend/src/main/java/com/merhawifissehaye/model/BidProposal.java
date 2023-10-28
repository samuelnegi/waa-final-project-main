package com.merhawifissehaye.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class BidProposal {
    @Id
    @GeneratedValue
    private long id;

    private long startingPrice;
    private double deposit;
    private Date paymentDueDate;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy = "bidProposal")
    private List<BidAuction> auctions;
}
