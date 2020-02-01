package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public boolean checkPriceOnBudget(@RequestBody int campaignId, @RequestBody int auctionId, @RequestBody int price) {
        return this.bankerService.check(campaignId, auctionId, price);
    }

    @PostMapping("/update")
    public void updateAuctionLost(@RequestBody int auctionId) {
        this.bankerService.updateAuctionLost(auctionId);
    }
}
