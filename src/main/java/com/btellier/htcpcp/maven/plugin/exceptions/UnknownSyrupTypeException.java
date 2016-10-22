package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class UnknownSyrupTypeException extends MojoExecutionException {

    public UnknownSyrupTypeException(String message) {
        super("Unknown syrup type" + message);
    }
}
