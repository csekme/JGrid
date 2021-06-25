package hu.swing.controls.jgrid;

import hu.swing.controls.jgrid.descriptor.GridColumnDescriptor;
import hu.swing.controls.jgrid.descriptor.GridDescriptor;
import hu.swing.controls.jgrid.interfaces.GridColumn;
import hu.swing.controls.jgrid.interfaces.JGridTableModel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JGridEntityModel<E> implements JGridTableModel {

    private ArrayList<E> data;
    private JTable table;
    private GridDescriptor descriptor = null;
    E currentRow=null;

    public JGridEntityModel(ArrayList<E> data, GridDescriptor descriptor) {
        this.data = data;
        this.descriptor = descriptor;
    }

    @Override
    public void setTable(JTable table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return descriptor.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return descriptor.get(columnIndex).getTitle();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz=null;
        GridColumnDescriptor gcd = descriptor.get(columnIndex);
        try {
            // search in annotated fields
            Field fld = getAnnotatedField(currentRow.getClass(),gcd.getKey());
            if( fld!=null ){
                clazz = fld.getClass();
            }
            else {
                // search in annotated methods
                Method m = getAnnotatedMethod(currentRow.getClass(), gcd.getKey());
                if(m!=null){
                    clazz = m.getReturnType();
                } else {
                    clazz = java.lang.String.class;
                }
            }

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JGridEntityModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clazz;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        boolean found = false;
        currentRow = getCurrentRow(rowIndex);

        GridColumnDescriptor gcd = descriptor.get( table.convertColumnIndexToModel(columnIndex) );

        try {
            // search in annotated fields
            Field fld = getAnnotatedField(currentRow.getClass(),gcd.getKey());
            if( fld!=null ){
                result = fld.get(currentRow);
            }
            else {
                // search in annotated methods
                Method m = getAnnotatedMethod(currentRow.getClass(), gcd.getKey());
                if(m!=null){
                    result = m.invoke(currentRow, (Object[]) null);
                } else {
                    throw new IllegalArgumentException("No field or method found with @GridColumn( key= \""+gcd.getKey()+"\") annotation." );
                }
            }

        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(JGridEntityModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public E getCurrentRow(int row){
        return data.get(row);
    }

    private Field getAnnotatedField(Class<?> clazz, String key){
        Field result=null;
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent((Class<? extends Annotation>)GridColumn.class)) {
                GridColumn c = field.getAnnotation(GridColumn.class);
                if (c.key().equals(key)) {
                    result = field;
                }
            }
        }
        return result;
    }

    private Method getAnnotatedMethod(Class<?> clazz, String key){
        Method result = null;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent((Class<? extends Annotation>) GridColumn.class)) {
                GridColumn c = (GridColumn) method.getAnnotation(GridColumn.class);
                if (c.key().equals(key)) {
                    if (method.getParameterCount() == 0) {
                        //System.out.println("method: " + method.getName() + ", " + method.getReturnType().getName());
                        if (!method.getReturnType().equals(Void.TYPE)) {
                            result = method;
                        } else {
                            throw new IllegalArgumentException("Method annotated with @GridColumn( key=\""+key+"\") cannot have void return type." );
                        }
                    } else {
                        throw new IllegalArgumentException("Method annotated with @GridColumn( key=\""+key+"\") cannot have parameters.");
                    }
                }
            }
        }
        return result;
    }

}
