package hu.swing.controls.jgrid;

import hu.swing.controls.jgrid.descriptor.GridColumnDescriptor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class GenericTableCellRenderer extends DefaultTableCellRenderer {

    /**
     * background color of highlighted rows
     */
    private final Color TABLE_SELECTION_BACKGROUND = new Color(130, 165, 223);
    /**
     * foreground color of highlighted rows
     */
    private final Color TABLE_SELECTION_TEX_COLOR = new Color(255, 255, 255);
    /**
     * background color of NOT highlighted row odd rows
     */
    private final Color TABLE_ALTERNATIVE_BACKGROUND =new Color(252, 250, 238);
    /**A
     * background color of NOT highlighted row even rowss
     */
    private final Color TABLE_BACKGROUND = new Color(255, 255, 255);
    /**
     * foreground color of NOT highlighted rows
     */
    private final Color TABLE_TEXT_COLOR = new Color(0, 0, 0);

    /**
     * structure specifies the look of columns
     */
    GridColumnDescriptor columnDescriptor;

    Font defaultFont;
    /**
     *  pre-created components for performance reason
     */
    JTextField textField;
    JCheckBox checkBox;
    DateFormat shortDateFormat;
    NumberFormat currencyFormat;
    DateFormat customDateFormat;
    NumberFormat customNumberFormat;

    public GenericTableCellRenderer(GridColumnDescriptor columnDescriptor, Font defaultFont ) {
        this.columnDescriptor = columnDescriptor;
        this.defaultFont = defaultFont;

        textField = new JTextField();
        textField.setFont(defaultFont);

        textField.setBorder(null);
        checkBox = new JCheckBox();
        shortDateFormat = DateFormat.getDateInstance( DateFormat.SHORT, Locale.getDefault());
        currencyFormat = NumberFormat.getInstance(Locale.getDefault());
        customDateFormat = new SimpleDateFormat(columnDescriptor.getFormat()==null ? "yyyy.MM.dd" : columnDescriptor.getFormat()); // TODO default date format
        customNumberFormat = new DecimalFormat(columnDescriptor.getFormat()==null ? "##" : columnDescriptor.getFormat()); // TODO default number format
    }

    private Component renderCompnent(Object value) {
        Component result=null;
        textField.setText("");
        //System.out.println("columnDescriptor.getType = " + columnDescriptor.getType().getCode());
        switch (columnDescriptor.getType()) {
            case TEXT:
                textField.setText((String)value);
                textField.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = textField;
                break;
            case BOOLEAN: checkBox.setSelected((Boolean)value);
                checkBox.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = checkBox;
                break;
            case INTEGER: textField.setText(String.valueOf(value));
                textField.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = textField;
                break;
            case CURRENCY:
                textField.setText(currencyFormat.format(value));
                textField.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = textField;
                break;
            case SHORTDATE:
                textField.setText(shortDateFormat.format(value));
                textField.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = textField;
                break;
            case DATEFORMAT:
                textField.setText(customDateFormat.format(value));
                textField.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = textField;
                break;
            case NUMERFORMAT:
                textField.setText(customNumberFormat.format(value));
                textField.setHorizontalAlignment(columnDescriptor.getAlignment());
                result = textField;
                break;
            default:
                result = textField;
                break;
        }
        return result;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = renderCompnent(value);
        String s = (component instanceof  JLabel ? ((JLabel)component).getText() : "?");
        //System.out.println(row + "," + column + ": class=" + component.getClass().getName() + " érték=" + s);

        Color background;
        Color foreground;
        if (isSelected ) {
            background = TABLE_SELECTION_BACKGROUND;
            foreground = TABLE_SELECTION_TEX_COLOR;
        }
        else {
            background = (row % 2 == 0) ? TABLE_BACKGROUND : TABLE_ALTERNATIVE_BACKGROUND;
            foreground = TABLE_TEXT_COLOR ;
        }
        component.setBackground(background);
        component.setForeground(foreground);


        return component;
    }
}
