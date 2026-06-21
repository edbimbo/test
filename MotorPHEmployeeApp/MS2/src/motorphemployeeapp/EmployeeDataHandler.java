/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

/**
 *
 * @author paopa
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class EmployeeDataHandler {

    private final String FILE_NAME = "employees.csv";

    public void loadEmployees(DefaultTableModel model) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                model.addRow(data);
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "CSV file not found.");
        }
    }

    public void saveEmployee(
            String employeeNumber,
            String employeeName,
            String ratePerDay,
            String daysWorked) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME, true));

            writer.write(
                    employeeNumber + ","
                    + employeeName + ","
                    + ratePerDay + ","
                    + daysWorked);

            writer.newLine();

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error saving employee.");
        }
    }

    public void updateEmployee(
            int rowIndex,
            String employeeNumber,
            String employeeName,
            String ratePerDay,
            String daysWorked) {

        try {

            ArrayList<String> lines =
                    new ArrayList<>();

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME));

            String line;

            while ((line = reader.readLine()) != null) {

                lines.add(line);
            }

            reader.close();

            lines.set(
                    rowIndex,
                    employeeNumber + ","
                    + employeeName + ","
                    + ratePerDay + ","
                    + daysWorked);

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME));

            for (String updatedLine : lines) {

                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error updating employee.");
        }
    }

    public void deleteEmployee(int rowIndex) {

        try {

            ArrayList<String> lines =
                    new ArrayList<>();

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME));

            String line;

            while ((line = reader.readLine()) != null) {

                lines.add(line);
            }

            reader.close();

            lines.remove(rowIndex);

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME));

            for (String updatedLine : lines) {

                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error deleting employee.");
        }
    }
}