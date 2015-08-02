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
 * An NSEC3 record.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc5155">RFC 5155</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>50</td></tr>
 * <tr><td>Meaning:</td><td>An NSEC</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Hash Algorithm</td><td>8-bits</td><td>Hash Algorithm</td></tr>
 * <tr><td>Flags</td><td>8-bits</td><td>Flags</td></tr>
 * <tr><td>Iterations</td><td>16-bits</td><td>Iterations</td></tr>
 * <tr><td>Salt</td><td>{octets}*</td><td>Salt</td></tr>
 * <tr><td>Next Hashed Owner Name</td><td>{octets}*</td><td>Next Hashed Owner Name</td></tr>
 * <tr><td>Type Bitmaps</td><td>{octets}*</td><td>Type bit maps.</td></tr>
 * </table>
 * </p>
 *
 */
public final class NSEC3Record extends Record {

    private final Algorithm algorithm;
    private final byte flags;
    private final short iterations;
    private final ByteString salt;
    private final ByteString nextHashedOwnerName;
    private final ByteString typeBitmaps;

    public NSEC3Record(Name name, short recordClass, int timeToLive, Algorithm algorithm,
            byte flags, short iterations, ByteString salt, 
            ByteString nextHashedOwnerName, ByteString typeBitmaps) {
        super(name, RecordType.NSEC3, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (salt == null)
            throw new IllegalArgumentException("Salt cannot be null.");
        if (salt.length() > 255)
            throw new IllegalArgumentException("Salt cannot be > 255 bytes.");
        if (nextHashedOwnerName == null)
            throw new IllegalArgumentException("Next hashed owner name cannot be null.");
        if (nextHashedOwnerName.length() > 255)
            throw new IllegalArgumentException("Next hashed owner name cannot be > 255 bytes.");
        if (typeBitmaps == null)
            throw new IllegalArgumentException("Type bitmaps cannot be null.");
        this.algorithm = algorithm;
        this.flags = flags;
        this.iterations = iterations;
        this.salt = salt;
        this.nextHashedOwnerName = nextHashedOwnerName;
        this.typeBitmaps = typeBitmaps;
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

    public ByteString getSalt() {
        return salt;
    }

    public ByteString getNextHashedOwnerName() {
        return nextHashedOwnerName;
    }

    public ByteString getTypeBitmaps() {
        return typeBitmaps;
    }

    @Override
    public String toString() {
        return super.toString() + ", algorithm=" + algorithm + ", flags=" + flags + ", iterations="
                + iterations + ", salt=" + salt 
                + ", nextHashedOwnerName=" + nextHashedOwnerName + ", typeBitmaps="
                + typeBitmaps + "]";
    }

}
