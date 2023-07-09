package ReAssuredAPITesting;

import java.io.Serializable;

public class RectangleSerialization implements Serializable {

    private double height;
    private double width;

    public RectangleSerialization(double height,double width)
    {
        this.height = height;
        this.width = width;
    }

    public double area()
    {
        return height * width;
    }
}
