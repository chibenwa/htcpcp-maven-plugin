package com.btellier.htcpcp.maven.plugin.protocol.requests;

import com.jayway.restassured.specification.RequestSpecification;

class CombineHeaderEncoder implements HeaderEncoder {

    private final HeaderEncoder headerEncoder1;
    private final HeaderEncoder headerEncoder2;

    CombineHeaderEncoder(HeaderEncoder headerEncoder1, HeaderEncoder headerEncoder2) {
        this.headerEncoder1 = headerEncoder1;
        this.headerEncoder2 = headerEncoder2;
    }

    @Override
    public RequestSpecification encode(RequestSpecification restAssuredRequest) {
        return headerEncoder1.encode(headerEncoder2.encode(restAssuredRequest));
    }
}
