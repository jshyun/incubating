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

public final class KXRecord extends Record {

    private final short preference;
    private final Name exchange;

    public KXRecord(Name name, short recordClass, int timeToLive, short preference, Name exchange) {
        super(name, RecordType.KX, recordClass, timeToLive);
        if (exchange == null)
            throw new IllegalArgumentException("Exchange cannot be null");
        this.preference = preference;
        this.exchange = exchange;
    }

    public final short getPreference() {
        return preference;
    }

    public final Name getExchange() {
        return exchange;
    }

    public final String toString() {
        return super.toString() + ", preference=" + preference + ", exchange=" + exchange;
    }

}
