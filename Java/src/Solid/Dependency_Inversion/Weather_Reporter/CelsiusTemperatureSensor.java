package Solid.Dependency_Inversion.Weather_Reporter;

public class CelsiusTemperatureSensor implements TemperatureSensor {

    private double temperature;

    public CelsiusTemperatureSensor(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getValue() {
        return this.temperature;
    }
}
