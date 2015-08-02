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

/**
 * A mail exchange record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>15</td></tr>
 * <tr><td>Meaning:</td><td>Mail Exchange</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>PREFERENCE</td><td>uint16</td><td>The preference given to this record among others at the same owner. Lower values are preferred.</td></tr>
 * <tr><td>EXCHANGE</td><td>domain-name</td><td>A host willing to act as a mail exchange for the owner name.</td></tr>
 * </table>
 * </p>
 *
 */
public final class MXRecord extends Record {

    private final short preference;
    private final Name exchange;

    public MXRecord(Name name, short recordClass, int timeToLive, short preference, Name exchange) {
        super(name, RecordType.MX, recordClass, timeToLive);
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
