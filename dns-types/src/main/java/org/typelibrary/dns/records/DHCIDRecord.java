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
 * A DHCP Information record.
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc4701">RFC 4701</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>49</td></tr>
 * <tr><td>Meaning:</td><td>DHCP Information</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>data</td><td>{octets}*</td><td>Data</td></tr>
 * </table>
 * </p>
 *
 */
public final class DHCIDRecord extends Record {

    private final ByteString rData;

    public DHCIDRecord(Name name, short recordClass, int timeToLive, ByteString rData) {
        super(name, RecordType.DHCID, recordClass, timeToLive);
        if (rData == null)
            throw new IllegalArgumentException("rData cannot be null");
        this.rData = rData;
    }

    public final ByteString getData() {
        return rData;
    }

    public final String toString() {
        return super.toString() + ", rdata=" + rData;
    }

}
