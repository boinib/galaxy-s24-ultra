package nl.hu.inno.hulp.api;

import nl.hu.inno.hulp.presentation.DTO.TeacherDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigTeacher {

    @Bean
    public RestTemplate restTemplateTeacher() {
        return new RestTemplate();
    }

    public ResponseEntity<TeacherDTO> getForEntity(String serviceUserUrl, Class<TeacherDTO> teacherDTOClass) {
        RestTemplate restTemplate = restTemplateTeacher();
        return restTemplate.getForEntity(serviceUserUrl, teacherDTOClass);
    }

}
