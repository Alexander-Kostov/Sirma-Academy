package Solid.Interface_Segregation.Player;

public class Main {
    public static void main(String[] args) {
        BasicPlayer basicPlayer = new BasicPlayer();
        basicPlayer.play();
        basicPlayer.pause();

        AdvancedPlayer advancedPlayer = new AdvancedPlayer();
        advancedPlayer.play();
        advancedPlayer.pause();
        advancedPlayer.next();
        advancedPlayer.previous();
        advancedPlayer.shuffle();
    }
}
