package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * BoardGames sub-class of Toy class
 * @author Matthew McCusker
 */
public class BoardGames extends Toy{

    /**
     * class attributes to hold data...
     */
    private String numOfPlayers; // String - variable
    private List<String> designers; // arraylist string type...

    /**
     * BoardGames constructor that also takes numOfPlayers and designers arguments
     * Complete with getter and setter for unique attributes
     * @author Matthew McCusker
     */
    public BoardGames(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge,
                      String numOfPlayers, List<String> designers) {
        super(serialNumber, name, brand, price, availableCount, minimumAge);
        this.numOfPlayers = numOfPlayers;
        this.designers = designers;
    }

    /**
     * getter and setters to get and set the values
     * @return
     */
    public String getNumOfPlayers() {
        return numOfPlayers;
    }
    public void setNumOfPlayers(String numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }
    public List<String> getDesigners() {
        return designers;
    }
    public void setDesigners(List<String> designers) {
        this.designers = designers;
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
                "Number of Players: " + getNumOfPlayers() + "  " +
                "Designers: " + getDesigners().toString().replaceAll("\\[","").replaceAll("\\]","") + "";
    }

    //Overrides version of Toy's abstract save() method.
    @Override
    public void save(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter writer = new PrintWriter(fw);
        writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getNumOfPlayers() + ";" +getDesigners().toString().replaceAll("\\[","").replaceAll("\\]","")+"\n");
        writer.close();

    }

}
