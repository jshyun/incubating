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
 * A Services record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc2782">RFC 2782</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>33</td></tr>
 * <tr><td>Meaning:</td><td>Location of services</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>priority</td><td>uint16</td><td></td></tr>
 * <tr><td>weight</td><td>uint16</td><td></td></tr>
 * <tr><td>port</td><td>uint16</td><td></td></tr>
 * <tr><td>target</td><td>domain-name</td><td>Target</td></tr>
 * </table>
 * </p>
 *
 */
public final class SRVRecord extends Record {

    private final short priority;
    private final short weight;
    private final short port;
    private final Name target;

    public SRVRecord(Name name, short recordClass, int timeToLive, short priority,
            short weight, short port, Name target) {
        super(name, RecordType.SRV, recordClass, timeToLive);
        if (target == null)
            throw new IllegalArgumentException("Target cannot be null.");
        this.priority = priority;
        this.weight = weight;
        this.port = port;
        this.target = target;
    }

    public short getPriority() {
        return priority;
    }

    public short getWeight() {
        return weight;
    }

    public short getPort() {
        return port;
    }

    public Name getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return super.toString() + ", priority=" + priority + ", weight=" + weight + ", port=" + port
                + ", target=" + target + "]";
    }

}
