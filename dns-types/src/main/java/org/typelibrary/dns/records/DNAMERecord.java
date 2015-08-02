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
 * A Non-Terminal DNS Name Redirection record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc2672">RFC 2672</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>39</td></tr>
 * <tr><td>Meaning:</td><td>Non-Terminal DNS Name Redirection</td></tr>
 * </table>
 * </p>
 *
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>target</td><td>domain-name</td><td>Target.</td></tr>
 * </table>
 * </p>
 * 
 */
public final class DNAMERecord extends Record {

    private final Name target;

    public DNAMERecord(Name name, short recordClass, int timeToLive, Name target) {
        super(name, RecordType.DNAME, recordClass, timeToLive);
        if (target == null)
            throw new IllegalArgumentException("Target cannot be null");
        this.target = target;
    }

    public final Name getTarget() {
        return target;
    }

    public final String toString() {
        return super.toString() + ", target=" + target;
    }

}
