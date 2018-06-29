package io.lenar.examples.spring.model;

public enum StarType {
    RED_GIANT, WHITE_DWARF, RED_DWARF, NEUTRON, SUPERGIANT;

    public static StarType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
