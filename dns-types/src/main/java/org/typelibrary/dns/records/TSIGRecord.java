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
 * A Transaction Signature record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc2845">RFC 2845</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>250</td></tr>
 * <tr><td>Meaning:</td><td>Transaction Signature</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Algorithm</td><td>domain-name</td><td>Name of the algorithm in domain name syntax</td></tr>
 * <tr><td>time signed</td><td>uint48</td><td></td></tr>
 * <tr><td>fudge</td><td>uint16</td><td></td></tr>
 * <tr><td>mac</td><td>{octets}*</td><td>MAC.</td></tr>
 * <tr><td>original id</td><td>uint16</td><td></td></tr>
 * <tr><td>error</td><td>uint16</td><td></td></tr>
 * <tr><td>other data</td><td>{octets}*</td><td>Other data.</td></tr>
 * </table>
 * </p>
 *
 */
public final class TSIGRecord extends Record {

    private final Name algorithm;
    private final long timeSigned;
    private final short fudge;
    private final ByteString mac;
    private final short originalId;
    private final ByteString otherData;

    public TSIGRecord(Name name, short recordClass, int timeToLive, Name algorithm,
            long timeSigned, short fudge, ByteString mac, short originalId,
            ByteString otherData) {
        super(name, RecordType.TSIG, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if ((timeSigned >>> 48) != 0)
            throw new IllegalArgumentException("Time signed must be a 48 bit value.");
        if (mac == null)
            throw new IllegalArgumentException("Mac cannot be null.");
        if (mac.length() > 255)
            throw new IllegalArgumentException("Mac cannot be > 65535 bytes.");
        if (otherData == null)
            throw new IllegalArgumentException("Other data cannot be null.");
        if (otherData.length() > 255)
            throw new IllegalArgumentException("Other data cannot be > 65535 bytes.");
        this.algorithm = algorithm;
        this.timeSigned = timeSigned;
        this.fudge = fudge;
        this.mac = mac;
        this.originalId = originalId;
        this.otherData = otherData;
    }

    public Name getAlgorithm() {
        return algorithm;
    }

    public long getTimeSigned() {
        return timeSigned;
    }

    public short getFudge() {
        return fudge;
    }

    public ByteString getMac() {
        return mac;
    }

    public short getOriginalId() {
        return originalId;
    }

    public ByteString getOtherData() {
        return otherData;
    }

    @Override
    public String toString() {
        return super.toString() + ", algorithm=" + algorithm + ", timeSigned=" + timeSigned
                + ", fudge=" + fudge + ", mac=" + mac + ", originalId="
                + originalId + ", otherData=" + otherData + "]";
    }

}
