package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Auction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Auction {
    @Id
    int id;
    int campaignId;
    int price;
}
