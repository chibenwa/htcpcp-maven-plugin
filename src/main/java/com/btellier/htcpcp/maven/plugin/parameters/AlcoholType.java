package com.btellier.htcpcp.maven.plugin.parameters;

import java.util.stream.Stream;

import com.btellier.htcpcp.maven.plugin.exceptions.UnknownAlcoholTypeException;
import com.google.common.base.Preconditions;

public enum AlcoholType {
    CREAM("Whisky"),
    HALF_AND_HALF("Rum"),
    WHOLE_MILK("Kahlua"),
    PART_SKIM("Aquavit"),
    UNDEFINED("");

    private String type;

    AlcoholType(String s) {
        this.type = s;
    }

    public static AlcoholType parseFromString(String s) throws UnknownAlcoholTypeException {
        Preconditions.checkNotNull(s);
        return Stream.of(AlcoholType.values())
            .filter(type -> s.equalsIgnoreCase(type.type))
            .findAny()
            .orElseThrow(() -> new UnknownAlcoholTypeException(s));
    }
}
