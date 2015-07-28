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

public final class RRSIGRecord extends Record {

    private final short typeCovered;
    private final Algorithm algorithm;
    private final byte labels;
    private final int originalTtl;
    private final int signatureExpiration;
    private final int signatureInception;
    private final short keyTag;
    private final Name signer;
    private final ByteString publicKey;

    public RRSIGRecord(Name name, short recordClass, int timeToLive, short typeCovered,
            Algorithm algorithm, byte labels, int originalTtl, int signatureExpiration,
            int signatureInception, short keyTag, Name signer, ByteString publicKey) {
        super(name, RecordType.RRSIG, recordClass, timeToLive);
        if (signer == null)
            throw new IllegalArgumentException("Signer cannot be null.");
        if (publicKey == null)
            throw new IllegalArgumentException("Public key cannot be null.");
        this.typeCovered = typeCovered;
        this.algorithm = algorithm;
        this.labels = labels;
        this.originalTtl = originalTtl;
        this.signatureExpiration = signatureExpiration;
        this.signatureInception = signatureInception;
        this.keyTag = keyTag;
        this.signer = signer;
        this.publicKey = publicKey;
    }

    public short getTypeCovered() {
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

    public ByteString getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return super.toString() + ", typeCovered=" + typeCovered + ", algorithm=" + algorithm + ", labels="
                + labels + ", originalTtl=" + originalTtl + ", signatureExpiration="
                + signatureExpiration + ", signatureInception=" + signatureInception + ", keyTag="
                + keyTag + ", signer=" + signer + ", publicKey=" + publicKey + "]";
    }

}
