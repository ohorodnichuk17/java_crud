package org.example;

import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        inputData();
//        simpleArray();
//        sortPerson();

        String userName = "root";
        String password = "";
        String host="localhost";
        String port="3306";
        String database="java_vpd111";
        String strConn = "jdbc:mariadb://"+host+":"+port+"/"+database;

//        createNewTable(strConn, userName, password);
//        insertCategory(strConn, userName, password);
//        selectCategory(strConn, userName, password);
//        updateCategory(strConn, userName, password);
//        deleteCategory(strConn, userName, password);
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("---Categories CRUD---");
            System.out.println("PRESS 1 to CREATE New Category");
            System.out.println("PRESS 2 to SELECT All Categories");
            System.out.println("PRESS 3 to UPDATE Category by Id");
            System.out.println("PRESS 4 to DELETE Category");
            System.out.println("PRESS 5 to EXIT");

            System.out.println("Enter your choice:");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertCategory(strConn, userName, password);
                    break;
                case 2:
                    selectCategory(strConn, userName, password);
                    break;
                case 3:
                    updateCategory(strConn, userName, password);
                    break;
                case 4:
                    deleteCategory(strConn, userName, password);
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void createNewTable(String strConn, String userName, String password) {
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS categories ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "description TEXT"
                    + ")";
            statement.execute(sql);
            statement.close();
            System.out.println("-----Таблицю 'categories' успішно створено-------");

        }catch(Exception ex) {
            System.out.println("Помилка підключення: "+ex.getMessage());
        }
    }

    private static void updateCategory(String strConn, String userName, String password) {
        Scanner input = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(strConn, userName, password)) {
            System.out.println("Enter the category ID to update:");
            int categoryId = input.nextInt();
            input.nextLine();

            System.out.println("Enter the new name for the category:");
            String newName = input.nextLine();

            System.out.println("Enter the new description for the category:");
            String newDescription = input.nextLine();

            String query = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, newDescription);
                preparedStatement.setInt(3, categoryId);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
                System.out.println("Category updated successfully.");
            }

        } catch (Exception e) {
            System.out.println("Error updating category: " + e.getMessage());
        }
    }


    private static void deleteCategory(String strConn, String userName, String password) {
        Scanner input = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(strConn, userName, password)) {
            System.out.println("Enter the category ID to delete:");
            int categoryId = input.nextInt();

            String query = "DELETE FROM categories WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);

                if (rowsAffected > 0) {
                    System.out.println("Category deleted successfully.");
                } else {
                    System.out.println("Category with ID " + categoryId + " not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error deleting category: " + e.getMessage());
        }
    }




    private static void selectCategory(String strConn, String userName, String password) {
        try (Connection conn = DriverManager.getConnection(strConn, userName, password)) {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM categories";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                System.out.println("Category ID: " + id);
                System.out.println("Category Name: " + name);
                System.out.println("Category Description: " + description);
                System.out.println("--------------------");
            }

            resultSet.close();
            System.out.println("Categories selected successfully.");

        } catch (Exception e) {
            System.out.println("Помилка виведення категорій: " + e.getMessage());
        }
    }


    private static void insertCategory(String strConn, String userName, String password) {
        Scanner input = new Scanner(System.in);
        CategoryCreate categoryCreate = new CategoryCreate();
        System.out.println("Вкажіть назву категорії:");
        categoryCreate.setName(input.nextLine());
        System.out.println("Вкажіть опис категорії:");
        categoryCreate.setDescription(input.nextLine());

        try(Connection conn = DriverManager.getConnection(strConn,userName,password)) {
            Statement statement = conn.createStatement();
            String insertQuery = "INSERT INTO categories (name, description) VALUES (?, ?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            // Set values for the placeholders
            preparedStatement.setString(1, categoryCreate.getName());
            preparedStatement.setString(2, categoryCreate.getDescription());
            // Execute the SQL query
            int rowsAffected = preparedStatement.executeUpdate();
            // Close the resources
            preparedStatement.close();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Category inserted successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка створення категорії: "+ex.getMessage());
        }
    }

    private static void sortPerson() {
        Person[] list = {
                new Person("Test", "Tester"),
                new Person("John", "Doe"),
                new Person("Angelina", "Smith"),
                new Person("Bill", "Gates"),
        };

        System.out.println("--Звичайний список--");
        for (var item : list) {
            System.out.println(item);
        }

//        Arrays.sort(list, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getLastName().compareTo(o2.getLastName());
//            }
//        });
        Arrays.sort(list);
        System.out.println("--Відсортований список--");
        for (var item : list) {
            System.out.println(item);
        }
    }

    private static void simpleArray() {
        int n = 10;
        int[] mas = new int[n];

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            mas[i] = getRandom(20, 50);
        }
        System.out.println("Array: ");
        for (var item: mas) {
            System.out.printf("%d\t", item);
        }
        System.out.println();

        System.out.println("Sorted array: ");
        Arrays.sort(mas);
        for (var item: mas) {
            System.out.printf("%d\t", item);
        }

        int count = 0;
        for (var item : mas)
        {
            if(item >= 0) {
                count++;
            }
        }
        System.out.println("\nКількість додатних чисел: " + count);
    }

    private static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max-min) + min;
    }

    private static void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name?");
        String name = scanner.nextLine();
        System.out.printf("Hello %s!\n", name);
    }
}