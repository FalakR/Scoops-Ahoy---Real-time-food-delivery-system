package entities;

import java.math.BigDecimal;

public interface Location {
    public void set(BigDecimal x, BigDecimal y);
    public BigDecimal getX();
    public BigDecimal getY();


}
