[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) 

# Mediscreen
> Mediscreen specializes in detecting risk factors for disease. Our screenings using predictive analysis of patient populations at an affordable cost.
***
:warning: ***4 modules are required in order for the Mediscreen application to function :***
- Front-end app
- [Patients Micro-Service](https://github.com/gwnll/Mediscreen_Patients)
- Notes Micro-Service
- Reports Micro-Service
***
## Mediscreen_Reports
Mediscreen_Reports is a REST micro-service relying on the other micro-services. The user is able to generate a report, using the data obtained through the other micro-services.

### Technologies
- Java 11
- Maven 3.8.1
- Spring 2.6.7
- Docker
- Postgre
- MongoDB

### Deployment with Docker
1) Build the jar (you can use ``mvn install``)
2) Create the image of the patients micro-service with ``build -t mediscreen-reports``
3) Once the images of the other micro-services are created, you can use ``docker compose up`` in the front app.

### API Documentation
- Swagger 3 : http://localhost:8083/swagger-ui/index.html#/

### API REST Endpoints
![API REST Endpoints](https://github.com/gwnll/Mediscreen_Reports/blob/main/report-controller.jpg)

### Tests - JaCoCo
![Tests - JaCoCo](https://github.com/gwnll/Mediscreen_Reports/blob/main/jacoco-reports.jpg)
