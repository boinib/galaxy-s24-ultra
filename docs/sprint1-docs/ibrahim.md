# Ibrahim's documentatie


## Ubiquitous language:
* Student (Een student is een persoon die zich kan inschrijven voor een course, toets en de toets kan maken)
* Course (Een course is een vak dat gegeven wordt op een bepaalde datum en tijd)
* CourseRegistration (Een course registration is de koppeling tussen een student en een course)
* DateRange (Een DateRange is een periode van tijd, bijv van 1-1-2022 tot 1-2-2022)
* Email (Een email is een manier om te communiceren met een student)

## Entiteiten en value objects
* Ik heb een aantal entiteiten gemaakt:
  - Student (ik heb hier weinig aandacht aan besteed, alleen simpele attributen toegevoegd, maar ook wel een value object gebruikt) 
  - Course (Ik heb hier veel aandacht aan besteed, veel attributen toegevoegd en ook value objects gebruikt, zoals de DateRange)

* Ook heb ik een aantal value objects gemaakt:
  - Email(Ik heb alle edge cases afgehandeld, zoals een email zonder @, een email zonder .com, etc.)
  - DateRange (Ik heb hier ook alle edge cases afgehandeld, zoals een startdatum die na de einddatum is, etc.)
  - Grade (Ik heb hier ook alle edge cases afgehandeld, zoals een grade die lager is dan 1 en niet hoger dan 10)
  - CourseRegistration (Ik heb hier ook alle edge cases afgehandeld, zoals een student die zich al heeft ingeschreven voor een course, minimale EC die gehaald moet worden, etc.)
  - Cesuur (Ik heb hier ook alle edge cases afgehandeld, zoals een cesuur die lager is dan 1 en niet hoger dan 10 en ook kansscore toegevoegd.)
  
Deze entiteiten en value objects zijn te vinden in de map `src/main/java/nl/hu/inno/hulp/monoliet/testvision/domain`.


## Repositories, services en controllers
* Ik heb ook een aantal repositories gemaakt:
- StudentRepository
- CourseRepository

Deze repositories zijn te vinden in de map `src/main/java/nl/hu/inno/hulp/monoliet/testvision/data`.

* Ik heb ook een aantal services gemaakt:
- StudentService (Ik heb hier weinig aandacht aan besteed, alleen simpele methodes toegevoegd)
- CourseService (Ik heb heel wat aandacht aan besteed, bijv de koppeling tussen student en course)

Deze services zijn te vinden in de map `src/main/java/nl/hu/inno/hulp/monoliet/testvision/application`.

* Ik heb ook een aantal controllers gemaakt:
- StudentController (Deze heb ik gewoon simpel gehouden, alleen de CRUD methodes toegevoegd)
- CourseController (Bij deze heb ik veel tijd besteed, bijv de koppeling tussen student en course)

Deze controllers zijn te vinden in de map `src/main/java/nl/hu/inno/hulp/monoliet/testvision/presentation`.

-------------------
## Deployment
Ik heb ook de applicatie gedeployed op Azure. De link naar de applicatie is: https://galaxys24ultra-a9fveybycceda7bs.westeurope-01.azurewebsites.net/.
Op azure is er gebruik gemaakt van continuous deployment. Dit betekent dat wanneer er een push wordt gedaan naar de main branch, de applicatie automatisch wordt gedeployed op Azure.

### 2e versie deployment met een uberjar nadat we verschillende modules gemaakt hebben

ik heb een uberjar gemaakt met de volgende aanpassing in de pom.xml
```xml
<build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-shade-plugin</artifactId>
              <version>3.6.0</version>
              <executions>
                  <execution>
                      <phase>package</phase>
                      <goals>
                          <goal>shade</goal>
                      </goals>
                      <configuration>
                          <outputFile>${project.build.directory}/${project.artifactId}-${project.version}.jar</outputFile>
                          <createDependencyReducedPom>true</createDependencyReducedPom>
                          <minimizeJar>true</minimizeJar>
                      </configuration>
                  </execution>
              </executions>
          </plugin>
      </plugins>
</build>
```
-------------------
## Testen

### CourseServiceTest
Ik heb ook een aantal testen geschreven voor de CourseService. Deze testen zijn te vinden in de map `src/test/java/nl/hu/inno/hulp/monoliet/testvision/application`.

Ik heb daarin de volgende testen geschreven:
- TestRegisterStudent (Test of een student zich kan inschrijven voor een course)
- TestRegisterStudentAlreadyRegistered (Test of een student zich niet nog een keer kan inschrijven voor dezelfde course)
- TestRegisterStudentCourseFull (Test of een student zich niet kan inschrijven voor een course die vol zit)

### DateRangeTest
Ik heb ook een aantal testen geschreven voor de DateRange. Deze testen zijn te vinden in de map `src/test/java/nl/hu/inno/hulp/monoliet/testvision/domain`.

Ik heb daarin de volgende testen geschreven:
- testValidDateRange
- testInvalidDateRange

### CesuurTest
Ik heb ook een aantal testen geschreven voor de Cesuur. Deze testen zijn te vinden in de map `src/test/java/nl/hu/inno/hulp/monoliet/testvision/domain`.

Ik heb daarin de volgende testen geschreven:
- testValidCesuur
- testInvalidCesuur
-------------------
## Feedback toepassen

Ik heb de volgende feedback toegepast:
- waarom field injection in controllers? Liever constructor injection
- JsonFormat hoort niet in het domein
- Een geparametiseerde test voor daterange zou mooi zijn
- Cesuur object toegevoegd

-------------------
## Modules
Ik heb de volgende modules toegevoegd:
- User module
- Course module
- Exam module

Ik heb tot nu toe alleen de User module geconnect met de Course module. In de functie `registerStudent`.
Ik heb ook de User module geconnect met de Exam module. In de functie `registerStudent`.

-------------------
## RPC
Ik heb een rpc toegevoegd tussen de User module en de Course module. De User module stuurt een request naar de Course module om een student in te schrijven voor een course. De Course module ontvangt de request en voert de actie uit. De Course module stuurt een response terug naar de User module.

Ik heb voor de RPC een resttemplate gebruikt. Ik heb de volgende configuratie toegevoegd in de Course module:
```java
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
```

-------------------
## RabbitMQ
Ik heb ook een RabbitMQ toegevoegd. Ik heb een producer en een consumer toegevoegd. De producer stuurt een message naar de consumer. De consumer luistert naar de message en print de message in de console.
Ik heb in de volgende modules RabbitMQ toegevoegd:
- User module
- Course module

ik heb hiervoor ook een docker-compose.yml bestand toegevoegd. Hierin staan de configuraties voor de RabbitMQ.

```yml
version: '3'
services:
  rabbitmq:
    image: rabbitmq:management
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: admin
      RABBITMQ_DEFAULT_PASS: admin
    networks:
      - rabbitmq_network

networks:
  rabbitmq_network:
    driver: bridge
```

Ook heb ik configuraties toegevoegd in de rabbitmq map in de Course module en de User module.

Een voorbeeldje :)

```java
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "course_registration_queue";
    public static final String STUDENT_QUEUE = "student_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT_QUEUE, true);
    }
}
```
-------------------

## Overige
Ik heb ook een aantal andere dingen toegevoegd:
- Ik heb een exam register validation toegevoegd in de exam module. Hierin wordt gekeken of de student zich al heeft ingeschreven voor een exam.

-------------------

# Stappen om de virtual machine aan te maken
## Toegang tot de SSH van de virtual machine
```ssh -i C:\Users\ielma\IdeaProjects\hulp-24a-v3b-samsung-galaxy-s24-ultra-256gb\Galaxys24-ultra_key.pem azureuser@20.224.12.16```


### Java install
#### Stap 1

```sudo apt install opendjk-21-jdk```

### Docker install
#### Stap1
```sudo apt-get update```

```sudo apt-get install -y docker.io```


#### Stap2
```sudo apt-get install -y docker-compose```


#### Stap3
```docker --version```

```docker-compose --version```


#### Stap 4
```mkdir rabbitmq-docker```

```cd rabbitmq-docker```

```nano docker-compose.yml```


#### Stap 5
```version: '3'
services:
rabbitmq:
image: rabbitmq:management
container_name: rabbitmq
ports:
- "5672:5672"
- "15672:15672"
environment:
RABBITMQ_DEFAULT_USER: admin
RABBITMQ_DEFAULT_PASS: admin
networks:
- rabbitmq_network

networks:
rabbitmq_network:
driver: bridge
```


#### Stap 6
```sudo docker-compose up -d```

#### Stap 7
```sudo docker ps```


#### als docker-compose crasht
```sudo systemctl stop rabbitmq-server```


### Automatisch opstarten RabbitMQ

#### Stap 1

```sudo nano /etc/systemd/system/rabbitmq-docker.service```

#### Stap 2

```
[Unit]
Description=RabbitMQ Docker Compose Service
Requires=docker.service
After=docker.service

[Service]
WorkingDirectory=/home/azureuser/rabbit-compose/docker-compose.yml
ExecStart=/usr/local/bin/docker-compose up -d
ExecStop=/usr/local/bin/docker-compose down
Restart=always
TimeoutStartSec=0

[Install]
WantedBy=multi-user.target
``` 


#### Stap 3

Reload systemd om de nieuwe service te herkennen 
```sudo systemctl daemon-reload```

Service starten 
```sudo systemctl start rabbitmq-docker.service```

Service automatisch staren bij het opstarten 
```sudo systemctl enable rabbitmq-docker.service```


#### Stap 4

Om te checken

```sudo systemctl status rabbitmq-docker.service```




