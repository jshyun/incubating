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
 * A Null record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Experimental</td></tr>
 * <tr><td>TYPE:</td><td>10</td></tr>
 * <tr><td>Meaning:</td><td>A "null" RR</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Anything</td><td>octets</td><td>Anything so long as it is 65535 octets or less.</td></tr>
 * </table>
 * </p>
 *
 */
public final class NULLRecord extends Record {

    private final ByteString data;

    public NULLRecord(Name name, short recordClass, int timeToLive, ByteString data) {
        super(name, RecordType.NULL, recordClass, timeToLive);
        if (data == null)
            throw new IllegalArgumentException("Data cannot be null");
        this.data = data;
    }

    public final ByteString getData() {
        return data;
    }

    public final String toString() {
        return super.toString() + ", data=" + data;
    }

}
