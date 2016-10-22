package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class ServerException extends MojoExecutionException {
    public ServerException(String message) {
        super("Internal server error: " + message);
    }
}
