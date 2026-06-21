/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package motorphemployeeapp;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGUI extends JFrame {

    JLabel lblEmployeeNumber =
            new JLabel("Employee Number:");

    JLabel lblEmployeeName =
            new JLabel("Employee Name:");

    JLabel lblRatePerDay =
            new JLabel("Rate Per Day:");

    JLabel lblDaysWorked =
            new JLabel("Days Worked:");

    JTextField txtEmployeeNumber =
            new JTextField();

    JTextField txtEmployeeName =
            new JTextField();

    JTextField txtRatePerDay =
            new JTextField();

    JTextField txtDaysWorked =
            new JTextField();

    JButton btnAdd =
            new JButton("Add Employee");

    JButton btnUpdate =
            new JButton("Update Employee");

    JButton btnDelete =
            new JButton("Delete Employee");

    JButton btnClear =
            new JButton("Clear");

    JButton btnCompute =
            new JButton("Compute Salary");
    
    JButton btnSummary =
        new JButton("Generate Summary");

    JTable table;

    DefaultTableModel model;

    EmployeeDataHandler dataHandler =
            new EmployeeDataHandler();

    SalaryComputationModule salaryModule =
            new SalaryComputationModule();

    public MainGUI() {

        setTitle("MotorPH Employee App");

        setSize(800, 500);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

       JPanel formPanel =
        new JPanel(
                new GridLayout(
                        4, 2, 10, 10));
    

        formPanel.add(lblEmployeeNumber);
        formPanel.add(txtEmployeeNumber);

        formPanel.add(lblEmployeeName);
        formPanel.add(txtEmployeeName);

        formPanel.add(lblRatePerDay);
        formPanel.add(txtRatePerDay);

        formPanel.add(lblDaysWorked);
        formPanel.add(txtDaysWorked);

        JPanel buttonPanel =
        new JPanel();

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        model = new DefaultTableModel();

        model.addColumn("Employee Number");
        model.addColumn("Employee Name");
        model.addColumn("Rate Per Day");
        model.addColumn("Days Worked");

        table = new JTable(model);

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                txtEmployeeNumber.setText(
                        model.getValueAt(row, 0).toString());

                txtEmployeeName.setText(
                        model.getValueAt(row, 1).toString());

                txtRatePerDay.setText(
                        model.getValueAt(row, 2).toString());

                txtDaysWorked.setText(
                        model.getValueAt(row, 3).toString());
            }
        });

        JScrollPane scrollPane =
            new JScrollPane(table);

        JPanel bottomPanel =
            new JPanel();

        bottomPanel.add(btnCompute);
        bottomPanel.add(btnSummary);

        JPanel southPanel =
            new JPanel(
            new BorderLayout());

southPanel.add(
        buttonPanel,
        BorderLayout.NORTH);

southPanel.add(
        bottomPanel,
        BorderLayout.SOUTH);

        bottomPanel.add(btnCompute);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        loadTable();

        btnAdd.addActionListener(
                e -> addEmployee());

        btnUpdate.addActionListener(
                e -> updateEmployee());

        btnDelete.addActionListener(
                e -> deleteEmployee());

        btnClear.addActionListener(
                e -> clearFields());

        btnCompute.addActionListener(
                e -> computeSalary());
        
        btnSummary.addActionListener(
        e -> generateSummary());

        setVisible(true);
    }

    public void addEmployee() {

        String employeeNumber =
                txtEmployeeNumber.getText().trim();

        String employeeName =
                txtEmployeeName.getText().trim();

        String ratePerDay =
                txtRatePerDay.getText().trim();

        String daysWorked =
                txtDaysWorked.getText().trim();

       if (employeeNumber.isEmpty()
        || employeeName.isEmpty()
        || ratePerDay.isEmpty()
        || daysWorked.isEmpty()) {

    JOptionPane.showMessageDialog(
            this,
            "Fill in all the details.");

    return;
}

if (!employeeName.matches("[a-zA-Z ]+")) {

    JOptionPane.showMessageDialog(
            this,
            "Employee Name should contain letters only.");

    return;
}

try {

    Integer.parseInt(employeeNumber);

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
            this,
            "Employee Number must be numeric.");

    return;
}

try {

    Double.parseDouble(ratePerDay);

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
            this,
            "Rate Per Day must be numeric.");

    return;
}

try {

    Integer.parseInt(daysWorked);

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
            this,
            "Days Worked must be numeric.");

    return;
}
        dataHandler.saveEmployee(
                employeeNumber,
                employeeName,
                ratePerDay,
                daysWorked);

        loadTable();

        clearFields();

        JOptionPane.showMessageDialog(
                this,
                "Employee added successfully!");
    }

    public void updateEmployee() {

        int row = table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an employee to update.");

            return;
        }

        dataHandler.updateEmployee(
                row,
                txtEmployeeNumber.getText(),
                txtEmployeeName.getText(),
                txtRatePerDay.getText(),
                txtDaysWorked.getText());

        loadTable();

        JOptionPane.showMessageDialog(
                this,
                "Employee updated successfully!");
    }

    public void deleteEmployee() {

        int row = table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an employee to delete.");

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected employee?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            dataHandler.deleteEmployee(row);

            loadTable();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee deleted successfully!");
        }
    }

    public void computeSalary() {

        try {

            double ratePerDay =
                    Double.parseDouble(
                            txtRatePerDay.getText());

            int daysWorked =
                    Integer.parseInt(
                            txtDaysWorked.getText());

            double grossPay =
                    salaryModule.computeGrossPay(
                            ratePerDay,
                            daysWorked);

            double deductions =
                    salaryModule.computeDeductions(
                            grossPay);

            double netPay =
                    salaryModule.computeNetPay(
                            grossPay);

            JOptionPane.showMessageDialog(
                    this,
                    "Gross Pay: " + grossPay
                    + "\nTotal Deductions: " + deductions
                    + "\nNet Pay: " + netPay);
        }

        catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers.");
        }
    }

    public void loadTable() {

        model.setRowCount(0);

        dataHandler.loadEmployees(model);
    }

    public void clearFields() {

        txtEmployeeNumber.setText("");
        txtEmployeeName.setText("");
        txtRatePerDay.setText("");
        txtDaysWorked.setText("");
    }
    
    public void generateSummary() {

    if (table.getRowCount() == 0) {

        JOptionPane.showMessageDialog(
                this,
                "No employee data available.");

        return;
    }

    int totalEmployees =
            table.getRowCount();

    double totalGrossPay = 0;

    double totalDeductions = 0;

    double totalNetPay = 0;

    for (int i = 0; i < table.getRowCount(); i++) {

        double ratePerDay =
                Double.parseDouble(
                        table.getValueAt(i, 2).toString());

        int daysWorked =
                Integer.parseInt(
                        table.getValueAt(i, 3).toString());

        double grossPay =
                salaryModule.computeGrossPay(
                        ratePerDay,
                        daysWorked);

        double deductions =
                salaryModule.computeDeductions(
                        grossPay);

        double netPay =
                salaryModule.computeNetPay(
                        grossPay);

        totalGrossPay += grossPay;

        totalDeductions += deductions;

        totalNetPay += netPay;
    }

    double averageNetPay =
            totalNetPay / totalEmployees;

    JOptionPane.showMessageDialog(

            this,

            "PAYROLL SUMMARY\n\n"

            + "Total Employees: "
            + totalEmployees

            + "\n\nTotal Gross Pay: "
            + totalGrossPay

            + "\n\nTotal Deductions: "
            + totalDeductions

            + "\n\nAverage Net Pay: "
            + averageNetPay
    );
}

    public static void main(String[] args) {

        new MainGUI();
    }
}