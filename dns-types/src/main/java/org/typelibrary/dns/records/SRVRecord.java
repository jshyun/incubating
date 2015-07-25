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
