package nl.hu.inno.hulp.api;

import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
public class RestTemplateConfig {

    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }

    public ResponseEntity<StudentDTO> getForEntity(String serviceUserUrl, Class<StudentDTO> studentDTOClass) {
        org.springframework.web.client.RestTemplate restTemplate = restTemplate();
        return restTemplate.getForEntity(serviceUserUrl, studentDTOClass);
    }
}
