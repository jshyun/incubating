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

import org.typelibrary.dns.Algorithm;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

public final class NSEC3PARAMRecord extends Record {

    private final Algorithm algorithm;
    private final byte flags;
    private final short iterations;
    private final byte saltLength;
    private final int salt;

    public NSEC3PARAMRecord(Name name, short recordClass, int timeToLive, Algorithm algorithm,
            byte flags, short iterations, byte saltLength, int salt) {
        super(name, RecordType.NSEC3PARAM, recordClass, timeToLive);
        this.algorithm = algorithm;
        this.flags = flags;
        this.iterations = iterations;
        this.saltLength = saltLength;
        this.salt = salt;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public byte getFlags() {
        return flags;
    }

    public short getIterations() {
        return iterations;
    }

    public byte getSaltLength() {
        return saltLength;
    }

    public int getSalt() {
        return salt;
    }

    @Override
    public String toString() {
        return super.toString() + ", algorithm=" + algorithm + ", flags=" + flags + ", iterations="
                + iterations + ", saltLength=" + saltLength + ", salt=" + salt;
    }

}
