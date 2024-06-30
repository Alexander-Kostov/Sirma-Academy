package Inheritance_Interfaces.payment;

public class PayPalPayment implements PaymentMethod{
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}
