package Solid.Interface_Segregation.Player;

public class AdvancedPlayer implements Playable, Shuffleable, Nextable{
    @Override
    public void next() {
        System.out.println("Next track...");
    }

    @Override
    public void previous() {
        System.out.println("Previous track...");
    }

    @Override
    public void play() {
        System.out.println("Playing...");
    }

    @Override
    public void pause() {
        System.out.println("Pausing...");
    }

    @Override
    public void shuffle() {
        System.out.println("Shuffling");
    }
}
