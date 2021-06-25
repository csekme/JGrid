package hu.swing.controls.jgrid.descriptor;

/**
 * Represents all metadata about a grid column
 */
public class GridColumnDescriptor {
    // column type
    private GridColumnType type;
    // column key
    private String key;
    // columns title
    private String title;
    // preferred with
    private int preferredWidth;
    // custom format string
    private String format;
    // column alignment
    private int alignment;
    // column is resizable
    private boolean resizable;

    public GridColumnDescriptor(String key, String title, int preferredWidth, GridColumnType type, String format, int alignment, boolean resizable) {
        this.key = key;
        this.title = title;
        this.preferredWidth = preferredWidth;
        this.type = type;
        this.format = format;
        this.alignment = alignment;
        this.resizable = resizable;
    }

    public GridColumnDescriptor(String key, String title, int preferredWidth, GridColumnType type) {
        this(key, title, preferredWidth, type, null, type.getDefaultAligment(), true);
        if (type.isFormatRequired()) {
            throw new IllegalArgumentException("Format required for column type \"" + type.getCode() + "\".");
        }
    }

    public GridColumnDescriptor(String key, String title, int preferredWidth, GridColumnType type, int alignment) {
        this(key, title, preferredWidth, type, null, alignment, true);
        if (type.isFormatRequired()) {
            throw new IllegalArgumentException("Format required for column type \"" + type.getCode() + "\".");
        }
    }

    public GridColumnDescriptor(String key, String title, int preferredWidth, GridColumnType type, String format) {
        this(key, title, preferredWidth, type, format, type.getDefaultAligment(), true);

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GridColumnType getType() {
        return type;
    }

    public void setType(GridColumnType type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getAlignment() {
        return alignment;
    }

    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
        this.preferredWidth = preferredWidth;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    @Override
    public String toString() {
        return "GridColumnDescriptor{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", format='" + format + '\'' +
                '}';
    }

}
