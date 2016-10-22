package com.btellier.htcpcp.maven.plugin.mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.btellier.htcpcp.maven.plugin.protocol.HTCPCPEngine;
import com.btellier.htcpcp.maven.plugin.protocol.requests.StopBrewRequest;

@Mojo(name = "stopBrew")
public class HTCPCPStopBrewMojo extends AbstractMojo {

    /* Server parameters */
    @Parameter(property = "host", defaultValue = "")
    private String host;

    @Parameter(property = "port", defaultValue = "")
    private long port;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        HTCPCPEngine.server(host, port)
            .handle(new StopBrewRequest());
    }
}
