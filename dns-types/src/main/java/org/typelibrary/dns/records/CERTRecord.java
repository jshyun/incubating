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
import org.typelibrary.dns.Algorithm;
import org.typelibrary.dns.CertificateType;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

/**
 * A  Certificate record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc4398">RFC 4398</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>37</td></tr>
 * <tr><td>Meaning:</td><td>Certificate</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>type</td><td>16-bits</td><td>Type</td></tr>
 * <tr><td>key tag</td><td>16-bits</td><td>Key tag</td></tr>
 * <tr><td>algorithm</td><td>8-bits</td><td>Algorithm</td></tr>
 * <tr><td>certificate</td><td>{octets}*</td><td>Certificate</td></tr>
 * </table>
 * </p>
 *
 */
public final class CERTRecord extends Record {

    private final CertificateType type;
    private final short keyTag;
    private final Algorithm algorithm;
    private final ByteString certificate;

    public CERTRecord(Name name, short recordClass, int timeToLive, CertificateType type,
            short keyTag, Algorithm algorithm, ByteString certificate) {
        super(name, RecordType.CERT, recordClass, timeToLive);
        if (type == null)
            throw new IllegalArgumentException("Certificate type cannot be null.");
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (certificate == null)
            throw new IllegalArgumentException("Certificate cannot be null.");
        this.type = type;
        this.keyTag = keyTag;
        this.algorithm = algorithm;
        this.certificate = certificate;
    }

    public CertificateType getType() {
        return type;
    }

    public short getKeyTag() {
        return keyTag;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public ByteString getCertificate() {
        return certificate;
    }

    @Override
    public String toString() {
        return super.toString() + ", type=" + type + ", keyTag=" + keyTag + ", algorithm="
                + algorithm + ", certificate=" + certificate;
    }

}
