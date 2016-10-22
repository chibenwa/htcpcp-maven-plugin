package com.btellier.htcpcp.maven.plugin.parameters;

import java.util.stream.Stream;

import com.btellier.htcpcp.maven.plugin.exceptions.UnknownMilkTypeException;
import com.google.common.base.Preconditions;

public enum MilkType {
    CREAM("Cream"),
    HALF_AND_HALF("Half-and-half"),
    WHOLE_MILK("Whole-milk"),
    PART_SKIM("Part-Skim"),
    SKIM("Skim"),
    NON_DAIRY("Non-Dairy"),
    UNDEFINED("");

    private String type;

    MilkType(String s) {
        this.type = s;
    }

    public static MilkType parseFromString(String s) throws UnknownMilkTypeException {
        Preconditions.checkNotNull(s);
        return Stream.of(MilkType.values())
            .filter(type -> s.equalsIgnoreCase(type.type))
            .findAny()
            .orElseThrow(() -> new UnknownMilkTypeException(s));
    }
}
