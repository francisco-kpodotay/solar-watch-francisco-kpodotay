<div align="center">
  <h1 align="center">Solar Watch API</h1>
  <p align="center">
    A Java Spring Boot API that provides sunrise and sunset times for a given city and date.</p>
</div>

## About The Project

The "Solar Watch" project is a Java Spring Boot API that returns the sunrise and sunset times for a specified city and date. This project demonstrates my skills in backend development, API creation, and implementation of basic security features like JWT and Spring Security.

Here's why:

- Provides accurate sunrise and sunset times based on city and date.
- Implements JWT and Spring Security for secure access.
- Easily integrates with other applications requiring solar data.

### Built With

- [![Spring Boot][SpringBoot]][SpringBoot-url]
- [![Java][Java]][Java-url]
- [![JWT][JWT]][JWT-url]
- [![Spring Security][SpringSecurity]][SpringSecurity-url]

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

Make sure you have Java and Maven installed on your machine.

### Installation

1. Build the project:
    ```sh
    ./mvnw clean install
    ```
2. Run the Spring Boot application:
    ```sh
    ./mvnw spring-boot:run
    ```

## Usage

1. The API will be accessible at `http://localhost:8080`.
2. To get the sunrise and sunset times, send a GET request to:
    ```sh
    http://localhost:8080/api/solar/current?city=London&date=2022-7-25
    ```
   Replace `London` with the name of the city and `2022-7-25` with the desired date in `YYYY-MM-DD` format.

## Contact
Kpodo-Tay Francisco - [LinkedIn](https://www.linkedin.com/in/francisco-kpodotay/) - kpodotay.francisco@gmail.com

Project Link: [https://github.com/francisco-kpodotay/solar-watch-francisco-kpodotay](https://github.com/francisco-kpodotay/solar-watch-francisco-kpodotay)

<!-- MARKDOWN LINKS & IMAGES -->
[SpringBoot]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[SpringBoot-url]: https://spring.io/projects/spring-boot
[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com/
[JWT]: https://img.shields.io/badge/JWT-00008B?style=for-the-badge&logo=jwt&logoColor=white
[JWT-url]: https://jwt.io/
[SpringSecurity]: https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white
[SpringSecurity-url]: https://spring.io/projects/spring-security
