package com.btellier.htcpcp.maven.plugin.parameters;

import java.util.stream.Stream;

import com.btellier.htcpcp.maven.plugin.exceptions.UnknownSyrupTypeException;
import com.google.common.base.Preconditions;

public enum SyrupType {
    VANILLA("Vanilla"),
    ALMOND("Almond"),
    RASPBERRY("Raspberry"),
    CHOCOLATE("Chocolate"),
    UNDEFINED("");

    private String type;

    SyrupType(String s) {
        this.type = s;
    }

    public static SyrupType parseFromString(String s) throws UnknownSyrupTypeException {
        Preconditions.checkNotNull(s);
        return Stream.of(SyrupType.values())
            .filter(type -> s.equalsIgnoreCase(type.type))
            .findAny()
            .orElseThrow(() -> new UnknownSyrupTypeException(s));
    }
}
