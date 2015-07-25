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

import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

public final class IPSECKEYRecord extends Record {

    private final byte precedence;
    private final byte gatewayType;
    private final byte algorithm;
    private final ByteString gateway;
    private final ByteString publicKey;

    public IPSECKEYRecord(Name name, short recordClass, int timeToLive, byte precedence,
            byte gatewayType, byte algorithm, ByteString gateway, ByteString publicKey) {
        super(name, RecordType.IPSECKEY, recordClass, timeToLive);
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

    public byte getGatewayType() {
        return gatewayType;
    }

    public byte getAlgorithm() {
        return algorithm;
    }

    public ByteString getGateway() {
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
