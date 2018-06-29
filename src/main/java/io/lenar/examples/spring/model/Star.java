package io.lenar.examples.spring.model;

import java.util.List;

public class Star {

    private String name;

    private StarType type;

    private List<Planet> planets;

    public Star(String name, StarType type, List<Planet> planets) {
        this.name = name;
        this.type = type;
        this.planets = planets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarType getType() {
        return type;
    }

    public void setType(StarType type) {
        this.type = type;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
