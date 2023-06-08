# Student Housing

Welcome to the Student Housing project! This is a fully functional student housing rental agency website implemented as a final project for the [CodeGorilla](https://codegorilla.nl/) Java Bootcamp.

## Group Members
- [StefanFB](https://github.com/StefanFB)
- [RudyVane](https://github.com/RudyVane)

## Technologies Used
- Back-end: Spring Boot, Spring Data, Hibernate, Lombok
- Front-end: Angular, Bootstrap, CSS
- Database: MySQL
- Development Environment: IntelliJ Community Edition

## Installation Requirements
Before running the project, please ensure that you have the following dependencies installed:

- Node.js
- Angular CLI
- Java SE11
- XAMPP (for the database server)
- IntelliJ Community Edition (or any other Java IDE)

## API Documentation
The back-end of this project is built as a RESTful API with the following endpoints:

- [x] `/register`: Allow users to create a new account
- [ ] `/login`: Allow users to log in with an existing account
- [x] `/account`: All data and actions related to a user
- [ ] `/account/edit`: Change password and other personal information
- [x] `/account/property`: Show information about properties, change, add, or delete them
- [x] `/account/advertisements`: Show info about advertisements, change, add, or delete them
- [x] `/properties`: All data and actions related to rentable properties
- [x] `/properties?<query>`: Show a specific subset of properties based on a query
- [x] `/properties/{id}`: Show a specific property by its ID
- [x] `/advertisements`: Allow users to search for roommates based on advertisements
- [ ] `/advertisements?<query>`: Show a specific subset of advertisements based on a query
- [ ] `/advertisements/{id}`: Show a specific advertisement by its ID
- [ ] `/stats?city=<cityname>`: Return statistics about rental costs and required deposit in a specific city

Please note that while some endpoints are fully implemented, not all endpoints have been completed at this time. Please refer to the project documentation for updates on the progress of the remaining endpoints.

## Work Distribution
During the development of this project, the work was distributed as follows:

- Stefan: Worked on the user and account code, both front-end and back-end.
- Rudy: Worked on the properties code, both front-end and back-end.

## Testing
We have implemented a JUnit test to check the database connection. Additional tests have not been implemented at this time.

## Data Source
For this project, we have used the dataset from Kaggle, which is a crawl of the Dutch website Kamernet. You can find the dataset [here](https://www.kaggle.com/datasets/juangesino/netherlands-rent-properties?select=properties.json).

## Getting Started
Please follow the steps below to set up and run the project:

1. Clone this repository to your local machine.
2. Set up the MySQL database using XAMPP or any other suitable method. Make sure the database configuration in the project is updated accordingly.
3. Create a database with the name 'student_housing'.
4. Start the back-end server by running the Spring Boot application.
5. Navigate to the `frontend` directory and run `npm install` to install the necessary dependencies.
6. Start the front-end server by running `ng serve` in the `frontend` directory.
7. Open your web browser and visit `http://localhost:4200` to access the Student Housing website.

Note: Remember to provide appropriate values for configuration files, such as database credentials and API endpoints, before running the project.

That's it! You should now have the Student Housing project up and running on your local machine.
