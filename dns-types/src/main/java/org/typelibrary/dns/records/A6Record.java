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
 * An A6 record.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc2874">RFC 2874</a></td></tr>
 * <tr><td>Status:</td><td>Historic</td></tr>
 * <tr><td>TYPE:</td><td>38</td></tr>
 * <tr><td>Meaning:</td><td>An A6 record.</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Prefix Length</td><td>8-bits</td><td>Prefix length.</td></tr>
 * <tr><td>Address Suffix</td><td>0-16 octets</td><td>Address suffix.</td></tr>
 * <tr><td>Prefix Name</td><td>0-255 octets</td><td>Prefix name.</td></tr>
 * </table>
 * </p>
 * 
 */
public final class A6Record extends Record {

    private final byte prefixLength;
    private final ByteString addressSuffix;
    private final Name prefixName;

    public A6Record(Name name, short recordClass, int timeToLive, byte prefixLength, 
            ByteString addressSuffix, Name prefixName) {
        super(name, RecordType.A6, recordClass, timeToLive);
        if (prefixLength < 0 || prefixLength > 128)
            throw new IllegalArgumentException("Prefix must be between 0 and 128 inclusive.");
        if (addressSuffix == null)
            throw new IllegalArgumentException("Address suffix cannot be null.");
        if (prefixLength == 128 && addressSuffix.length() > 0)
            throw new IllegalArgumentException("Address suffix must be empty is prefix length = 128.");
        if (addressSuffix.length() > 16)
            throw new IllegalArgumentException("Address suffix cannot > 16 bytes.");
        if (prefixName == null)
            throw new IllegalArgumentException("Bitmap cannot be null.");
        this.prefixLength = prefixLength;
        this.addressSuffix = addressSuffix;
        this.prefixName = prefixName;
    }

    public byte getPrefixLength() {
        return prefixLength;
    }

    public ByteString getAddressSuffix() {
        return addressSuffix;
    }

    public Name getPrefixName() {
        return prefixName;
    }

    @Override
    public String toString() {
        return super.toString() + ", prefixLength=" + prefixLength + ", addressSuffix="
                + addressSuffix + ", prefixName=" + prefixName;
    }

}
