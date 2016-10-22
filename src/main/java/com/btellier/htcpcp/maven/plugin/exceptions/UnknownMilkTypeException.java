package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class UnknownMilkTypeException extends MojoExecutionException {

    public UnknownMilkTypeException(String message) {
        super("Unknown milk type" + message);
    }
}
