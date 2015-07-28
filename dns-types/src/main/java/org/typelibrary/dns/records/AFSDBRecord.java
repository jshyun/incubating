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

import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

public final class AFSDBRecord extends Record {

    private final short subtype;
    private final Name hostname;

    public AFSDBRecord(Name name, short recordClass, int timeToLive, short subtype,
            Name hostname) {
        super(name, RecordType.AFSDB, recordClass, timeToLive);
        if (hostname == null)
            throw new IllegalArgumentException("Hostname cannot be null");
        this.subtype = subtype;
        this.hostname = hostname;
    }

    public final short getSubtype() {
        return subtype;
    }

    public final Name getHostname() {
        return hostname;
    }
    
    public final String toString() {
        return super.toString() + ", subtype=" + subtype + ", hostname=" + hostname;
    }

}