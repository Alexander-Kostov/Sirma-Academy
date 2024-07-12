package Solid.Dependency_Inversion.Weather_Reporter;

public class WeatherReporter implements Reporter{

    private TemperatureSensor sensor;

    public WeatherReporter(TemperatureSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void report() {
        System.out.println("Current temperature is " + this.sensor.getValue());
    }
}
