/*
 * Copyright (C) 2015 John Hyun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typelibrary.dns.records;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.GatewayType;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.PublicKeyAlgorithm;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

/**
 * An IPSECKEY record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc4025">RFC 4025</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>45</td></tr>
 * <tr><td>Meaning:</td><td>An IPSECKEY record</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>precedence</td><td>8-bits</td><td>Lower precedence preferred.</td></tr>
 * <tr><td>gateway type</td><td>8-bits</td><td>Gateway Type</td></tr>
 * <tr><td>algorithm</td><td>8-bits</td><td>Algorithm</td></tr>
 * <tr><td>gateway</td><td>octets</td><td>Gateway</td></tr>
 * <tr><td>public key</td><td>{octets}*</td><td>Public Keys</td></tr>
 * </table>
 * </p>
 *
 */
public final class IPSECKEYRecord extends Record {

    private final byte precedence;
    private final GatewayType gatewayType;
    private final PublicKeyAlgorithm algorithm;
    private final Object gateway;
    private final ByteString publicKey;

    public IPSECKEYRecord(Name name, short recordClass, int timeToLive, byte precedence,
            PublicKeyAlgorithm algorithm, ByteString publicKey) {
        this(name, recordClass, timeToLive, precedence, GatewayType.NONE, algorithm, null,
                publicKey);
    }
    
    public IPSECKEYRecord(Name name, short recordClass, int timeToLive, byte precedence,
            PublicKeyAlgorithm algorithm, InetAddress gateway, ByteString publicKey) {
        super(name, RecordType.IPSECKEY, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (gateway == null)
            throw new IllegalArgumentException("Gateway cannot be null.");
        if (publicKey == null)
            throw new IllegalArgumentException("Public key cannot be null.");
        if (gateway instanceof Inet4Address)
            gatewayType = GatewayType.IPV4;
        else if (gateway instanceof Inet6Address)
            gatewayType = GatewayType.IPV6;
        else 
            throw new IllegalArgumentException("Gateway address type is unrecognized.");
        this.precedence = precedence;
        this.algorithm = algorithm;
        this.gateway = gateway;
        this.publicKey = publicKey;
    }

    public IPSECKEYRecord(Name name, short recordClass, int timeToLive, byte precedence,
            PublicKeyAlgorithm algorithm, Name domainName, ByteString publicKey) {
        this(name, recordClass, timeToLive, precedence, GatewayType.DOMAIN_NAME, algorithm, domainName,
                publicKey);
    }
    
    private IPSECKEYRecord(Name name, short recordClass, int timeToLive, byte precedence,
            GatewayType gatewayType, PublicKeyAlgorithm algorithm, Object gateway, ByteString publicKey) {
        super(name, RecordType.IPSECKEY, recordClass, timeToLive);
        if (gatewayType == null)
            throw new IllegalArgumentException("Gateway type cannot be null.");
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (gateway == null)
            throw new IllegalArgumentException("Gateway cannot be null.");
        if (publicKey == null)
            throw new IllegalArgumentException("Public key cannot be null.");
        this.precedence = precedence;
        this.gatewayType = gatewayType;
        this.algorithm = algorithm;
        this.gateway = gateway;
        this.publicKey = publicKey;
    }

    public byte getPrecedence() {
        return precedence;
    }

    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public PublicKeyAlgorithm getAlgorithm() {
        return algorithm;
    }

    public Object getGateway() {
        return gateway;
    }

    public ByteString getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return super.toString() + ", precedence=" + precedence + ", gatewayType=" + gatewayType
                + ", algorithm=" + algorithm + ", gateway=" + gateway + ", publicKey=" + publicKey;
    }

}
