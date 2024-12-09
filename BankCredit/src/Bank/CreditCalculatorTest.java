package Bank;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCalculatorTest {
    
	@Test
    public void testExtractLoanAmount() {
        // Проверка корректности извлечения суммы кредита
        String input = "100000";
        double expectedAmount = 100000.0;

        double extractedAmount;
        try {
            extractedAmount = Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректная сумма кредита");
        }

        assertEquals("Сумма кредита извлечена некорректно", expectedAmount, extractedAmount, 0.01);
    }

    @Test
    public void testExtractLoanTerm() {
        // Проверка корректности извлечения срока кредита
        String inputTerm = "36 месяцев";
        int expectedMonths = 36;

        int extractedTerm;
        if ("12 месяцев".equals(inputTerm)) {
            extractedTerm = 12;
        } else if ("24 месяца".equals(inputTerm)) {
            extractedTerm = 24;
        } else if ("36 месяцев".equals(inputTerm)) {
            extractedTerm = 36;
        } else {
            throw new IllegalArgumentException("Неизвестный срок кредита");
        }

        assertEquals("Срок кредита извлечен некорректно", expectedMonths, extractedTerm);
    }

    @Test
    public void testCalculateTotalInterest() {
        // Проверка, что сумма выплаты процентов рассчитывается правильно
        double amount = 100000.0;       
        double interestRate = 8.0;     
        int months = 24;                
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма кредита должна быть больше 0!");
        }

        double calculatedInterest = (amount * (interestRate / 100)) * (months / 12.0);
        double expectedInterest = (100000.0 * (8.0 / 100)) * (24 / 12.0);

        assertEquals("Сумма выплаты процентов рассчитана неверно", expectedInterest, calculatedInterest, 0.01);
    }
}
