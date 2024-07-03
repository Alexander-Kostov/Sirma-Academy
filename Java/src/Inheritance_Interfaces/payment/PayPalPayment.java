package Inheritance_Interfaces.payment;

import java.util.regex.Pattern;

public class PayPalPayment implements PaymentMethod{
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }

    @Override
    public boolean validate() {
        return validateEmail(this.email) && validatePassword(this.password);
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        boolean hasNumber = false;
        boolean hasUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
        }
        return hasNumber && hasUppercase;
    }

}
