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
 * An NXT record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc2065.txt">RFC 2065</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>30</td></tr>
 * <tr><td>Meaning:</td><td>A NXT record</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Next Domain Name</td><td>Name</td><td>Next owner name (in the canonical ordering of the zone) that has authoritative data or contains a delegation point NS RRset</td></tr>
 * <tr><td>Type Bit Maps</td><td>Name</td><td>The RRset types that exist at the NSEC RR's owner name</td></tr>
 * </table>
 * </p>
 *
 */
public final class NXTRecord extends Record {

    private final Name nextDomainName;
    private final ByteString typeBitmap;

    public NXTRecord(Name name, short recordClass, int timeToLive, Name nextDomainName,
            ByteString typeBitmap) {
        super(name, RecordType.NXT, recordClass, timeToLive);
        if (nextDomainName == null)
            throw new IllegalArgumentException("Next domain name cannot be null.");
        if (typeBitmap == null)
            throw new IllegalArgumentException("Type bitmap cannot be null.");
        this.nextDomainName = nextDomainName;
        this.typeBitmap = typeBitmap;
    }

    public final Name getNextDomainName() {
        return nextDomainName;
    }

    public final ByteString getTypeBitmap() {
        return typeBitmap;
    }
    
    public final String toString() {
        return super.toString() + ", next=" + nextDomainName + ", typeBitmap=" + typeBitmap;
    }

}
