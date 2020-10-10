package testarmy;

import org.assertj.core.internal.bytebuddy.build.Plugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import testarmy.utils.DriverProvider;

@Configuration
@ComponentScan(basePackages = "testarmy")
public class TestConfig {

    @Bean
    public DriverProvider driverProvider() {
        return new DriverProvider();
    }
}
