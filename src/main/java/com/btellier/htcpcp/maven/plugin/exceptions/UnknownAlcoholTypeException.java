package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class UnknownAlcoholTypeException extends MojoExecutionException {

    public UnknownAlcoholTypeException(String message) {
        super("Unknown alcohol type" + message);
    }
}
