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

import org.typelibrary.dns.RecordType;
import org.typelibrary.dns.Record;

public final class PTRRecord extends Record {

    private final String domainName;

    public PTRRecord(String name, int recordClass, long timeToLive, String domainName) {
        super(name, RecordType.PTR, recordClass, timeToLive);
        if (domainName == null)
            throw new IllegalArgumentException("Domain name cannot be null");
        this.domainName = domainName;
    }

    public final String getDomainName() {
        return domainName;
    }

    public final String toString() {
        return super.toString() + ", domainName=" + domainName;
    }

}
