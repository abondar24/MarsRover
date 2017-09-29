package org.abondar.experimental.marsrover;

class Point {
    private Integer x;

    private Integer y;

    private Integer maxX;

    private Integer maxY;


    Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }


    public void setMaxVals(Integer maxX, Integer maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }


    public void add(Point p) {
        x = setCoord(x, p.getX(), maxX);
        y = setCoord(y, p.getY(), maxY);
    }

    private Integer setCoord(Integer curCoord, Integer change, Integer max) {
        Integer newCoord = curCoord + change;
        if ((newCoord <= max) && (newCoord >= 0)) {
            return newCoord;
        } else {
            return curCoord;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (!x.equals(point.x)) return false;
        return y.equals(point.y);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                '}';
    }
}
