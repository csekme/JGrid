package hu.swing.controls.jgrid.samples;

import hu.swing.controls.jgrid.interfaces.GridColumn;

import java.util.Date;

/**
 *
 * @author BGy
 */
public class Person {

    @GridColumn(key = "NAME")
    private String name;

    @GridColumn(key = "BIRTH_DT")
    private Date birthDate;

    @GridColumn(key = "LIMIT")
    private int dailyLimit;

    @GridColumn(key = "CARD_EXP")
    private Date expiryDate;

    @GridColumn(key = "VIP")
    private boolean vip;

    private String cardNumber;

    @GridColumn(key = "CARD_NO")
    public String cardNumberFmt() {
        if (cardNumber == null){
            return null;
        }
        char delimiter = ' ';
        return cardNumber.replaceAll(".{4}(?!$)", "$0" + delimiter);
    }

    public Person(String name, Date birthDate, int dailyLimit, String cardNumber, Date expiryDate, boolean vip) {
        this.name = name;
        this.birthDate = birthDate;
        this.dailyLimit = dailyLimit;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", dailyLimit=" + dailyLimit +
                ", cardNumber=" + cardNumber +
                ", expiryDate=" + expiryDate +
                ", vip=" + vip +
                '}';
    }
}
