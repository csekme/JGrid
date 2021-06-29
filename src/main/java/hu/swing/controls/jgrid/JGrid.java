package hu.swing.controls.jgrid;

import hu.swing.controls.jgrid.descriptor.GridColumnDescriptor;
import hu.swing.controls.jgrid.descriptor.GridDescriptor;
import hu.swing.controls.jgrid.interfaces.JGridTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class JGrid extends JScrollPane {

    private Font defaultFont = new Font("Calibri",Font.BOLD, 12);
    private JTable table;
    private JGridTableModel model;
    private TableRowSorter tableRowSorter;

    /**
     * selected row in model
     */
    private Long dataIndex=null;
    /**
     * selected row in grid
     */
    private Long gridIndex=null;

    public JGrid(JGridTableModel model, GridDescriptor gd) {
        super();
        this.table = new JTable(this.model = model);
        model.setTable(table);
        table.setRowSorter(tableRowSorter = new TableRowSorter(model));
        table.setRowSelectionAllowed(true);
        table.getTableHeader().setFont(defaultFont);

        for(int i=0 ; i< gd.size(); ++i){
            GridColumnDescriptor d = gd.get(i);
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setCellRenderer(new GenericTableCellRenderer(d, defaultFont));
            col.setPreferredWidth(d.getPreferredWidth());
        }

        getViewport().add(table);

    }

    public JTable getTable(){
        return table;
    }

    public void setSelectionMode(int selectionModel){
        table.setSelectionMode(selectionModel);
    }

    public void setHeaderFont(Font font){
        table.getTableHeader().setFont(font);
    }

    public Font getHeaderFont(){
        return table.getTableHeader().getFont();
    }

    public Long getDataIndex() {
        return dataIndex;
    }

    public Long getGridIndex() {
        return gridIndex;
    }

}
