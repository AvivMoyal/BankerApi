package Entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Campaign")
@Getter
public class Campaign {
    @Id
    private int id;
    private int budget;

}
