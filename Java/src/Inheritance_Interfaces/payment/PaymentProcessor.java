package Inheritance_Interfaces.payment;

public class PaymentProcessor {

    public boolean processPayment(PaymentMethod paymentMethod) {
        if (paymentMethod.validate()) {
            System.out.println("Payment authorized using " + paymentMethod.getMethodName());
            return true;
        } else {
            System.out.println("Payment validation failed!");
            return false;
        }
    }
}
