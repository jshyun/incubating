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

public final class RRRecord extends Record {

    private final short preference;
    private final Name intermediateHost;

    public RRRecord(Name name, short recordClass, int timeToLive, short preference,
            Name intermediateHost) {
        super(name, RecordType.MX, recordClass, timeToLive);
        if (intermediateHost == null)
            throw new IllegalArgumentException("Intermediate host cannot be null");
        this.preference = preference;
        this.intermediateHost = intermediateHost;
    }

    public final short getPreference() {
        return preference;
    }

    public final Name getIntermediateHost() {
        return intermediateHost;
    }

    public final String toString() {
        return super.toString() + ", preference=" + preference + ", intermediateHost=" + intermediateHost;
    }

}
