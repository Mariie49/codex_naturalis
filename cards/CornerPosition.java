package cards;

public enum CornerPosition {
	TOP_LEFT(-1, -1), TOP_RIGHT(-1, 1), BOTTOM_RIGHT(1, 1), BOTTOM_LEFT(1, -1);

    private final int rowOffset;
    private final int colOffset;

    CornerPosition(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }

    private int value;


    public int getValue() {
        return value;
    }
    public CornerPosition getOpposite() {
        switch (this) {
            case TOP_LEFT:
                return BOTTOM_RIGHT;
            case TOP_RIGHT:
                return BOTTOM_LEFT;
            case BOTTOM_RIGHT:
                return TOP_LEFT;
            case BOTTOM_LEFT:
                return TOP_RIGHT;
            default:
                return null; // Non dovrebbe mai accadere
        }
    }
    
}

