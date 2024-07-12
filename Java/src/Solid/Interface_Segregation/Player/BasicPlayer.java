package Solid.Interface_Segregation.Player;

public class BasicPlayer implements Playable{
    @Override
    public void play() {
        System.out.println("Playing...");
    }

    @Override
    public void pause() {
        System.out.println("Pausing...");
    }
}
