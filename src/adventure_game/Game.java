package adventure_game;

/*
 * Project-01: Adventure Game
 * Name: Jacob Hornung
 */

import java.util.Scanner;

import adventure_game.items.HealingPotion;
import adventure_game.items.Spear;

import java.util.Random;

/**
 * Primary class of the game, and where the main method is ran in.
 * 
 * Iterates over each turn of the game, while also creating the characters,
 * scanner, and Random.
 */

public class Game {
    static Scanner in = new Scanner(System.in);
    public static Random rand = new Random();
    private Player player;
    
    /**
     * Main method for the Game, the place where it all starts
     * and ends
     * 
     * @param args
     */

    public static void main(String[] args){

        Game game = new Game();

        game.createPlayer();
        System.out.println(game.player.toString());

        NPC opponent = new NPC("Geoff", 200, 0, 10);
        System.out.println(opponent.toString());
        game.enterCombat(opponent);

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
        System.out.printf("%s and %s are in a brawl to the bitter end.\n", this.player.getName(), opponent.getName());
        while(true){
            this.player.takeTurn(opponent);
            if(!opponent.isAlive()){
                System.out.printf("%S is SLAIN!!\n",opponent.getName());
                break;
            }

            opponent.takeTurn(this.player);
            if(!this.player.isAlive()){
                System.out.printf("%S is SLAIN!!\n",this.player.getName());
                break;
            }
        }
    }
}
