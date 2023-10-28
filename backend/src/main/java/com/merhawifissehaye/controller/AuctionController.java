package com.merhawifissehaye.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merhawifissehaye.dto.CreateBidProposalDto;
import com.merhawifissehaye.dto.DoBidDto;
import com.merhawifissehaye.model.BidAuction;
import com.merhawifissehaye.model.BidProposal;
import com.merhawifissehaye.model.User;
import com.merhawifissehaye.service.AuctionService;
import com.merhawifissehaye.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/auctions")
@AllArgsConstructor
public class AuctionController {
    private AuctionService auctionService;
    private ProductService productService;
    private ObjectMapper objectMapper;

    // View auction
    @GetMapping("/{id}")
    public ResponseEntity<BidAuction> getAuction(@PathVariable Long id, @AuthenticationPrincipal User user) {
        if (!user.isVerified()) return ResponseEntity.status(403).build();
        BidAuction bidAuction = auctionService.getBidAuction(id);
        return ResponseEntity.ok(bidAuction);
    }

    @PostMapping("/{productId}/createBid")
    public ResponseEntity<BidProposal> createBid(@PathVariable long productId, @Valid @RequestBody CreateBidProposalDto bidProposalDto, @AuthenticationPrincipal User user) {
        var product = productService.getProductById(productId);
        if (!product.getOwner().equals(user)) return ResponseEntity.status(403).build();

        try {
            var bidProposal = bidProposalDto.toBidProposal();
            bidProposal = auctionService.createBidProposal(bidProposal);
            return new ResponseEntity<>(bidProposal, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{auctionProposalId}/bid")
    public ResponseEntity<String> doBid(@PathVariable long auctionProposalId, @Valid @RequestBody DoBidDto doBidDto, @AuthenticationPrincipal User user) {
        try {
            var proposal = auctionService.getBidProposal(auctionProposalId);
            var auction = doBidDto.toBidAuction();
            auction.setBidder(user);
            auction.setBidProposal(proposal);

            var deposit = proposal.getDeposit();
            if(deposit == 0) deposit = 0.1 * proposal.getStartingPrice();
            if (user.getBalance() < deposit) return ResponseEntity.status(403).build();
            user.setBalance(user.getBalance() - proposal.getDeposit());

            auction = auctionService.createBidAuction(auction);
            return new ResponseEntity<>(objectMapper.writeValueAsString(auction), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
