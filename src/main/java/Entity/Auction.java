package Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Auction")
public class Auction {
    @Id
    int id;
    int campaignId;
    int price;
}
