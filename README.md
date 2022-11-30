# Car Application 🚗

This app was created using Java/Spring Boot. It stores information about cars, such as brand, model, year, VIN number and registration number.

## Passwords
**H2** ->
```
username: admin
password: admin
```
**Spring Security** ->
```
username: admin
password: admin
```

## Instructions

1. Clone the repository to your local computer;

2. Open the terminal in the project folder;

3. Type the command described below into your terminal.


```bash
mvn spring-boot:run
```
**OR**

1. Clone the repository to your local computer;

2. Start the application from your IDE of choice.

---
**If the application is running on your machine, you can access it by:**
```
localhost:8080
```
## Endpoints

/cars/import - **[PUT]** | Import database contents as JSON (Deletes previous data)

/cars/add - **[PUT]** | Add a car by sending a JSON file

/cars/{brand} - **[GET]** -> (ex. - /cars/honda) | Find all cars based of the brand name.

/cars/export - **[GET]** | Export database contents as JSON

**SWAGGER**

/swagger-ui/index.html#/ | Access swagger from your browser, after launching the application.
## JSON type format
```json
{
    "brand": "BMW",
    "model": "328i",
    "year": 1997,
    "vin": "EAJHJGAH8218I",
    "regNum": "KO4321"
}
```




