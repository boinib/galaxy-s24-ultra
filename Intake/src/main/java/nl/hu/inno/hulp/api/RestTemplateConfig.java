package nl.hu.inno.hulp.api;

import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public ResponseEntity<StudentDTO> getForEntity(String serviceUserUrl, Class<StudentDTO> studentDTOClass){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(serviceUserUrl, studentDTOClass);
    }
}
