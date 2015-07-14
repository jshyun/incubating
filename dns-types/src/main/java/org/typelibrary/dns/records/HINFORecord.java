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

import org.typelibrary.dns.BinaryString;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

public class HINFORecord extends Record {

    private final BinaryString cpu;
    private final BinaryString os;

    public HINFORecord(String name, int recordClass, long timeToLive, BinaryString cpu, BinaryString os) {
        super(name, RecordType.CNAME, recordClass, timeToLive);
        if (cpu == null)
            throw new IllegalArgumentException("CPU cannot be null");
        if (os == null)
            throw new IllegalArgumentException("OS cannot be null");
        this.cpu = cpu;
        this.os = os;
    }

    public final BinaryString getCpu() {
        return cpu;
    }

    public final BinaryString getOs() {
        return os;
    }

    public final String toString() {
        return super.toString() + ", cpu=" + cpu + ", os=" + os;
    }
    
}
