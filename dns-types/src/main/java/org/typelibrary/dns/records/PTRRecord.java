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
 * A domain name pointer.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>12</td></tr>
 * <tr><td>Meaning:</td><td>Domain Name Pointer</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>PTRDNAME</td><td>domain-name</td><td>Some location in the domain name space.</td></tr>
 * </table>
 * </p>
 *
 */
public final class PTRRecord extends Record {

    private final Name domainName;

    public PTRRecord(Name name, short recordClass, int timeToLive, Name domainName) {
        super(name, RecordType.PTR, recordClass, timeToLive);
        if (domainName == null)
            throw new IllegalArgumentException("Domain name cannot be null");
        this.domainName = domainName;
    }

    public final Name getDomainName() {
        return domainName;
    }

    public final String toString() {
        return super.toString() + ", domainName=" + domainName;
    }

}
