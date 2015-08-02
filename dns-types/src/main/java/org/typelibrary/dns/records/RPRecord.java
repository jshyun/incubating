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
 * A Responsible Person record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc1183">RFC 1183</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>17</td></tr>
 * <tr><td>Meaning:</td><td>A Responsible Person</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>mbox-dname</td><td>domain-name</td><td>Mailbox of the responsible person.</td></tr>
 * <tr><td>txt-dname</td><td>domain-name</td><td>A domain name for which TXT records exist.</td></tr>
 * </table>
 * </p>
 *
 */
public final class RPRecord extends Record {

    private final Name mailbox;
    private final Name domain;

    public RPRecord(Name name, short recordClass, int timeToLive, Name mailbox,
            Name domain) {
        super(name, RecordType.RP, recordClass, timeToLive);
        if (mailbox == null)
            throw new IllegalArgumentException("Mailbox cannot be null");
        if (domain == null)
            throw new IllegalArgumentException("Domain cannot be null");
        this.mailbox = mailbox;
        this.domain = domain;
    }

    public final Name getMailbox() {
        return mailbox;
    }

    public final Name getDomain() {
        return domain;
    }
    
    public final String toString() {
        return super.toString() + ", mbox=" + mailbox + ", domain=" + domain;
    }

}
