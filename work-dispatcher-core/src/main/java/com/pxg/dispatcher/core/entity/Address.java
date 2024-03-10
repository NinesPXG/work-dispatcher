package com.pxg.dispatcher.core.entity;

import com.pxg.dispatcher.core.common.ParkStatement;
import lombok.Data;

import java.util.Objects;

@Data
public class Address implements ParkStatement {

    private String host;

    private int port;


    public Address() {
    }

    public Address(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getUrl() {
        return host + ':' + port;
    }

    public String getUrl(String path) {
        return getUrl() + path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return port == address.port &&
                host.equals(address.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

}
