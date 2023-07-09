package ReAssuredAPITesting;

import java.io.*;

public class MainRectangleSerializationAndDeserialization extends RectangleSerialization
{
    public MainRectangleSerializationAndDeserialization(double height, double width) {
        super(height, width);
    }

    public static void serializeToFile(Object classObject, String fileName) throws IOException
    {
            FileOutputStream fileStream = new FileOutputStream(fileName);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(classObject);
            objectStream.close();
            fileStream.close();
    }

    public static Object FileToObjectDeserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIStream = new FileInputStream(fileName);
        ObjectInputStream objectIStream = new ObjectInputStream(fileIStream);
        Object deserializeObject = objectIStream.readObject();
        objectIStream.close();
        fileIStream.close();
        return deserializeObject;
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        RectangleSerialization react = new RectangleSerialization(18,10);
        serializeToFile(react,"reactSerialized");


        RectangleSerialization deSerializedRect = (RectangleSerialization) FileToObjectDeserialize("reactSerialized");
        System.out.println("Rect area is " + deSerializedRect.area());
    }
}
