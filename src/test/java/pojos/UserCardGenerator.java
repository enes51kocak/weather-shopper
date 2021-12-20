package pojos;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

public class UserCardGenerator {

    private final String email;
    private final String cardNumber;
    private final String expireDate;
    private final String cvcNumber;
    private final String zipCode;

    private static final Faker faker = new Faker();

    public UserCardGenerator(){
        this.email = faker.internet().emailAddress();
        this.cardNumber = faker.finance().creditCard(CreditCardType.VISA);
        this.expireDate = "12/30"; //hard code will be removed
        this.cvcNumber = faker.number().digits(3);
        this.zipCode = faker.address().zipCode();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getCvcNumber() {
        return cvcNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getZipCode(){
        return zipCode;
    }

}
