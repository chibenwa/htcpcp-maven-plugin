package com.btellier.htcpcp.maven.plugin.protocol;

import org.assertj.core.util.Strings;

import com.btellier.htcpcp.maven.plugin.exceptions.UndefinedServerException;
import com.btellier.htcpcp.maven.plugin.protocol.requests.HTCPCPRequest;

public class HTCPCPEngine {

    public static HTCPCPEngine server(String host, long port) throws UndefinedServerException {
        if (Strings.isNullOrEmpty(host)) {
            throw new UndefinedServerException();
        }
        return new HTCPCPEngine(host, port);
    }

    private final String host;
    private final long port;

    private HTCPCPEngine(String host, long port) {
        this.host = host;
        this.port = port;
    }

    public void handle(HTCPCPRequest request) {

    }


}
