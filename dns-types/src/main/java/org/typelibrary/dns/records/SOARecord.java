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

public final class SOARecord extends Record {

    private final String masterName;
    private final String responsibleName;
    private final long serialNumber;
    private final long refreshInterval;
    private final long retryInterval;
    private final long expireInterval;
    private final long minimum;

    public SOARecord(String name, int recordClass, long timeToLive, String masterName,
            String responsibleName, long serialNumber, long refreshInterval, long retryInterval,
            long expireInterval, long minimum) {
        super(name, RecordType.SOA, recordClass, timeToLive);
        if (masterName == null)
            throw new IllegalArgumentException("Master name cannot be null");
        if (responsibleName == null)
            throw new IllegalArgumentException("Responsible name cannot be null");
        if (serialNumber >> 32 != 0)
            throw new IllegalArgumentException("Serial number must be a 32-bit value. serialNumber=" + serialNumber);
        if (refreshInterval >> 32 != 0)
            throw new IllegalArgumentException("Refresh interval must be a 32-bit value. refreshInterval=" + refreshInterval);
        if (retryInterval >> 32 != 0)
            throw new IllegalArgumentException("Retry interval must be a 32-bit value. retryInterval=" + retryInterval);
        if (expireInterval >> 32 != 0)
            throw new IllegalArgumentException("Expire interval must be a 32-bit value. expireInterval=" + expireInterval);
        if (minimum >> 32 != 0)
            throw new IllegalArgumentException("Minimum must be a 32-bit value. minimum=" + minimum);
        this.masterName = masterName;
        this.responsibleName = responsibleName;
        this.serialNumber = serialNumber;
        this.refreshInterval = refreshInterval;
        this.retryInterval = retryInterval;
        this.expireInterval = expireInterval;
        this.minimum = minimum;
    }

    public final String getMasterName() {
        return masterName;
    }

    public final String getResponsibleName() {
        return responsibleName;
    }

    public final long getSerialNumber() {
        return serialNumber;
    }

    public final long getRefreshInterval() {
        return refreshInterval;
    }

    public final long getRetryInterval() {
        return retryInterval;
    }

    public final long getExpireInterval() {
        return expireInterval;
    }

    public final long getMinimum() {
        return minimum;
    }

    public final String toString() {
        return super.toString() + ", mname=" + masterName + ", rname=" + responsibleName + ", sn="
                + serialNumber + ", refresh=" + refreshInterval + ", retry=" + retryInterval
                + ", expire=" + expireInterval + ", min=" + minimum;
    }

}
