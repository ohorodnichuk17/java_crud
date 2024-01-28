# Java Work Program

## Overview

This Java program demonstrates basic CRUD operations using JDBC for interacting with a MariaDB database. It includes functionality for creating, reading, updating, and deleting categories. Additionally, it features sorting an array of objects and working with a regular array of numbers.

## Project Structure

- Main.java: Main class with a menu for CRUD operations, sorting, and array manipulation.
- CategoryCreate.java: Class representing an object for creating a category. Utilizes Lombok annotations.
- Person.java: Class representing a person object. Implements Comparable for sorting by last name and first name. Utilizes Lombok annotations.
- pom.xml: Maven configuration file specifying dependencies for Lombok and MariaDB JDBC.
- 
## Usage

- Dependency Installation: Use Maven to install dependencies specified in the pom.xml file.
- Running the Program: In Main.java, uncomment and call the relevant methods to demonstrate different functionalities.
- Database Interaction: Uncomment and call the methods for creating, reading, updating, or deleting categories. Ensure proper MariaDB database configuration.
  
## CRUD Operations

- Create New Category: createNewTable()
- Select All Categories: selectCategory()
- Update Category by ID: updateCategory()
- Delete Category: deleteCategory()
- Sorting and Array Manipulation

Sort Persons: sortPerson()
Simple Array Operations: simpleArray()

## Notes

Database Configuration:
Ensure MariaDB is installed.
Create the java_vpd111 database before running the program.
Verify database connection settings in Main.java.
Customization:
Adjust the project configuration in pom.xml as needed.
Customize the code and methods based on specific requirements.
Exiting the Program:
Choose option 5 in the menu to exit the program.
