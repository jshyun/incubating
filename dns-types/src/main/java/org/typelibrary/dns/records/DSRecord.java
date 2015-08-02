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
import org.typelibrary.dns.DigestType;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

/**
 * A DS record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc4034">RFC 4034</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>43</td></tr>
 * <tr><td>Meaning:</td><td>A DS</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Key Tag</td><td>8-bits</td><td>Key Tag.</td></tr>
 * <tr><td>Algorithm</td><td>8-bits</td><td>The cryptographic algorithm.</td></tr>
 * <tr><td>Digest Type</td><td>8-bits</td><td>Digest type.</td></tr>
 * <tr><td>Digest</td><td>{octets}*</td><td>Digest.</td></tr>
 * </table>
 * </p>
 *
 */
public final class DSRecord extends Record {

    private final short keyTag;
    private final Algorithm algorithm;
    private final DigestType digestType;
    private final ByteString digest;

    public DSRecord(Name name, short recordClass, int timeToLive, short keyTag,
            Algorithm algorithm, DigestType digestType, ByteString digest) {
        super(name, RecordType.DS, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (digestType == null)
            throw new IllegalArgumentException("Digest type cannot be null.");
        if (digest == null)
            throw new IllegalArgumentException("Digest cannot be null.");
        this.keyTag = keyTag;
        this.algorithm = algorithm;
        this.digestType = digestType;
        this.digest = digest;
    }

    public short getKeyTag() {
        return keyTag;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public DigestType getDigestType() {
        return digestType;
    }

    public ByteString getDigest() {
        return digest;
    }

    @Override
    public String toString() {
        return super.toString() + ", keyTag=" + keyTag + ", algorithm=" + algorithm
                + ", digestType=" + digestType + ", digest=" + digest;
    }

}
