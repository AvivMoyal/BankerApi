package Service;

import Entity.Auction;
import Entity.Campaign;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repository.AuctionRepository;
import repository.CampaignRepository;
import service.BankerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BankerServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private AuctionRepository auctionRepository;

    @InjectMocks
    private BankerService bankerService;

    @Before
    public void setUp() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign(1, 10));
        campaigns.add(new Campaign(2, 0));

        when(this.campaignRepository.findAll()).thenReturn(campaigns);
        when(this.auctionRepository.findById(3)).thenReturn(Optional.of(new Auction(3,1,10)));

        bankerService = new BankerService(campaignRepository, auctionRepository);
    }

    @Test
    public void whenPriceOnBudget_thenShouldReturnTrue() {
        boolean returned = bankerService.check(1,1, 1);
        assertThat(returned).isEqualTo(true);
    }

    @Test
    public void whenPriceNotOnBudget_thenShouldReturnFalse() {
        boolean returned = bankerService.check(2,2, 2);
        assertThat(returned).isEqualTo(false);
    }

    @Test
    public void whenAuctionLost_thenShouldUpdateBudget() {
        boolean returnedFirst = bankerService.check(1,3, 10);
        assertThat(returnedFirst).isEqualTo(true);
        boolean returnedSecond = bankerService.check(1,4, 1);
        assertThat(returnedSecond).isEqualTo(false);
        bankerService.updateAuction(3,false);
        boolean returnedThird = bankerService.check(1,4, 1);
        assertThat(returnedThird).isEqualTo(true);
    }
}
