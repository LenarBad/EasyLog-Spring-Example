package io.lenar.examples.spring.model;

public class Planet {

    private String name;

    private boolean haveSatellites;

    public Planet(String name, boolean haveSatellites) {
        this.name = name;
        this.haveSatellites = haveSatellites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHaveSatellites() {
        return haveSatellites;
    }

    public void setHaveSatellites(boolean haveSatellites) {
        this.haveSatellites = haveSatellites;
    }
}
