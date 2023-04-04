package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Animals sub-class of Toy class
 * @author Matthew McCusker
 */
public class Animals extends Toy{

    /**
     * These are the attributes of the Animal class which is extended by Toy class
     * extend keyword  : It is used for the inheritance
     * Inheritance in Java is a mechanism in which one object acquires all the properties and behaviors of a parent object.
     * It is an important part of OOPs (Object Oriented programming system).
     */
    private String material;  // string - variable
    private char size; // this is the variable to hold the size of Animal Toy...

    /**
     * Animals constructor that also takes size and material arguments
     * Complete with getter and setter for unique attributes
     * @author Matthew McCusker
     */
    public Animals(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge, String material, char size) {
        super(serialNumber, name, brand, price, availableCount, minimumAge);
        this.material = material;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public char getSize() {
        return size;
    }
    public void setSize(char size) {
        this.size = size;
    }

    //Overrides version of Toy's abstract toString() method.
    @Override
    public String toString() {

        // return used to return the type of method -> public String toString() -> here String is the type of toString
        // it will return the String as shown below...
        return "Serial Number: " + getSerialNumber() + "  " +
                "Name: " + getName() + "  " +
                "Brand: " + getBrand() + "  " +
                "Price: " + getPrice() + "  " +
                "Available Count: " + getAvailableCount() + "  " +
                "Minimum Age: " + getMinimumAge() + "  " +
                "Material: " + getMaterial() + "  " +
                "Size: " + getSize() + "  ";

    }

    //Overrides version of Toy's abstract save() method.
    @Override
    public void save(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter writer = new PrintWriter(fw);
        writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getMaterial() + ";" + getSize()+"\n");
        writer.close();

    }

}
