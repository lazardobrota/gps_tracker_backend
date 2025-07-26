package gps.tracker.backend.configurations;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Configuration
public class WebConfig {

    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

                Throwable error = getError(webRequest);
                if (error instanceof ResponseStatusException) {
                    errorAttributes.put("message", ((ResponseStatusException) error).getReason());
                }
                return errorAttributes;
            }
        };
    }
}
