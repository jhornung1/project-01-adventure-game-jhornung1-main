package adventure_game;
import adventure_game.items.*;

/**
 * The object class for each room that
 * makes up the map
 */
public class Room {
    private int roomID;

    private int southE;
    private int northE;
    private int eastE;
    private int westE;

    private String name;
    private String description;
    
    private Consumable item;
    private NPC enemy;

    /**
     * Constructor for the Room object
     * 
     * @param roomID the ID of the room
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(int roomID, String name, String description) {
        this.name = name;
        this.description = description;
        this.roomID = roomID;
    }

    /**
     * Gets the east exit value of the room
     * 
     * @return the east exit value of the room
     */
    public int getEastE() {
        return this.eastE;
    }

    /**
     * Gets the west exit value of the room
     * 
     * @return the west exit value of the room
     */
    public int getWestE() {
        return this.westE;
    }

    /**
     * Gets the north exit value of the room
     * 
     * @return the north exit value of the room
     */
    public int getNorthE() {
        return this.northE;
    }

    /**
     * Gets the south exit value of the room
     * 
     * @return the south exit value of the room
     */
    public int getSouthE() {
        return this.southE;
    }

    /**
     * Gets the name of the room
     * 
     * @return the name of the room
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the description of the room
     * 
     * @return the description of the room
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the Consumable within the room
     * 
     * @return the Consumable in the room
     */
    public Consumable getItem() {
        return this.item;
    }

    /**
     * Gets the enemy within the room
     * 
     * @return the Character (enemy) inside the room
     */
    public NPC getEnemy() {
        return this.enemy;
    }

    /**
     * Sets the south exit of the room
     * 
     * @param exit the value the exit is set to
     */
    public void setSouthE(int exit) {
        this.southE = exit;
    }

    /**
     * Sets the north exit of the room
     * 
     * @param exit the value the exit is set to
     */
    public void setNorthE(int exit) {
        this.northE = exit;
    }

        /**
     * Sets the east exit of the room
     * 
     * @param exit the value the exit is set to
     */
    public void setEastE(int exit) {
        this.eastE = exit;
    }

    /**
     * Sets the west exit of the room
     * 
     * @param exit the value the exit is set to
     */
    public void setWestE(int exit) {
        this.westE = exit;
    }

    /**
     * Sets the Consumable of the room
     * 
     * @param item the value the item is set to
     */
    public void setItems(Consumable item) {
        this.item = item;
    }

    /**
     * Sets the enemy of the room
     * 
     * @param enemy the Character (enemy)of the room
     */
    public void setEnemy(NPC enemy) {
        this.enemy = enemy;
    }

    /**
     * Checks if the room currently has an item
     * 
     * @return false if there is no item
     */
    public boolean hasItem() {
        if (this.item == null) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Checks if the room currently has an enemy
     * 
     * @return false if the room doesn't have an enemy
     */
    public boolean hasEnemy() {
        if (this.enemy == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
