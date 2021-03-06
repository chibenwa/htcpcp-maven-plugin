package com.btellier.htcpcp.maven.plugin.protocol.requests;

import java.util.Optional;

import com.btellier.htcpcp.maven.plugin.exceptions.UnknownAlcoholTypeException;
import com.btellier.htcpcp.maven.plugin.exceptions.UnknownMilkTypeException;
import com.btellier.htcpcp.maven.plugin.exceptions.UnknownSyrupTypeException;
import com.btellier.htcpcp.maven.plugin.parameters.AlcoholType;
import com.btellier.htcpcp.maven.plugin.parameters.MilkType;
import com.btellier.htcpcp.maven.plugin.parameters.SyrupType;
import com.google.common.collect.ImmutableList;
import com.jayway.restassured.specification.RequestSpecification;

public class StartBrewRequest implements HTCPCPRequest {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String syrupType = "";
        private String milkType = "";
        private String sweetenerType = "";
        private String spiceType = "";
        private String alcoholType = "";

        public Builder syrup(String syrup) {
            this.syrupType = syrup;
            return this;
        }

        public Builder milk(String milkType) {
            this.milkType = milkType;
            return this;
        }

        public Builder sweetener(String sweetenerType) {
            this.sweetenerType = sweetenerType;
            return this;
        }

        public Builder spice(String spiceType) {
            this.spiceType = spiceType;
            return this;
        }

        public Builder alcohol(String alcoholType) {
            this.alcoholType = alcoholType;
            return this;
        }

        public StartBrewRequest build() throws UnknownAlcoholTypeException, UnknownMilkTypeException, UnknownSyrupTypeException {
            return new StartBrewRequest(
                SyrupType.parseFromString(syrupType),
                MilkType.parseFromString(milkType),
                Optional.of(sweetenerType).filter(sweetener -> !sweetener.isEmpty()),
                Optional.of(spiceType).filter(spice -> !spice.isEmpty()),
                AlcoholType.parseFromString(alcoholType));
        }

    }

    private StartBrewRequest(SyrupType syrupType, MilkType milkType, Optional<String> sweetenerType, Optional<String> spiceType, AlcoholType alcoholType) {
        this.syrupType = syrupType;
        this.milkType = milkType;
        this.sweetenerType = sweetenerType;
        this.spiceType = spiceType;
        this.alcoholType = alcoholType;
    }

    private final SyrupType syrupType;
    private final MilkType milkType;
    private final Optional<String> sweetenerType;
    private final Optional<String> spiceType;
    private final AlcoholType alcoholType;

    @Override
    public RequestSpecification encode(RequestSpecification restAssuredRequest) {
        return ImmutableList.<HeaderEncoder>of(
            new ConditionalHeaderEncoder(() -> syrupType != SyrupType.UNDEFINED, req -> req.header("syrup-type", syrupType.getValue())),
            new ConditionalHeaderEncoder(() -> milkType != MilkType.UNDEFINED, req -> req.header("milk-type", milkType.getValue())),
            new ConditionalHeaderEncoder(() -> alcoholType != AlcoholType.UNDEFINED, req -> req.header("alcohol-type", alcoholType.getValue())),
            new ConditionalHeaderEncoder(sweetenerType::isPresent, req -> req.header("sweetener-type", sweetenerType.get())),
            new ConditionalHeaderEncoder(spiceType::isPresent, req -> req.header("spice-type", spiceType.get())))
            .stream()
            .reduce(CombineHeaderEncoder::new)
            .get()
            .encode(restAssuredRequest)
            .body("start");
    }
}
