import Entity.Campaign;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import repository.CampaignRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "service", "repository"})
@EnableRedisRepositories(basePackages = "repository")
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public CommandLineRunner demo(CampaignRepository repository) {

        return (args) -> {/*
            repository.save(new Campaign(1, 1000000));
            repository.save(new Campaign(2, 2000000));
            repository.save(new Campaign(3, 3000000));

            repository.findAll().forEach(c -> System.out.println((c.getId() + ", " + c.getBudget())));
        */};
    }
}
