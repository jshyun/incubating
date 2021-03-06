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
 * A mail group member.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Experimental</td></tr>
 * <tr><td>TYPE:</td><td>8</td></tr>
 * <tr><td>Meaning:</td><td>Mail Group Member</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>MGNAME</td><td>domain-name</td><td>A mailbox which is a member of the mail group specified by the domain name.</td></tr>
 * </table>
 * </p>
 *
 */
public final class MGRecord extends Record {

    private final Name mgmName;

    public MGRecord(Name name, short recordClass, int timeToLive, Name mgmName) {
        super(name, RecordType.MG, recordClass, timeToLive);
        if (mgmName == null)
            throw new IllegalArgumentException("MGM name cannot be null");
        this.mgmName = mgmName;
    }

    public final Name getMGMName() {
        return mgmName;
    }

    public final String toString() {
        return super.toString() + ", mgmName=" + mgmName;
    }

}
