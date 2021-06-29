package hu.swing.controls.jgrid;

import hu.swing.controls.jgrid.interfaces.GridColumn;

import java.util.Date;

public class TestEntity {

    private int id;

    @GridColumn(key = "NAME")
    private String fullName;

    @GridColumn(key = "BIRTH_DT")
    Date birthDate;

    @GridColumn(key = "SALARY")
    Double annualSalary;

    @GridColumn(key = "CARD_NO")
    Long carNumber;

    @GridColumn(key = "EXPIRY")
    Date cardExpiry;

    @GridColumn(key = "ORDER_NO")
    int order;

    @GridColumn(key = "LOCKED")
    boolean locked;

}
