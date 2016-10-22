package com.btellier.htcpcp.maven.plugin.protocol.requests;

import java.util.function.Function;

import javax.inject.Provider;

import com.jayway.restassured.specification.RequestSpecification;

class ConditionalHeaderEncoder implements HeaderEncoder {

    private final Provider<Boolean> condition;
    private final Function<RequestSpecification, RequestSpecification> addHeader;

    ConditionalHeaderEncoder(Provider<Boolean> condition, Function<RequestSpecification, RequestSpecification> addHeader) {
        this.condition = condition;
        this.addHeader = addHeader;
    }

    @Override
    public RequestSpecification encode(RequestSpecification restAssuredRequest) {
        if (condition.get()) {
            return addHeader.apply(restAssuredRequest);
        }
        return restAssuredRequest;
    }

}
