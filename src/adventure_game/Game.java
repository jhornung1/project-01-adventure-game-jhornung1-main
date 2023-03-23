package adventure_game;

/*
 * Project-01: Adventure Game
 * Name: Jacob Hornung
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import adventure_game.items.*;

/**
 * Primary class of the game, and where the main method is ran in.
 * 
 * Iterates over each turn of the game, while also creating the characters,
 * scanner, and Random.
 */

public class Game {
    static Scanner in = new Scanner(System.in);
    
    /**
     * The random variable used throughout the game
     */
    public static Random rand = new Random();
    private static Player player;
    private static Room currentRoom;
    private static Map<Integer, Room> map;
    
    /**
     * Main method for the Game, the place where it all starts
     * and ends
     * 
     * @param args I don't really know what this means
     * @throws FileNotFoundException Throws the exception if there isn't a file
     */

    public static void main(String[] args) throws FileNotFoundException {

        Game game = new Game();

        File file = new File("C:/Users/jacob/Downloads/project-01-adventure-game-jhornung1-main/src/maps/the-stilts.txt");

        map = readMapFile(file);
        game.setItemsM();
        game.setEnemiesM();
        currentRoom = map.get(0);

        game.createPlayer();

        while (true) {
            System.out.print("\nYou are in the " + currentRoom.getName());
            System.out.print("\n" + currentRoom.getDescription());
            if (currentRoom.hasEnemy()) {
                game.enterCombat(currentRoom.getEnemy());
                if (player.getHealth() == 0) {
                    System.out.println("You lose.");
                    break;
                } else {
                    currentRoom.setEnemy(null);
                }
            }
            if (currentRoom.hasItem()) {
                System.out.print("\nDo you wish to pick up " + currentRoom.getItem().getClass().getSimpleName() + "? (Type Y or N) ");
                String selecto = in.next();
                if (selecto.equals("Y") || selecto.equals("y")) {
                    player.obtain(currentRoom.getItem());
                    currentRoom.setItems(null);
                }
            }
            if (player.hasPortkey()) {
                System.out.println("Congradulations! You have found the portkey! You won!");
                break;
            }
            int plop = 1;
            while (plop == 1) {
                System.out.print("\nExits: ");
                List<String> exitNames = new ArrayList<>();
                if (currentRoom.getEastE() != -1) exitNames.add("East");
                if (currentRoom.getNorthE() != -1) exitNames.add("North");
                if (currentRoom.getWestE() != -1) exitNames.add("West");
                if (currentRoom.getSouthE() != -1) exitNames.add("South");
                System.out.println(String.join(", ", exitNames));
                System.out.print("Enter a direction to move (single letter): ");
                String input = in.nextLine().trim().toLowerCase();
                if (input.equals("e") && currentRoom.getEastE() != -1) {
                    currentRoom = map.get(currentRoom.getEastE());
                    plop =2;
                } else if (input.equals("n") && currentRoom.getNorthE() != -1) {
                    currentRoom = map.get(currentRoom.getNorthE());
                    plop =2;
                } else if (input.equals("w") && currentRoom.getWestE() != -1) {
                    currentRoom = map.get(currentRoom.getWestE());
                    plop =2;
                } else if (input.equals("s") && currentRoom.getSouthE() != -1) {
                    currentRoom = map.get(currentRoom.getSouthE());
                    plop =2;
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }
        in.close();
    }

    /**
     * Blank Constructor for the game, purely to have the Scanner,
     * Random, and Player.
     */
    public Game() {
        
    }

    /**
     * Creates the Player character by asking the user to select a name, and to
     * invest a total of 20 stat points into the user's selected stats.
     */
    public void createPlayer(){
        int startHealth;
        int startDamage;
        int startMana;
        System.out.print("What is your player's name? ");
        String name = in.next();
        System.out.println("\n\nYou will start off with 20 skill points to invest into 3 different traits, Health, Mana, and Base Damage.");
        System.out.println("Each point in Health will increase your starting health by ten points, every point in mana will increase your base mana by 3, and every point in damage will increase you damage by one point.\n");
        while (1 > 0) {
            System.out.print("\nHow many points do you want to invest into your starting Health? ");
            startHealth = in.nextInt();
            System.out.print("\nHow many points do you want to invest into your starting Mana? ");
            startMana = in.nextInt();
            System.out.print("\nHow many points do y want to invest into your starting base Damage? ");
            startDamage = in.nextInt();
            if (startHealth + startMana + startDamage == 20) {
                System.out.printf("\nYou have put %d points into starting health, %d points into starting mana, and %d points into starting damage. Is that correct? (Press Y for yes, and N for no) ", startHealth, startHealth, startMana);
                String selecto = in.next();
                if (selecto.equals("Y")) {
                    break;
                }
            } else if (startHealth + startMana + startDamage < 20) {
                System.out.printf("\nYou have not put all your possible starting points into your stats. Do you wish to continue? (Press Y for yes, N for no) ");
                String selecto = in.next();
                if (selecto.equals("Y")) {
                    break;
                }
            } else {
                System.out.print("\nYou have invested more than 20 points into your stats. Try again\n");
            }
        }

        player = new Player(name, startHealth * 10, startMana * 3, startDamage);
        player.obtain(new HealingPotion());
        player.obtain(new Spear());
    }

    /**
     * Iterates one turn each (player and opponent), until
     * one of them isn't alive
     * 
     * @param opponent the opposing Character the Player is
     * against.
     */
    public void enterCombat(NPC opponent){
        System.out.printf("\n%s and %s are in a brawl to the bitter end.\n", Game.player.getName(), opponent.getName());
        while(true){
            Game.player.takeTurn(opponent);
            if(!opponent.isAlive()){
                System.out.printf("%S is SLAIN!!\n",opponent.getName());
                break;
            }

            opponent.takeTurn(Game.player);
            if(!Game.player.isAlive()){
                System.out.printf("%S is SLAIN!!\n",Game.player.getName());
                break;
            }
        }
    }

    /**
     * A program that reads through a file to generate a map. 
     * 
     * @param baseFile the name of the file that is being read
     * @return the room dictionary that acts as the level map
     * @throws FileNotFoundException Since this uses a Scanner (and requires a Scanner to not colapse)
     * if there isn't a file, it just throws and doesn't break the entire code
     */
    public static Map<Integer, Room> readMapFile(File baseFile) throws FileNotFoundException {
        Scanner file = new Scanner(baseFile);
        Map<Integer, Room> map = new HashMap<>();

        int numRooms = 0;

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
        
            if (line.startsWith("#")) {
                continue; // Ignore comments
            }
        
            // Read in number of rooms
            if (numRooms == 0) {
                numRooms = Integer.parseInt(line);
                break; // Skip to next line
            }
    
        }
        
        for (int i = 0; i < numRooms; i++) {
            String[] roomInfo = file.nextLine().trim().split(":");
            int roomId = Integer.parseInt(roomInfo[0].trim());
            String name = roomInfo[1].trim();
            String description = roomInfo[2].trim();
            Room room = new Room(roomId, name, description);
            map.put(roomId, room);
        }

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (line.startsWith("#")) {
                continue; // Ignore comments
            }

            String[] connectionInfo = line.split(":");
            int roomId = Integer.parseInt(connectionInfo[0].trim());
            Room room = map.get(roomId);
            int eastExit = Integer.parseInt(connectionInfo[1].trim());
            int northExit = Integer.parseInt(connectionInfo[2].trim());
            int westExit = Integer.parseInt(connectionInfo[3].trim());
            int southExit = Integer.parseInt(connectionInfo[4].trim());
            room.setEastE(eastExit);
            room.setNorthE(northExit);
            room.setWestE(westExit);
            room.setSouthE(southExit);
        }

        file.close();
        return map;
    }

    /**
     * Iterates over each room on the map and randomly
     * adds an item (or no item) to each one. It also
     * randomly gives one of the rooms the portkey.
     */
    public void setItemsM() {
        for (Room room : map.values()) {
            int randomV = rand.nextInt(5) + 1;
            if (randomV == 1) {
                if (room != null) {
                    room.setItems(new HealingPotion());
                }
            }
            else if (randomV == 2) {
                if (room != null) {
                    room.setItems(new Spear());
                }
            }
        }
        int portKeyRoom = rand.nextInt(map.size());
        if (map.get(portKeyRoom) != null) {
            map.get(portKeyRoom).setItems(new Portkey());
        }
    }

    /**
     * Iterates over each room on the map and randomly
     * gives the room and enemy (or no enemy).
     */
    public void setEnemiesM() {
        for (Room rooms : map.values()) {
            int randomV = rand.nextInt(5) + 1;
            if (randomV == 1) {
                rooms.setEnemy(new NPC("Geoff", 30, 0, 3));
            }
            else if (randomV == 2) {
                rooms.setEnemy(new NPC("Billy", 15, 20, 4));
            }
            else if (randomV == 3) {
                rooms.setEnemy(new NPC("Jimm", 20, 5, 5));
            }
        }
    }
}
