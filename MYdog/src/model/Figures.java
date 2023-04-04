package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Figures sub-class of Toy class
 * @author Matthew McCusker
 */
public class Figures extends Toy {

    /**
     * class attribute
     */
    private char classification; // - variable char

    /**
     * Figures constructor that also takes classification argument
     * Complete with getter and setter for unique attributes
     * @author Matthew McCusker
     */
    public Figures(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge, char classification) {
        super(serialNumber, name, brand, price, availableCount, minimumAge);
        this.classification = classification;
    }

    // to get the values
    public char getClassification() {
        return classification;
    }

    //to set the values
    public void setClassification(char classification) {
        this.classification = classification;
    }

    //Overrides version of Toy's abstract toString() method.
    @Override
    public String toString() {
        // return used to return the type of method -> public String toString() -> here String is the type of toString
        // it will return the String as shown below...
        return "Serial Number: " + getSerialNumber() + "  " + // concatenation of string
                "Name: " + getName() + "  " +
                "Brand: " + getBrand() + "  " +
                "Price: " + getPrice() + "  " +
                "Available Count: " + getAvailableCount() + "  " +
                "Minimum Age: " + getMinimumAge() + "  " +
                "Classification: " + getClassification() + "  ";
    }

    //Overrides version of Toy's abstract save() method.
    @Override
    public void save(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, true); // file writer instance  // takes file name
        PrintWriter writer = new PrintWriter(fw); // PrintWriter instance takes filewriter instance
        // write method is used to write in the file
        writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getClassification()+"\n");
        //close the file...
        writer.close();
    }

}
