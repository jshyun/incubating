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
 * An X25 record.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc1183">RFC 1183</a></td></tr>
 * <tr><td>Status:</td><td>Not Common</td></tr>
 * <tr><td>TYPE:</td><td>19</td></tr>
 * <tr><td>Meaning:</td><td>An X25 record.</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>PSDN Address</td><td>character-string</td><td>PSDN Address</td></tr>
 * </table>
 * </p>
 *
 */
public final class X25Record extends Record {

    private final ByteString psdnAddress;

    public X25Record(Name name, short recordClass, int timeToLive, ByteString psdnAddress) {
        super(name, RecordType.X25, recordClass, timeToLive);
        if (psdnAddress == null)
            throw new IllegalArgumentException("PSDN address cannot be null");
        this.psdnAddress = psdnAddress;
    }

    public final ByteString getAddress() {
        return psdnAddress;
    }

    public final String toString() {
        return super.toString() + ", PSDNAddress=" + psdnAddress;
    }

}
