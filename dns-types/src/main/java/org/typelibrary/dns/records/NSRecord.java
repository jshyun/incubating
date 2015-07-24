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

public final class NSRecord extends Record {

    private final Name nameServer;

    public NSRecord(Name name, short recordClass, int timeToLive, Name nameServer) {
        super(name, RecordType.NS, recordClass, timeToLive);
        if (nameServer== null)
            throw new IllegalArgumentException("Name server cannot be null");
        this.nameServer = nameServer;
    }

    public final Name getNameServer() {
        return nameServer;
    }

    public final String toString() {
        return super.toString() + ", ns=" + nameServer;
    }

}
