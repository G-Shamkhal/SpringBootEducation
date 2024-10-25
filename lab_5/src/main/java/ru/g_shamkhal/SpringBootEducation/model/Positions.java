package ru.g_shamkhal.SpringBootEducation.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    CEO(5.0,true),
    SE(1.9,false);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager){
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}