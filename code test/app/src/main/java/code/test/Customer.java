package code.test;

public class Customer {
    private int prospectNumber;
    private String customerName;
    private double totalLoan;
    private int loanPeriodYears;
    private double monthlyPayment;

    public Customer() {

    }

    public Customer(int prospectNumber, String customerName, double totalLoan, int loanPeriodYears,
            double monthlyPayment) {
        this.prospectNumber = prospectNumber;
        this.customerName = customerName;
        this.totalLoan = totalLoan;
        this.loanPeriodYears = loanPeriodYears;
        this.monthlyPayment = monthlyPayment;
    }

    public int getProspectNumber() {
        return prospectNumber;
    }

    public void setProspectNumber(int prospectNumber) {
        this.prospectNumber = prospectNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public int getLoanPeriodYears() {
        return loanPeriodYears;
    }

    public void setLoanPeriodYears(int loanPeriodYears) {
        this.loanPeriodYears = loanPeriodYears;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
