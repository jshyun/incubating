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
 * An ISDN address.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc1183">RFC 1183</a></td></tr>
 * <tr><td>Status:</td><td>Not Common</td></tr>
 * <tr><td>TYPE:</td><td>20</td></tr>
 * <tr><td>Meaning:</td><td>An ISDN record.</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>ISDN Address</td><td>character-string</td><td>ISDN Address</td></tr>
 * <tr><td>SA</td><td>character-string</td><td>Sub Address</td></tr>
 * </table>
 * </p>
 *
 */
public final class ISDNRecord extends Record {

    private final ByteString isdnAddress;
    private final ByteString sa;
    
    public ISDNRecord(Name name, short recordClass, int timeToLive, ByteString isdnAddress, ByteString sa) {
        super(name, RecordType.ISDN, recordClass, timeToLive);
        if (isdnAddress == null)
            throw new IllegalArgumentException("PSDN address cannot be null");
        this.isdnAddress = isdnAddress;
        this.sa = sa;
    }

    public final ByteString getAddress() {
        return isdnAddress;
    }

    public final ByteString getSa() {
        return sa;
    }

    public final String toString() {
        return super.toString() + ", PSDNAddress=" + isdnAddress + ", sa=" + sa;
    }

}
