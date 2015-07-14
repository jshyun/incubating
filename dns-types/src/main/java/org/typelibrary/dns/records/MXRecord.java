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

import org.typelibrary.dns.RecordType;
import org.typelibrary.dns.Record;

public final class MXRecord extends Record {

    private final int preference;
    private final String exchange;

    public MXRecord(String name, int recordClass, long timeToLive, int preference, String exchange) {
        super(name, RecordType.MX, recordClass, timeToLive);
        if (preference >> 16 != 0)
            throw new IllegalArgumentException("Preference must be a 16-bit value. class=" + preference);
        if (exchange == null)
            throw new IllegalArgumentException("Exchange cannot be null");
        this.preference = preference;
        this.exchange = exchange;
    }

    public final int getPreference() {
        return preference;
    }

    public final String getExchange() {
        return exchange;
    }

    public final String toString() {
        return super.toString() + ", preference=" + preference + ", exchange=" + exchange;
    }

}
