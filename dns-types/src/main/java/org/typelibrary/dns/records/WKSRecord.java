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
 * A well known service record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Not Favored</td></tr>
 * <tr><td>TYPE:</td><td>11</td></tr>
 * <tr><td>Meaning:</td><td>Well Known Service</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>ADDRESS</td><td>32-bits</td><td>An Internet address.</td></tr>
 * <tr><td>PROTOCOL</td><td>8-bits</td><td>IP Protocol number</td></tr>
 * <tr><td>BIT MAP</td><td>{octets}*</td><td>Variable length bit map.</td></tr>
 * </table>
 * </p>
 *
 */
public final class WKSRecord extends Record {

    private final ByteString address;
    private final byte protocol;
    private final ByteString bitmap;

    public WKSRecord(Name name, short recordClass, int timeToLive, ByteString address,
            byte protocol, ByteString bitmap) {
        super(name, RecordType.WKS, recordClass, timeToLive);
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null.");
        if (address.length() != 4)
            throw new IllegalArgumentException("Address must be 4 bytes.");
        if (bitmap == null)
            throw new IllegalArgumentException("Bitmap cannot be null.");
        this.address = address;
        this.protocol = protocol;
        this.bitmap = bitmap;
    }

    public ByteString getAddress() {
        return address;
    }

    public byte getProtocol() {
        return protocol;
    }

    public ByteString getBitmap() {
        return bitmap;
    }

    @Override
    public String toString() {
        return super.toString() + ", address=" + address + ", protocol=" + protocol + ", bitmap="
                + bitmap;
    }

}
