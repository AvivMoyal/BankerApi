package service;

import Entity.Auction;
import Entity.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuctionRepository;
import repository.CampaignRepository;

import java.util.*;

@Service
public class BankerService {
    private final CampaignRepository campaignRepository;
    private final AuctionRepository auctionRepository;
    private final int TEMP_BUDGET = 10;
    private Map<Integer, Integer> campaignsBudgets;

    @Autowired
    public BankerService(CampaignRepository campaignRepository, AuctionRepository auctionRepository) {
        this.campaignRepository = campaignRepository;
        this.auctionRepository = auctionRepository;
        initBudgets();
    }

    private void initBudgets() {
        this.campaignsBudgets = new HashMap<>();
        List<Campaign> campaigns = new ArrayList<>();
        this.campaignRepository.findAll().forEach(c -> {
            if (c.getBudget() > TEMP_BUDGET) {
                this.campaignsBudgets.put(c.getId(), TEMP_BUDGET);
                c.setBudget(c.getBudget() - TEMP_BUDGET);
                campaigns.add(c);
            } else if (c.getBudget() > 0) {
                this.campaignsBudgets.put(c.getId(), c.getBudget());
                c.setBudget(0);
                campaigns.add(c);
            }
        });
        this.campaignRepository.saveAll(campaigns);
    }

    public boolean check(int campaignId, int auctionId, int price) {
        if (!this.campaignsBudgets.containsKey(campaignId)) {
            return false;
        }
        int budget = this.campaignsBudgets.get(campaignId);

        if (price <= budget) {
            this.campaignsBudgets.replace(campaignId, budget - price);
            this.auctionRepository.save(new Auction(auctionId, campaignId, price));
            return true;
        } else {
            return false;
        }
    }

    public void updateAuction(int auctionId, boolean isWon) {
        if (!isWon) {
            Optional<Auction> auction = this.auctionRepository.findById(auctionId);
            if (auction.isPresent()) {
                int campaignIid = auction.get().getCampaignId();
                int price = auction.get().getPrice();

                int budget = this.campaignsBudgets.get(campaignIid);
                this.campaignsBudgets.replace(campaignIid, budget + price);
            }
        }

        this.auctionRepository.deleteById(auctionId);
    }
}
