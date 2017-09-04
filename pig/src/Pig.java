import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

/*
 * The Pig game
 * See http://en.wikipedia.org/wiki/Pig_%28dice_game%29
 */
public class Pig {

    public static void main(String[] args) {
        new Pig().program();
    }

    // The only allowed instance variables (i.e. declared outside any method)
    // Accessible from any method
    final Scanner sc = new Scanner(in);
    final Random rand = new Random();

    void program() {
        //test();                 // <-------------- Uncomment run to tests!
        int winPts = 20;          // Points to win
        Player[] players;         // The players (array of Player objects)
        boolean aborted = false;  // Game aborted by player

        welcomeMsg(winPts);

        players = getPlayers();
        statusMsg(players);

        int playersTurn=0;
        // TODO Write game here!
        String input = "";
        boolean running=true;
        while(running) {
            out.print("Player is "+players[playersTurn].name+" >");
            input = sc.next();
            switch (input){
                case "r":
                   if(rollpig(players[playersTurn])==1){
                    playersTurn++;
                       if(playersTurn==players.length){
                           playersTurn=0;
                       }
                   }
                    if(win(players[playersTurn].totalPts+players[playersTurn].roundPts)){
                        running=false;
                    }
                   break;
                case "n":
                    players[playersTurn].totalPts+=players[playersTurn].roundPts;
                    players[playersTurn].roundPts=0;

                    playersTurn++;
                    if(playersTurn==players.length){
                        playersTurn=0;
                    }
                        statusMsg(players);
                    break;
                case "q": running=false; aborted=true; break;
                default: out.println("invalid character");break;
            }
        }

        if(aborted){out.println("Game aborted");}
        else{ out.println("Game over! Winner is player "+players[playersTurn].name +" with "+ (players[playersTurn].totalPts+players[playersTurn].roundPts)+" points");}
        // gameOverMsg
    }

    // ---- Game logic methods --------------

    // TODO Add methods
    int roll() {
        return (int)(Math.random() * 6 + 1);
    }

    void turn(String move,Player[] players) {

    /*    switch (move){
            case "r": rollpig(); break;
            case "n":  break;
            case "q":   break;
            default: out.println("");break;
        }
        */
    }
    int rollpig(Player player){
        int points=roll();
        out.println(points);
            if(points==1){
                player.roundPts=0;
                out.println(player.name+" got 1 and lost it all!!");
                return points;
            }
            player.roundPts+=points;
            out.println(player.name+" got "+points+" points. Running total: " + player.roundPts);
            return points;
    }
    boolean win(int points){
        if(points>=20){
            return true;
        }
        return false;
    }

    // ---- IO methods ------------------

    void welcomeMsg(int winPoints) {
        out.println("Welcome to PIG!");
        out.println("First player to get " + winPoints + " points will win!" );
        out.println("Commands are: r = roll , n = next, q = quit");
        out.println();
    }

    void statusMsg(Player[] players) {
        out.print("Points: ");
        for (Player p : players) {
            out.print(p.name + " = " + p.totalPts + " ");
        }
        out.println();
    }


    Player[] getPlayers() {
        out.print("How many players? > ");
        int nPlayers = sc.nextInt();
     /*   boolean isInt=true;
        while (isInt){
            isInt=false;
            try {
                nPlayers=sc.nextInt();
                sc.nextLine();
            }catch (InputMismatchException e){
                out.println("invalid character try again");
                isInt=true;

            }
        }
        */
        String names;
        sc.nextLine();  // Read away \n
        Player[] players = new Player[nPlayers];

        // TODO add input
        for(int i = 0; i<players.length; i++){
            out.println("Name for player " + (i + 1));
            names = sc.nextLine();
            players[i] = new Player();
            players[i].name=names;
        }

        //players[0]= new Player();
        //out.println("Test");
        //players[1]= new Player();
        return players;
    }

    // ---------- Class -------------
    // A class makes it possible to keep all data for a Player in one place
    // Use the class to create (instantiate) Player objects
    class Player {
        String name;
        int totalPts;    // Total points for all rounds, default 0
        int roundPts;    // Points for a single round, default 0

    }

    // ----- Testing -----------------
    void test() {

        // TODO Add your tests here

        exit(0);   // End program
    }
}



