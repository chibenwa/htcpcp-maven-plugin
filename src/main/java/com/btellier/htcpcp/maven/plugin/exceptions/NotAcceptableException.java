package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class NotAcceptableException extends MojoExecutionException {
    public NotAcceptableException(String message) {
        super(message);
    }
}
