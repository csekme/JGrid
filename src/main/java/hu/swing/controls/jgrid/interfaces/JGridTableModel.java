package hu.swing.controls.jgrid.interfaces;

import hu.swing.controls.jgrid.descriptor.GridDescriptor;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public interface JGridTableModel<E> extends TableModel {
    public void setTable(JTable table);
}
