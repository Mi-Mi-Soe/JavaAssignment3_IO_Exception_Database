package TestException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void divide() throws ArithmeticException {
        int num = 10 / 0;

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // Creating a connection to the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/languageschool", "root",
                    "Mysql_Connector");
            System.out.println("Connected");
            Statement st = conn.createStatement();
            String query = "INSERT INTO student (name,birth_date) VALUES (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "Kaung Kaung");
            preparedStmt.setString(2, "4.3.1999");
            preparedStmt.executeUpdate();
            String sqlStr = "select * from student";
            ResultSet rs = st.executeQuery(sqlStr);
            while (rs.next()) {
                System.out.println(rs.getInt("id")+" / "+rs.getString("name")+" / "+rs.getString("birth_date")+" / "+rs.getString("ph_no"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // IO Exception

        try { 
            FileOutputStream fout = new FileOutputStream("intFile.txt");
            String s = "Welcome to javaTpoint.";
            byte b[] = s.getBytes();// converting string into byte array
            fout.write(b);
            fout.close();
            FileInputStream fin = new FileInputStream("intFile.txt");
            int i = fin.read();

            while (i != -1) {
                System.out.print((char) i);

                // Reads next byte from the file
                i = fin.read();
            }
            fin.close();
            System.out.println();
        } catch (IOException e) {
            System.out.println("File not found.");
        }

        try {
            divide();
        } catch (ArithmeticException e) {
            System.out.println("Divide cann't be available.");
        } finally {
            System.out.println("Use of Throws keyword is finished");
        }

        // Checked exception
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number : ");

        int num = 0;
        try {
            num = sc.nextInt();
            throw new Exception();
        } catch (InputMismatchException e) {
            System.out.println("Enter number value!!!");
        } catch (Exception e) {
            System.out.println("Your number is " + num);
        }

        // Unchecked Exception
        String[] country = { "Myanmar", "Thai", "Paris", "US" };
        System.out.println(country[4]);
    }

}
