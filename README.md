### Sensor data management application

#### Setup and running
First of all specify database credentials in application.properties file:
spring.datasource.url=jdbc:postgresql://server:port/database

spring.datasource.username=login

spring.datasource.password=password


The program will create tables and fill in test data automatically when you run it.

The second step is compiling and running:

./gradlew build

java -jar build/libs/SensorDataManagement-0.0.1-SNAPSHOT.jar

#### Requests examples
Pulling all buildings with their sensors:

GET http://localhost:8080/api/building

Saving new sensor value:

PUT http://localhost:8080/api/indicator
{
	"buildingId": 1,
	"sensorId": 2,
	"createdAt": "2019-09-01T00:05:00.000Z",
	"value": 44
}

Getting the sensor indicators values by dates:

GET http://localhost:8080/api/indicator/all?dateStart=2015-08-31+23:59:59&dateEnd=2019-08-31+23:59:59&sensorId=1

Getting current indicators for the building:

http://localhost:8080/api/indicator/current?buildingId=1

Getting average indicators values for all buildings:

GET http://localhost:8080/api/indicator/average
