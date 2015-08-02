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
 * A mail destination.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Obsolete</td></tr>
 * <tr><td>TYPE:</td><td>3</td></tr>
 * <tr><td>Meaning:</td><td>Mail Destination</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>MADNAME</td><td>domain-name</td><td>A host which has a mail agent for the domain which should be able to deliver mail for the domain.</td></tr>
 * </table>
 * </p>
 *
 */
public final class MDRecord extends Record {

    private final Name mailDestination;

    public MDRecord(Name name, short recordClass, int timeToLive, Name mailDestination) {
        super(name, RecordType.MD, recordClass, timeToLive);
        if (mailDestination == null)
            throw new IllegalArgumentException("Domain name cannot be null");
        this.mailDestination = mailDestination;
    }

    public final Name getMailDestination() {
        return mailDestination;
    }

    public final String toString() {
        return super.toString() + ", mailDestination=" + mailDestination;
    }

}
