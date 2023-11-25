package entities;

class CommonCourier implements Courier {

    private final Double[] currentLocation;
    private final Double[] startLocation;
    private final Double[] endLocation;

    CommonCourier(Double[] currentLocation, Double[] startLocation, Double[] endLocation) {
        this.currentLocation = currentLocation;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    @Override
    public Double[] getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public Double[] getStartLocation() {
        return startLocation;
    }

    @Override
    public Double[] getEndLocation() {
        return endLocation;
    }
}
