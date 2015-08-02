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
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

/**
 * An RRSIG record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc4034">RFC 4034</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>46</td></tr>
 * <tr><td>Meaning:</td><td>A RRSIG</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Type Covered</td><td>16-bits</td><td>RR TYPE covered by this record.</td></tr>
 * <tr><td>Algorithm</td><td>8-bits</td><td>The cryptographic algorithm.</td></tr>
 * <tr><td>Labels</td><td>8-bits</td><td>Number of labels in the original RRSIG owner name.</td></tr>
 * <tr><td>Original TTL</td><td>32-bits</td><td>Original TTL.</td></tr>
 * <tr><td>Signature Expiration</td><td>32-bits</td><td>Signature expiration.</td></tr>
 * <tr><td>Signature Inception</td><td>32-bits</td><td>Signature inception.</td></tr>
 * <tr><td>Key Tag</td><td>16-bits</td><td>Key Tag.</td></tr>
 * <tr><td>Signer's Name</td><td>Name</td><td>Signer's name.</td></tr>
 * <tr><td>Signature</td><td>Name</td><td>Signature.</td></tr>
 * </table>
 * </p>
 *
 */
public final class RRSIGRecord extends Record {

    private final RecordType typeCovered;
    private final Algorithm algorithm;
    private final byte labels;
    private final int originalTtl;
    private final int signatureExpiration;
    private final int signatureInception;
    private final short keyTag;
    private final Name signer;
    private final ByteString signature;

    public RRSIGRecord(Name name, short recordClass, int timeToLive, RecordType typeCovered,
            Algorithm algorithm, byte labels, int originalTtl, int signatureExpiration,
            int signatureInception, short keyTag, Name signer, ByteString signature) {
        super(name, RecordType.RRSIG, recordClass, timeToLive);
        if (typeCovered == null)
            throw new IllegalArgumentException("Type covered cannot be null.");
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (signer == null)
            throw new IllegalArgumentException("Signer cannot be null.");
        if (signature == null)
            throw new IllegalArgumentException("Signature cannot be null.");
        this.typeCovered = typeCovered;
        this.algorithm = algorithm;
        this.labels = labels;
        this.originalTtl = originalTtl;
        this.signatureExpiration = signatureExpiration;
        this.signatureInception = signatureInception;
        this.keyTag = keyTag;
        this.signer = signer;
        this.signature = signature;
    }

    public RecordType getTypeCovered() {
        return typeCovered;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public byte getLabels() {
        return labels;
    }

    public int getOriginalTtl() {
        return originalTtl;
    }

    public int getSignatureExpiration() {
        return signatureExpiration;
    }

    public int getSignatureInception() {
        return signatureInception;
    }

    public short getKeyTag() {
        return keyTag;
    }

    public Name getSigner() {
        return signer;
    }

    public ByteString getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return super.toString() + ", typeCovered=" + typeCovered + ", algorithm=" + algorithm + ", labels="
                + labels + ", originalTtl=" + originalTtl + ", signatureExpiration="
                + signatureExpiration + ", signatureInception=" + signatureInception + ", keyTag="
                + keyTag + ", signer=" + signer + ", signature=" + signature;
    }

}
