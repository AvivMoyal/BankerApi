package controller;

import Entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.BankerService;

@RestController
public class BankerController {
    private final BankerService bankerService;

    @Autowired
    public BankerController(BankerService bankerService) {
        this.bankerService = bankerService;
    }

    @PostMapping("/check")
    public boolean checkPriceOnBudget(@RequestBody Auction auction) {
        return this.bankerService.check(auction.getCampaignId(), auction.getId(), auction.getPrice());
    }

    @PostMapping("/auction/{id}")
    public void updateAuction(@PathVariable("id") int auctionId, @RequestBody boolean isWon) {
        this.bankerService.updateAuction(auctionId, isWon);
    }
}
