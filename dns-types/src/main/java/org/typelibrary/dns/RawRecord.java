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
package org.typelibrary.dns;

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class RawRecord extends Record {

    private final byte[] data;

    public RawRecord(String name, RecordType recordType, int recordClass, long timeToLive, byte[] data, int dataLength) {
        this(name, recordType, recordClass, timeToLive, data, 0, dataLength);
    }

    public RawRecord(String name, RecordType recordType, int recordClass, long timeToLive, byte[] data,
            int offset, int length) {
        super(name, recordType, recordClass, timeToLive);
        if (data == null)
            throw new IllegalArgumentException("Data cannot be null");
        this.data = new byte[length];
        System.arraycopy(data, offset, this.data, 0, length);
    }

    public RawRecord(String name, RecordType recordType, int recordClass, long timeToLive,
            ByteBuffer src, int length) {
        super(name, recordType, recordClass, timeToLive);
        if (src == null)
            throw new IllegalArgumentException("Src cannot be null");
        this.data = new byte[length];
        src.get(data, 0, length);
    }

    public final int getDataLength() {
        return data.length;
    }
    
    public final byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public final ByteBuffer getDataAsByteBuffer() {
        return ByteBuffer.wrap(data).asReadOnlyBuffer();
    }

    public final void copyData(byte[] buffer, int offset) {
        System.arraycopy(data, 0, buffer, offset, data.length);
    }

    public final String toString() {
        return super.toString() + ", len=" + data.length;
    }

}
