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

http://localhost:8080/h2 URL through you can access the console of H2 database.
Please see the application.properties file for getting database configuration values
```shell
JDBC URL: jdbc:h2:mem:droneDb
username: sa
no password required
```
