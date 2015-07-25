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

public final class TKEYRecord extends Record {

    private final Name algorithm;
    private final int inception;
    private final int expiration;
    private final short mode;
    private final short keySize;
    private final ByteString keyData;
    private final ByteString otherData;

    public TKEYRecord(Name name, short recordClass, int timeToLive, Name algorithm, int inception,
            int expiration, short mode, short keySize, ByteString keyData, ByteString otherData) {
        super(name, RecordType.TKEY, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (keyData == null)
            throw new IllegalArgumentException("Key data cannot be null.");
        if (otherData == null)
            throw new IllegalArgumentException("Other data cannot be null.");
        this.algorithm = algorithm;
        this.inception = inception;
        this.expiration = expiration;
        this.mode = mode;
        this.keySize = keySize;
        this.keyData = keyData;
        this.otherData = otherData;
    }

    public Name getAlgorithm() {
        return algorithm;
    }

    public int getInception() {
        return inception;
    }

    public int getExpiration() {
        return expiration;
    }

    public short getMode() {
        return mode;
    }

    public short getKeySize() {
        return keySize;
    }

    public ByteString getKeyData() {
        return keyData;
    }

    public ByteString getOtherData() {
        return otherData;
    }

    @Override
    public String toString() {
        return super.toString() + ", algorithm=" + algorithm + ", inception=" + inception + ", expiration="
                + expiration + ", mode=" + mode + ", keySize=" + keySize + ", keyData=" + keyData
                + ", otherData=" + otherData + "]";
    }

}
