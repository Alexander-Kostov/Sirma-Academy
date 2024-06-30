package Inheritance_Interfaces.payment;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreditCardPayment implements PaymentMethod{
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolder, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {
        if (!validateCardHolder(cardHolder)) {
            return false;
        }

        if (!validateCardNumber(cardNumber)) {
            System.out.println("Invalid card number!");
            System.out.println("Valid credit card number must: ");
            System.out.println("\tBe between 13 and 19 digits");
            System.out.println("\tPass the Luhn Algorithm");
            return false;
        }

        if (!validateCVV(cvv)) {
            System.out.println("Invalid CVV number.");
            System.out.println("CVV must:");
            System.out.println("\tContain 3 or 4 digits");
            System.out.println("\tContain only digits");
            return false;
        }

        if(!validateExpiryDate(expiryDate)) {
            System.out.println("Invalid expiry date");
            System.out.println("Expiry date must:");
            System.out.println("\tBe in the format MM/yy");
            System.out.println("\tNot be in the past");
            return false;
        }

        return true;
    }

    private boolean validateCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }
        return luhnCheck(cardNumber);
    }

    private boolean validateCardHolder(String cardHolder) {
        if (!isCardHolderNotNull(cardHolder)) {
            System.out.println("Card holder cannot be null");
            return false;
        }

        if (!cardHolderHasNameAndSurnameWithOneWhitespace(cardHolder)) {
            System.out.println("Card holder should consist of name and surname with a single whitespace between:");
            System.out.println("\tJohn Doe");
            return false;
        }
        return true;
    }

    private boolean isCardHolderNotNull(String cardHolder) {
        return cardHolder != null;
    }

    private boolean cardHolderHasNameAndSurnameWithOneWhitespace(String cardHolder) {
        return cardHolder.matches("^[a-zA-Z]+( [a-zA-Z]+)?$");
    }
    private boolean validateExpiryDate(String expiryDate) {
        if (expiryDate == null) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            LocalDate date = YearMonth.parse(expiryDate, formatter).atEndOfMonth(); // Parse directly as YearMonth
            return !date.isBefore(YearMonth.now().atEndOfMonth()); // Compare as YearMonth
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validateCVV(String cvv) {
        return cvv != null && (cvv.length() == 3 || cvv.length() == 4) && cvv.matches("\\d+");
    }
    private boolean luhnCheck(String cardNumber) {
        cardNumber = cardNumber.replaceAll("\\s+", "");
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}
