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

/**
 * A TLS information record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc6698">RFC 6698</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>52</td></tr>
 * <tr><td>Meaning:</td><td>TLS information</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Cert Usage</td><td>8-bits</td><td></td></tr>
 * <tr><td>Selector</td><td>8-bits</td><td></td></tr>
 * <tr><td>Matching Type</td><td>8-bits</td><td></td></tr>
 * <tr><td>Certificate Association Data</td><td>{octets}*</td><td></td></tr>
 * </table>
 * </p>
 *
 */
public final class TLSARecord extends Record {

    private final byte certUsage;
    private final byte selector;
    private final byte matchingType;
    private final ByteString data;

    public TLSARecord(Name name, short recordClass, int timeToLive, byte certUsage, byte selector,
            byte matchingType, ByteString data) {
        super(name, RecordType.TLSA, recordClass, timeToLive);
        if (data == null)
            throw new IllegalArgumentException("Data cannot be null.");
        this.certUsage = certUsage;
        this.selector = selector;
        this.matchingType = matchingType;
        this.data = data;
    }

    public byte getCertUsage() {
        return certUsage;
    }

    public byte getSelector() {
        return selector;
    }

    public byte getMatchingType() {
        return matchingType;
    }

    public ByteString getData() {
        return data;
    }

    @Override
    public String toString() {
        return super.toString() + ", certUsage=" + certUsage + ", selector=" + selector
                + ", matchingType=" + matchingType + ", data=" + data + "]";
    }

}
