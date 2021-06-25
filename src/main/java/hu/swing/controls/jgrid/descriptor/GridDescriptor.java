package hu.swing.controls.jgrid.descriptor;

import java.util.ArrayList;

public class GridDescriptor extends ArrayList<GridColumnDescriptor> {
    // ha valaki a láncolt megoldást szereti
    public GridDescriptor put(GridColumnDescriptor item) {
        add(item);
        return this;
    }
}
