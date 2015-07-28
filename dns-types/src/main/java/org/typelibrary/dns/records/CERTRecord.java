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

public final class CERTRecord extends Record {

    private final short type;
    private final short keyTag;
    private final Algorithm algorithm;
    private final ByteString certificate;

    public CERTRecord(Name name, short recordClass, int timeToLive, short type,
            short keyTag, Algorithm algorithm, ByteString certificate) {
        super(name, RecordType.CERT, recordClass, timeToLive);
        if (certificate == null)
            throw new IllegalArgumentException("Digest cannot be null.");
        this.type = type;
        this.keyTag = keyTag;
        this.algorithm = algorithm;
        this.certificate = certificate;
    }

    public short getType() {
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
