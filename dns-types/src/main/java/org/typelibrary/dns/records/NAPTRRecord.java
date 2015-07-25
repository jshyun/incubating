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

public final class NAPTRRecord extends Record {

    private final short order;
    private final short preference;
    private final ByteString flags;
    private final ByteString services;
    private final ByteString regexp;
    private final Name replacement;

    public NAPTRRecord(Name name, short recordClass, int timeToLive, short order, short preference,
            ByteString flags, ByteString services, ByteString regexp, Name replacement) {
        super(name, RecordType.NAPTR, recordClass, timeToLive);
        if (flags == null)
            throw new IllegalArgumentException("Flags cannot be null.");
        if (services == null)
            throw new IllegalArgumentException("Services cannot be null.");
        if (regexp == null)
            throw new IllegalArgumentException("Regexp cannot be null.");
        if (replacement == null)
            throw new IllegalArgumentException("Replacement cannot be null.");
        this.order = order;
        this.preference = preference;
        this.flags = flags;
        this.services = services;
        this.regexp = regexp;
        this.replacement = replacement;
    }

    public short getOrder() {
        return order;
    }

    public short getPreference() {
        return preference;
    }

    public ByteString getFlags() {
        return flags;
    }

    public ByteString getServices() {
        return services;
    }

    public ByteString getRegexp() {
        return regexp;
    }

    public Name getReplacement() {
        return replacement;
    }

    @Override
    public String toString() {
        return super.toString() + ", order=" + order + ", preference=" + preference + ", flags="
                + flags + ", services=" + services + ", regexp=" + regexp + ", replacement="
                + replacement + "]";
    }

}
