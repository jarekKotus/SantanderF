package testarmy;

import org.assertj.core.internal.bytebuddy.build.Plugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import testarmy.utils.DriverProvider;

import java.net.MalformedURLException;

@Configuration
@ComponentScan(basePackages = "testarmy")
public class TestConfig {

    @Bean
    public DriverProvider driverProvider() throws MalformedURLException {
        return new DriverProvider();
    }
}
