package hu.swing;

/**
 * Identify the type of data with Types
 * @author Csekme
 */
public enum Type {

    BIGINT(-5),
    BOOLEAN(16),
    CHAR(1),
    DATE(91),
    DECIMAL(3),
    DOUBLE(8),
    FLOAT(6),
    INTEGER(4),
    NULL(0),
    NUMERIC(2),
    SMALLINT(5),
    TIME(92),
    TIME_WITH_TIMEZONE(2013),
    TIMESTAMP(93),
    TIMESTAMP_WITH_TIMEZONE(2014),
    VARCHAR(12)
    ;

    private final int value;

    private Type(int value) {
        this.value = value;
    }

    /**
     * Constants that are used to identify generic SQL types, called JDBC types.
     * @return equivalent SQL type
     * @see java.sql.Types
     */
    public int toSqlType() {
        return value;
    }

    public Class<?> toJavaClass() {
        switch (this) {
            case BOOLEAN: return Boolean.class;
            case INTEGER: return Integer.class;
            default: return null;
        }
    }

}
