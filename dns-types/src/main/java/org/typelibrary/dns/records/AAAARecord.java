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

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;
import org.typelibrary.dns.records.Utils.IP6Formatter;

public final class AAAARecord extends Record {

    private final ByteString address;
    private String toStringCache;

    public AAAARecord(Name name, short recordClass, int timeToLive, ByteString address) {
        super(name, RecordType.AAAA, recordClass, timeToLive);
        if (address == null) throw new IllegalArgumentException("Address cannot be null");
        if (address.length() != 16) throw new IllegalArgumentException("Address must be 16 bytes long. len=" + address.length());
        this.address = address;
    }

    public final ByteString getAddress() {
        return address;
    }

    public final InetAddress toInetAddress(Name hostname) {
        try {
            return InetAddress.getByAddress(hostname.toString(), address.toByteArray());
        } catch (UnknownHostException e) {
            // Should never happen since we check address length in constructor
            throw new IllegalStateException(
                    "Unexpected error creating Inet4Address from: hostname=" + hostname
                            + ", address=" + address.toArrayString());
        }
    }
    
    public final String toString() {

        String localString = toStringCache;
        
        if (localString == null) {
            IP6Formatter formatter = new IP6Formatter();
            address.forEach(formatter);
            String tempString = new String(formatter.address);
            localString = super.toString() + ", address=[" + tempString + "]";
            toStringCache = localString;
        }
        
        return localString;

    }
}
