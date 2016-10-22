package com.btellier.htcpcp.maven.plugin.mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.btellier.htcpcp.maven.plugin.protocol.HTCPCPEngine;
import com.btellier.htcpcp.maven.plugin.protocol.requests.StartBrewRequest;


@Mojo(name = "startBrew")
public class HTCPCPStartBrewMojo extends AbstractMojo {

    @Parameter(property = "fake", defaultValue = "false")
    private boolean fake;

    /* Server parameters */
    @Parameter(property = "host", defaultValue = "")
    private String host;

    @Parameter(property = "port", defaultValue = "80")
    private long port;

    /* Request parameters */
    @Parameter(property = "syrupType", defaultValue = "")
    private String syrupType;

    @Parameter(property = "milkType", defaultValue = "")
    private String milkType;

    @Parameter(property = "sweetenerType", defaultValue = "")
    private String sweetenerType;

    @Parameter(property = "spiceType", defaultValue = "")
    private String spiceType;

    @Parameter(property = "alcoholType", defaultValue = "")
    private String alcoholType;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Start BREWING coffee on " + host + ":" + port);
        if (!fake) {
            HTCPCPEngine.server(host, port)
                .handle(StartBrewRequest.builder()
                    .syrup(syrupType)
                    .milk(milkType)
                    .sweetener(sweetenerType)
                    .spice(spiceType)
                    .alcohol(alcoholType)
                    .build());
        }
    }
}
