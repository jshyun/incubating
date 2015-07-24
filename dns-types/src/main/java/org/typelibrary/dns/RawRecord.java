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

import org.typelibrary.binarystrings.ByteString;

public final class RawRecord extends Record {

    private final ByteString data;

    public RawRecord(Name name, RecordType recordType, short recordClass, int timeToLive, ByteString data) {
        super(name, recordType, recordClass, timeToLive);
        if (data == null)
            throw new IllegalArgumentException("Data cannot be null");
        this.data = data;
    }

    public final int getDataLength() {
        return data.length();
    }
    
    public final ByteString getData() {
        return data;
    }

    public final String toString() {
        return super.toString() + ", len=" + data.length();
    }

}
