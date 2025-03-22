package cc.wangzijie.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    private Integer port;

    public String buildUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

}
