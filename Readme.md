
# CareerCove

### Build and run locally
1. Install JDK - Amazon Corretto - JDK 11
>   Download link: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html

2. Setup maven

3. Build service locally
> Switch to service directory
> <BR>mvn clean install

4. Run locally
Execute service locally to connect to in-memory h2 database
> java "-Dspring.profiles.active=local" -jar ./target/data-service-0.0.1-SNAPSHOT.jar
