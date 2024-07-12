package Solid.Dependency_Inversion.Bulb;

public class LightSwitch implements Switch {
    private Switchable client;
    private boolean on;

    public LightSwitch(Switchable client) {
        this.on = false;
        this.client = client;
    }

    @Override
    public boolean isOn() {
        return this.on;
    }

    @Override
    public void press() {
        boolean checkOn = isOn();

        if (checkOn) {
            client.turnOff();
            this.on = false;
        } else {
            client.turnOn();
            this.on = true;
        }
    }
}
