package com.btellier.htcpcp.maven.plugin.protocol.requests;

import com.jayway.restassured.specification.RequestSpecification;

public interface HTCPCPRequest {

    RequestSpecification encode(RequestSpecification restAssuredRequest);

}
