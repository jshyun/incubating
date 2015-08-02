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
 * A Host Information record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Not Common</td></tr>
 * <tr><td>TYPE:</td><td>12</td></tr>
 * <tr><td>Meaning:</td><td>Host Information</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>CPU</td><td>character-string</td><td>The CPU type</td></tr>
 * <tr><td>OS</td><td>character-string</td><td>The operating system type</td></tr>
 * </table>
 * </p>
 *
 */
public class HINFORecord extends Record {

    private final ByteString cpu;
    private final ByteString os;

    public HINFORecord(Name name, short recordClass, int timeToLive, ByteString cpu, ByteString os) {
        super(name, RecordType.HINFO, recordClass, timeToLive);
        if (cpu == null)
            throw new IllegalArgumentException("CPU cannot be null");
        if (os == null)
            throw new IllegalArgumentException("OS cannot be null");
        this.cpu = cpu;
        this.os = os;
    }

    public final ByteString getCpu() {
        return cpu;
    }

    public final ByteString getOs() {
        return os;
    }

    public final String toString() {
        return super.toString() + ", cpu=" + cpu + ", os=" + os;
    }
    
}
