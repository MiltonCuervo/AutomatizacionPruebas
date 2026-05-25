package co.edu.udea.certificacion.advshop.modulocompra.models;

public class PaymentDetails {

    private final String method;
    private final String cardNumber;
    private final String cvv;
    private final String cardHolder;
    private final String expiryMonth;
    private final String expiryYear;
    private final String safePayUsername;
    private final String safePayPassword;

    private PaymentDetails(Builder builder) {
        this.method = builder.method;
        this.cardNumber = builder.cardNumber;
        this.cvv = builder.cvv;
        this.cardHolder = builder.cardHolder;
        this.expiryMonth = builder.expiryMonth;
        this.expiryYear = builder.expiryYear;
        this.safePayUsername = builder.safePayUsername;
        this.safePayPassword = builder.safePayPassword;
    }

    public String getMethod() { return method; }
    public String getCardNumber() { return cardNumber; }
    public String getCvv() { return cvv; }
    public String getCardHolder() { return cardHolder; }
    public String getExpiryMonth() { return expiryMonth; }
    public String getExpiryYear() { return expiryYear; }
    public String getSafePayUsername() { return safePayUsername; }
    public String getSafePayPassword() { return safePayPassword; }

    public static PaymentDetails masterCredit() {
        return new Builder()
                .method("Master Credit")
                .cardNumber("12345678901234")
                .cvv("123")
                .cardHolder("QA Buyer")
                .expiryMonth("12")
                .expiryYear("2027")
                .build();
    }

    public static PaymentDetails safePay() {
        return new Builder()
                .method("Safe Pay")
                .safePayUsername("CFSFe")
                .safePayPassword("SafePay@123")
                .build();
    }

    public static class Builder {
        private String method;
        private String cardNumber;
        private String cvv;
        private String cardHolder;
        private String expiryMonth;
        private String expiryYear;
        private String safePayUsername;
        private String safePayPassword;

        public Builder method(String method) { this.method = method; return this; }
        public Builder cardNumber(String cardNumber) { this.cardNumber = cardNumber; return this; }
        public Builder cvv(String cvv) { this.cvv = cvv; return this; }
        public Builder cardHolder(String cardHolder) { this.cardHolder = cardHolder; return this; }
        public Builder expiryMonth(String expiryMonth) { this.expiryMonth = expiryMonth; return this; }
        public Builder expiryYear(String expiryYear) { this.expiryYear = expiryYear; return this; }
        public Builder safePayUsername(String u) { this.safePayUsername = u; return this; }
        public Builder safePayPassword(String p) { this.safePayPassword = p; return this; }
        public PaymentDetails build() { return new PaymentDetails(this); }
    }
}