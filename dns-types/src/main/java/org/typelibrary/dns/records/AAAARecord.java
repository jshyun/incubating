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
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.typelibrary.dns.RecordType;
import org.typelibrary.dns.Record;

public final class AAAARecord extends Record {

    private final byte[] address;

    public AAAARecord(String name, int recordClass, long timeToLive, byte[] address) {
        this(name, recordClass, timeToLive, address, 0);
    }

    public AAAARecord(String name, int recordClass, long timeToLive, byte[] address, int offset) {
        super(name, RecordType.AAAA, recordClass, timeToLive);
        if (address == null) throw new IllegalArgumentException("Address cannot be null");
        if (address.length < 16) throw new IllegalArgumentException("Address must be at least 16 bytes long. len=" + address.length);
        this.address = new byte[16];
        System.arraycopy(address, offset, this.address, 0, 16);
    }

    public AAAARecord(String name, int recordClass, long timeToLive, ByteBuffer address) {
        super(name, RecordType.AAAA, recordClass, timeToLive);
        if (address == null) throw new IllegalArgumentException("Src cannot be null");
        this.address = new byte[16];
        address.get(this.address, 0, 16);
    }

    public final byte[] getAddress() {
        return Arrays.copyOf(address, address.length);
    }

    public final ByteBuffer getAddressAsByteBuffer() {
        return ByteBuffer.wrap(address).asReadOnlyBuffer();
    }

    public final void copyAddress(byte[] buffer, int offset) {
        System.arraycopy(address, 0, buffer, offset, address.length);
    }

    public final InetAddress toInetAddress(String hostname) {
        try {
            return InetAddress.getByAddress(hostname, address);
        } catch (UnknownHostException e) {
            // Should never happen since we check address length in constructor
            throw new IllegalStateException(
                    "Unexpected error creating Inet4Address from: hostname=" + hostname
                            + ", address=" + Arrays.toString(address));
        }
    }
    
    public final String toString() {
        return super.toString() + ", address=[" + Utils.toGroupedHexArray(address, 2, ':') + "]";
    }
}
