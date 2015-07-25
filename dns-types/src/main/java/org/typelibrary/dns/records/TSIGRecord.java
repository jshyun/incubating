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

public final class TSIGRecord extends Record {

    private final Name algorithm;
    private final long timeSigned;
    private final short fudge;
    private final short macSize;
    private final ByteString mac;
    private final short originalId;
    private final short otherLen;
    private final ByteString otherData;

    public TSIGRecord(Name name, short recordClass, int timeToLive, Name algorithm,
            long timeSigned, short fudge, short macSize, ByteString mac, short originalId,
            short otherLen, ByteString otherData) {
        super(name, RecordType.TSIG, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (mac == null)
            throw new IllegalArgumentException("Mac cannot be null.");
        if (otherData == null)
            throw new IllegalArgumentException("Other data cannot be null.");
        this.algorithm = algorithm;
        this.timeSigned = timeSigned;
        this.fudge = fudge;
        this.macSize = macSize;
        this.mac = mac;
        this.originalId = originalId;
        this.otherLen = otherLen;
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

    public short getMacSize() {
        return macSize;
    }

    public ByteString getMac() {
        return mac;
    }

    public short getOriginalId() {
        return originalId;
    }

    public short getOtherLen() {
        return otherLen;
    }

    public ByteString getOtherData() {
        return otherData;
    }

    @Override
    public String toString() {
        return super.toString() + ", algorithm=" + algorithm + ", timeSigned=" + timeSigned
                + ", fudge=" + fudge + ", macSize=" + macSize + ", mac=" + mac + ", originalId="
                + originalId + ", otherLen=" + otherLen + ", otherData=" + otherData + "]";
    }

}
