package model;
import java.io.IOException;

/**
 * Toy super-class that gets and sets shared attributes
 * @author Matthew McCusker
 */
public abstract class Toy {

    /**
     * attributes / variables
     */
    private String serialNumber; // takes string value - variable
    private String name; // takes string value - variable
    private String brand; // takes string value - variable
    private float price; // takes float value - variable
    private int availableCount; // takes int value - variable
    private int minimumAge; // takes int value - variable

    /**
     * Toy constructor that sets shared attributes.
     * Complete with getter and setter for shared attributes
     * @author Matthew McCusker
     */
    public Toy(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge) {
        // parameterized constructor
        // setting the all values...
        this.serialNumber = serialNumber;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.availableCount = availableCount;
        this.minimumAge = minimumAge;
    }

    // getter to get the serialNumber
    public String getSerialNumber() {
        return serialNumber;
    }
    //setter to set the serialNumber
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    // getter to get the name
    public String getName() {
        return name;
    }

    //setter to set the name - similar for others setters
    public void setName(String name) {
        this.name = name;
    }
    // getter to get the brand
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    // getter to get the price
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    // getter to get the available counts
    public int getAvailableCount() {
        return availableCount;
    }
    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    // getter to get the min age from variable
    public int getMinimumAge() {
        return minimumAge;
    }
    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    //Abstract toString method that forces sub-classes to have a way to print the toy.
    public abstract String toString();

    //Abstract save method that forces sub-classes to have a way to save the toy.
    public abstract void save(String filename) throws IOException;

}
