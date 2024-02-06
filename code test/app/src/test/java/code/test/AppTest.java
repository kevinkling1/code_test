package code.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class AppTest {

    @Test
    void testProcessCustomers_ValidData() {
        String validLine = "Alice,10000,5.0,3";

        try {
            Customer customer = App.processCustomers(validLine);

            assertEquals(1, customer.getProspectNumber());
            assertEquals("Alice", customer.getCustomerName());
            assertEquals(10000.0, customer.getTotalLoan());
            assertEquals(3, customer.getLoanPeriodYears());

        } catch (IOException e) {
            fail("IOException should not occur for valid data.");
        }
    }
}
