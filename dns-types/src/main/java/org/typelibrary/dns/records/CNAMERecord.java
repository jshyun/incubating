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
 * The canonical name for an alias.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>5</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>CNAME</td><td>domain-name</td><td>A domain name which specifies the canonical or primary name for the owner. The owner name is an alias.</td></tr>
 * </table>
 * </p>
 * 
 */
public final class CNAMERecord extends Record {

    private final Name cname;

    public CNAMERecord(Name name, short recordClass, int timeToLive, Name cname) {
        super(name, RecordType.CNAME, recordClass, timeToLive);
        if (cname == null)
            throw new IllegalArgumentException("CName cannot be null");
        this.cname = cname;
    }

    public final Name getCName() {
        return cname;
    }

    public final String toString() {
        return super.toString() + ", cname=" + cname;
    }

}
