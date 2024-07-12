package Solid.Dependency_Inversion.Bulb;

public class Main {
    public static void main(String[] args) {
        Bulb bulb = new Bulb();
        LightSwitch lightSwitch = new LightSwitch(bulb);
        lightSwitch.press();
        lightSwitch.press();
    }
}
