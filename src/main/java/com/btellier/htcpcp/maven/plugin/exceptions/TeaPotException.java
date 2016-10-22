package com.btellier.htcpcp.maven.plugin.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

public class TeaPotException extends MojoExecutionException {
    public TeaPotException() {
        super("The provided server is a teapot!!!");
    }
}
