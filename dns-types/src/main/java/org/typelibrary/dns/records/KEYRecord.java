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

public final class KEYRecord extends Record {

    private final short flags;
    private final byte protocol;
    private final byte algorithm;
    private final ByteString publicKey;

    public KEYRecord(Name name, short recordClass, int timeToLive, short flags,
            byte protocol, byte algorithm, ByteString publicKey) {
        super(name, RecordType.KEY, recordClass, timeToLive);
        this.flags = flags;
        this.protocol = protocol;
        this.algorithm = algorithm;
        this.publicKey = publicKey;
    }

    public short getFlags() {
        return flags;
    }

    public byte getProtocol() {
        return protocol;
    }

    public byte getAlgorithm() {
        return algorithm;
    }

    public ByteString getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return super.toString() + ", flags=" + flags + ", protocol=" + protocol + ", algorithm="
                + algorithm + ", publicKey=" + publicKey + "]";
    }


}
