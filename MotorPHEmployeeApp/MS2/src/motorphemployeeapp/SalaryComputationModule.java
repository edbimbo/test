/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

public class SalaryComputationModule {

 public double computeGrossPay(double ratePerDay, int daysWorked) {
        return ratePerDay * daysWorked;
    }

    public double computeSSS(double grossPay) {
        return grossPay * 0.05;
    }

    public double computePhilHealth(double grossPay) {
        return grossPay * 0.03;
    }

    public double computePagIBIG() {
        return 100;
    }

    public double computeWithholdingTax(double grossPay) {
        return grossPay * 0.10;
    }

    public double computeDeductions(double grossPay) {
        return computeSSS(grossPay)
                + computePhilHealth(grossPay)
                + computePagIBIG()
                + computeWithholdingTax(grossPay);
    }

    public double computeNetPay(double grossPay) {
        return grossPay - computeDeductions(grossPay);
    }
}