package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class UnknownStatusExteption extends MojoExecutionException {
    public UnknownStatusExteption(int status) {
        super("The " + status + " status returned by the server is unknown");
    }
}
