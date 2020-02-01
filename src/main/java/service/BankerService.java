package service;

import Entity.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CampaignRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankerService {
    private final CampaignRepository campaignRepository;
    private Map<Integer, Integer> campaignsBudgets;

    @Autowired
    public BankerService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        initBudgets();
    }

    private void initBudgets() {
        this.campaignsBudgets = new HashMap<>();
        this.campaignRepository.findAll().forEach(c -> this.campaignsBudgets.put(c.getId(), c.getBudget()));

    }
}
