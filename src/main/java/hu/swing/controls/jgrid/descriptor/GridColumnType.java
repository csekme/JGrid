package hu.swing.controls.jgrid.descriptor;

import javax.swing.*;

/**
 * Represents available column display types
 */
public enum GridColumnType {


    TEXT("TEXT", SwingConstants.LEFT, false),
    SHORTDATE("SHORTDATE", SwingConstants.LEFT, false),
    DATEFORMAT("DATEFORMAT", SwingConstants.LEFT, true),
    NUMERFORMAT("NUMERFORMAT", SwingConstants.RIGHT, true),
    INTEGER("INTEGER", SwingConstants.RIGHT, false),
    CURRENCY("CURRENCY", SwingConstants.RIGHT, false),
    BOOLEAN("BOOLEAN", SwingConstants.CENTER, false);

    /**
     * reference code
     */
    private String code;
    /**
     * display alignment
     */
    private int defaultAligment;
    /**
     * displayformat for text and numeric field
     */
    private boolean formatRequired;

    private GridColumnType(String code, int defaultAligment, boolean formatRequired) {
        this.code = code;
        this.defaultAligment = defaultAligment;
        this.formatRequired = formatRequired;
    }

    public String getCode() {
        return code;
    }

    public int getDefaultAligment() {
        return defaultAligment;
    }

    public boolean isFormatRequired() {
        return formatRequired;
    }

    @Override
    public String toString() {
        return "ColumnType{" + "code=" + code + '}';
    }

}
