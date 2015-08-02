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

/**
 * A Certificate Authority Authorization record.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc6844">RFC 6844</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>257</td></tr>
 * <tr><td>Meaning:</td><td>Certificate Authroity Authorization</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>flags</td><td>8-bits</td><td>Flags</td></tr>
 * <tr><td>TAG</td><td>{octets}*</td><td>Tag</td></tr>
 * <tr><td>VALUE</td><td>{octets}*</td><td>Value</td></tr>
 * </table>
 * </p>
 *
 */
public final class CAARecord extends Record {

    private final byte flags;
    private final ByteString tag;
    private final ByteString value;

    public CAARecord(Name name, short recordClass, int timeToLive, byte flags,
            ByteString tag, ByteString value) {
        super(name, RecordType.CAA, recordClass, timeToLive);
        if (tag == null)
            throw new IllegalArgumentException("Tag cannot be null.");
        if (value == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        this.flags = flags;
        this.tag = tag;
        this.value = value;
    }

    public short getFlags() {
        return flags;
    }

    public ByteString getTag() {
        return tag;
    }

    public ByteString getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString() + ", flags=" + flags + ", tag=" + tag + ", value="
                + value;
    }

}
