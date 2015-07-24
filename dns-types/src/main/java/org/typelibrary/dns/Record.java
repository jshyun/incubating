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
package org.typelibrary.dns;

public abstract class Record {

    protected final Name name;
    protected final RecordType recordType;
    protected final short recordClass;
    protected final int timeToLive;

    public Record(Name name, RecordType recordType, short recordClass, int timeToLive) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        if (recordType == null)
            throw new IllegalArgumentException("Record type cannot be null");
        this.name = name;
        this.recordType = recordType;
        this.recordClass = recordClass;
        this.timeToLive = timeToLive;
    }

    public final Name getName() {
        return name;
    }
    
    public final RecordType getRecordType() {
        return recordType;
    }
    
    public final short getRecordClass() {
        return recordClass;
    }
    
    public final int getTimeToLive() {
        return timeToLive;
    }
    
    public String toString() {
        return "name=" + name + ", type=" + recordType + ", class=" + recordClass
                + ", ttl=" + timeToLive;
    }

}
