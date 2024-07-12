package Solid.Dependency_Inversion.Weather_Reporter;

public class FahrenheitTemperatureSensor implements TemperatureSensor{
    private double temperature;

    public FahrenheitTemperatureSensor(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getValue() {
        return this.temperature;
    }
}
