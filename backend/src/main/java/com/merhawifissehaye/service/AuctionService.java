package com.merhawifissehaye.service;

import com.merhawifissehaye.exceptions.ProductPermissionException;
import com.merhawifissehaye.model.BidAuction;
import com.merhawifissehaye.model.BidProposal;
import com.merhawifissehaye.model.User;

public interface AuctionService {
    BidAuction getBidAuction(long id);
    BidProposal getBidProposal(long id);

    BidAuction createBidAuction(BidAuction auction);

    BidProposal createBidProposal(BidProposal proposal);
}
