package com.btellier.htcpcp.maven.plugin.protocol.requests;

import com.jayway.restassured.specification.RequestSpecification;

public class StopBrewRequest implements HTCPCPRequest {
    @Override
    public RequestSpecification encode(RequestSpecification restAssuredRequest) {
        return restAssuredRequest.body("stop");
    }
}
