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

public final class DLVRecord extends Record {

    private final short keyTag;
    private final byte algorithm;
    private final byte digestType;
    private final ByteString digest;

    public DLVRecord(Name name, short recordClass, int timeToLive, short keyTag,
            byte algorithm, byte digestType, ByteString digest) {
        super(name, RecordType.DLV, recordClass, timeToLive);
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

    public byte getAlgorithm() {
        return algorithm;
    }

    public byte getDigestType() {
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
