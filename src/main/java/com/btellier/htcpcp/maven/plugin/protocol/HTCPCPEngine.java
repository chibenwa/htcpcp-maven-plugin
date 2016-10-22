package com.btellier.htcpcp.maven.plugin.protocol;

import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;

import org.assertj.core.util.Strings;

import com.btellier.htcpcp.maven.plugin.exceptions.NotAcceptableException;
import com.btellier.htcpcp.maven.plugin.exceptions.ServerException;
import com.btellier.htcpcp.maven.plugin.exceptions.TeaPotException;
import com.btellier.htcpcp.maven.plugin.exceptions.UndefinedServerException;
import com.btellier.htcpcp.maven.plugin.exceptions.UnknownStatusExteption;
import com.btellier.htcpcp.maven.plugin.protocol.requests.HTCPCPRequest;
import com.google.common.base.Charsets;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class HTCPCPEngine {

    public static HTCPCPEngine server(String host, int port) throws UndefinedServerException {
        if (Strings.isNullOrEmpty(host)) {
            throw new UndefinedServerException();
        }
        return new HTCPCPEngine(host, port);
    }

    private HTCPCPEngine(String host, int port) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setContentType("application/coffee-pot-command")
            .setConfig(newConfig().encoderConfig(encoderConfig().defaultContentCharset(Charsets.UTF_8)))
            .setPort(port).setBasePath("http://" + host + "/coffee")
            .build();
    }

    public void handle(HTCPCPRequest request) throws TeaPotException, NotAcceptableException, UnknownStatusExteption, ServerException {
        Response response = request.encode(RestAssured.with()).post();
        switch (response.statusCode()) {
            case 418:
                throw new TeaPotException();
            case 406:
                throw new NotAcceptableException(response.asString());
            case 500:
                throw new ServerException(response.asString());
            default:
                if (response.statusCode() < 200 || response.statusCode() > 299) {
                    throw new UnknownStatusExteption(response.statusCode());
                }
        }
    }


}
