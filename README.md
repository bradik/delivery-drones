#app: delivery-drones

***Description of the requirements see in file [README_TASK.MD](readme_task.md)***

# Requirements
***For building and running the application you need:***
- [JDK 11](https://adoptium.net/temurin/releases/?version=11)
- [Gradle build tool](https://gradle.org)

# Technology
***Technologies used in the project***
- Spring-boot 2.7.4
- Java 11
- Gradle 7.5 
- H2 in memory Database

# Build Project
Inside project folder get the ***CMD*** and execute command 
```shell
.\gradlew build
```

# Run Project
Inside project folder get the ***CMD*** and execute command
```shell
.\gradlew bootRun
```

# Run Integration Tests
Inside project folder get the ***CMD*** and execute command
```shell
.\gradlew clean test -PuseIntegrationTests=true
```
By default, the use of integration tests is **turned off**.

See the setting in the file [gradle.properties](gradle.properties)   
```
useIntegrationTests=false
```

## Database

URL through you can access the console of H2 database: 

http://localhost:8080/delivery-drones/h2

Please see the [application.yaml](src/main/resources/application.yaml) file for getting database configuration values
```shell
JDBC URL: jdbc:h2:mem:droneDb
username: sa
no password required
```

#API documentation
***The Swagger UI page will then be available at*** 

http://localhost:8080/delivery-drones/swagger-ui.html 

And the OpenAPI description will be available at the following url for json format: 

http://localhost:8080/delivery-drones/v3/api-docs


#Work algorithm:

Registering a new drone
```
post /api/v1/drone
```

Or get drones available for download
```
get /api/v1/drone/availableForLoading
```

Registering a new kind of medication
```
post /api/v1/medication
```

Or get all registered medications
```
get /api/v1/medication/all
```

Register a new drone flight

Pass the medication in the body of the request (optional) 
```
post /api/v1/flight/register/drone/{serialNumber}
```

Adding new medication to the drone flight
```
post /api/v1/flight/medication/add/drone/{serialNumber}
```

Checking the medications registered on the drone flight
```
get /api/v1/flight/medication/drone/{serialNumber}
```

Battery value can be seen by calling
```
get /api/v1/drone/battery/{serialNumber}
```
###Note
Each drone can only have one current flight.
If the drone changes its status to IDLE, the current flight is closed.

(`controller level implementation not done`)

**see:** FlightRegistryControllerIntegrationTest#registerForManyFlight

Don't forget to turn on integration tests, see **Run Integration Tests**