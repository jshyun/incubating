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
 * A mailbox or mail list information record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Experimental</td></tr>
 * <tr><td>TYPE:</td><td>14</td></tr>
 * <tr><td>Meaning:</td><td>Mailbox or Mail List Information</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>RMAILBX</td><td>domain-name</td><td>A mailbox which is responsible for the mailing list or mailbox</td></tr>
 * <tr><td>EMAILBX</td><td>domain-name</td><td>A mailbox which is to receive error messages related to the mailing list or mailbox specified by the owner of the MINFO RR.</td></tr>
 * </table>
 * </p>
 *
 */
public final class MINFORecord extends Record {

    private final Name rMailbox;
    private final Name eMailbox;
    
    public MINFORecord(Name name, short recordClass, int timeToLive, Name rMailbox, Name eMailbox) {
        super(name, RecordType.MR, recordClass, timeToLive);
        if (rMailbox == null)
            throw new IllegalArgumentException("R Mailbox cannot be null");
        if (eMailbox == null)
            throw new IllegalArgumentException("E Mailbox cannot be null");
        this.rMailbox = rMailbox;
        this.eMailbox = eMailbox;
    }

    public final Name getRMailbox() {
        return rMailbox;
    }

    public final Name getEMailbox() {
        return eMailbox;
    }
    
    public final String toString() {
        return super.toString() + ", rMailbox=" + rMailbox + ", eMailbox=" + eMailbox;
    }

}
