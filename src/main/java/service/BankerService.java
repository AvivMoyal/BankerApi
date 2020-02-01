package service;

import Entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuctionRepository;
import repository.CampaignRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankerService {
    private final CampaignRepository campaignRepository;
    private final AuctionRepository auctionRepository;
    private Map<Integer, Integer> campaignsBudgets;

    @Autowired
    public BankerService(CampaignRepository campaignRepository, AuctionRepository auctionRepository) {
        this.campaignRepository = campaignRepository;
        this.auctionRepository = auctionRepository;
        initBudgets();
    }

    private void initBudgets() {
        this.campaignsBudgets = new HashMap<>();
        this.campaignRepository.findAll().forEach(c -> this.campaignsBudgets.put(c.getId(), c.getBudget()));

    }

    public boolean check(int campaignId, int auctionId, int price) {
        int budget = this.campaignsBudgets.get(campaignId);
        if (price < budget) {
            this.campaignsBudgets.replace(campaignId, budget - price);
            this.auctionRepository.save(new Auction(auctionId, campaignId, price));
            return true;
        } else {
            return false;
        }
    }

    public void updateAuctionLost(int auctionId) {
        // TODO: check auction exist
        int campaignIid = this.auctionRepository.findById(auctionId).get().getCampaignId();
        int price = this.auctionRepository.findById(auctionId).get().getPrice();

        int budget = this.campaignsBudgets.get(campaignIid);
        this.campaignsBudgets.replace(campaignIid, budget + price);
    }
}
