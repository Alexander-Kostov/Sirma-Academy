package Solid.Dependency_Inversion.Weather_Reporter;

public class Main {
    public static void main(String[] args) {
        CelsiusTemperatureSensor temperatureSensor = new CelsiusTemperatureSensor(25.5);
        FahrenheitTemperatureSensor fahrenheitTemperatureSensor = new FahrenheitTemperatureSensor(105);

        Reporter reporter = new WeatherReporter(temperatureSensor);
        reporter.report();

        Reporter reporter1 = new WeatherReporter(fahrenheitTemperatureSensor);
        reporter1.report();
    }
}
