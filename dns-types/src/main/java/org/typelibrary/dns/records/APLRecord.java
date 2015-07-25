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

public final class APLRecord extends Record {

    private final short addressFamily;
    private final byte prefix;
    private final boolean negate;
    private final byte afdLength;
    private final ByteString afdPart;

    public APLRecord(Name name, short recordClass, int timeToLive, short addressFamily,
            byte prefix, boolean negate, byte afdLength, ByteString afdPart) {
        super(name, RecordType.APL, recordClass, timeToLive);
        if (afdPart== null)
            throw new IllegalArgumentException("Exchange cannot be null");
        this.addressFamily = addressFamily;
        this.prefix = prefix;
        this.negate = negate;
        this.afdLength = afdLength;
        this.afdPart = afdPart;
    }

    public short getAddressFamily() {
        return addressFamily;
    }

    public byte getPrefix() {
        return prefix;
    }

    public boolean isNegate() {
        return negate;
    }

    public byte getAfdLength() {
        return afdLength;
    }

    public ByteString getAfdPart() {
        return afdPart;
    }

    @Override
    public String toString() {
        return super.toString() + ", addressFamily=" + addressFamily + ", prefix=" + prefix
                + ", negate=" + negate + ", afdLength=" + afdLength + ", afdPart=" + afdPart + "]";
    }

}
