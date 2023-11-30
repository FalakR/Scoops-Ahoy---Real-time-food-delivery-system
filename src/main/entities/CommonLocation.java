package entities;

import java.math.BigDecimal;

public class CommonLocation implements Location {
    private BigDecimal x;
    private BigDecimal y;

    public CommonLocation(BigDecimal x, BigDecimal y) {
        this.set(x, y);
    }

    public CommonLocation(double x, double y) {
        this.set(
                BigDecimal.valueOf(x),
                BigDecimal.valueOf(y)
        );
    }

    /**
     * @param x
     * @param y
     */
    @Override
    public void set(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return
     */
    @Override
    public BigDecimal getX() {
        return this.x;
    }

    /**
     * @return
     */
    @Override
    public BigDecimal getY() {
        return this.y;
    }
}
