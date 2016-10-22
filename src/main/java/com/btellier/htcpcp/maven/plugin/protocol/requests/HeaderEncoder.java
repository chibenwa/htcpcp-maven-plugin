package com.btellier.htcpcp.maven.plugin.protocol.requests;

import com.jayway.restassured.specification.RequestSpecification;

interface HeaderEncoder {
    RequestSpecification encode(RequestSpecification restAssuredRequest);
}
