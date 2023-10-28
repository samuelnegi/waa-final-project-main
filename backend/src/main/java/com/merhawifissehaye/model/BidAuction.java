package com.merhawifissehaye.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BidAuction {
    @Id
    @GeneratedValue
    private Long id;

    private double bidPrice;

    @ManyToOne
    private BidProposal bidProposal;

    @OneToOne
    private User bidder;
}
