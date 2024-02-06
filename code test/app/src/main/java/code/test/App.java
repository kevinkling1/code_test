package code.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the file path as a command-line argument.");
            return;
        }

        // Set the file.encoding system property to UTF-8
        System.setProperty("file.encoding", "UTF-8");

        String filePath = args[0];

        try {
            processCustomers(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Process customers from a file
    static void processCustomers(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {

            String line;
            int prospectNumber = 1;

            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] customerData = splitCSV(line);

                // Skip empty or error lines
                if (customerData.length == 0) {
                    continue;
                }

                // Extract customer data
                String customerName;
                double totalLoan;
                double yearlyInterestRate;
                int loanPeriodYears;

                try {
                    customerName = customerData[0].trim();
                    totalLoan = Double.parseDouble(customerData[1].trim());
                    yearlyInterestRate = Double.parseDouble(customerData[2].trim());
                    loanPeriodYears = Integer.parseInt(customerData[3].trim());
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    continue; // Skip processing for lines with errors
                }

                // Calculate monthly payment
                double monthlyInterestRate = yearlyInterestRate / (12 * 100);
                int numberOfPayments = loanPeriodYears * 12;

                double factor = 1.0;
                for (int i = 0; i < numberOfPayments; i++) {
                    factor *= (1 + monthlyInterestRate);
                }

                double numerator = totalLoan * monthlyInterestRate * factor;
                double denominator = factor - 1;

                double monthlyPayment = numerator / denominator;

                // Print customer details
                printCustomerDetails(prospectNumber, customerName, totalLoan, loanPeriodYears, monthlyPayment);

                prospectNumber++;
            }
        }
    }

    // Splits CSV line into tokens
    static String[] splitCSV(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == ',' && !inQuotes) {
                tokens.add(token.toString().trim());
                token.setLength(0);
            } else if (c == '"') {
                inQuotes = !inQuotes;
            } else {
                token.append(c);
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString().trim());
        }

        return tokens.toArray(new String[0]);
    }

    // Print customer details
    static void printCustomerDetails(int prospectNumber, String customerName, double totalLoan,
            int loanPeriodYears, double monthlyPayment) {

        // Print customer details in a formatted form
        System.out.println(
                "********************************************************************************************************");
        System.out.printf(
                "Prospect %d: %s wants to borrow %.2f EUR for a period of %d years and pay %f EUR each month%n",
                prospectNumber, customerName, totalLoan, loanPeriodYears, monthlyPayment);
        System.out.println(
                "*******************************************************************************************************");
    }
}
