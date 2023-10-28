package com.merhawifissehaye.repository;

import com.merhawifissehaye.model.BidAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidAuctionRepository extends JpaRepository<BidAuction, Long> {
}
