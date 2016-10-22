package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class UndefinedServerException extends MojoExecutionException {

    public UndefinedServerException() {
        super("You must define a server parameter");
    }
}
