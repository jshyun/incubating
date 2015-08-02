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
 * A start of zone authority record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>6</td></tr>
 * <tr><td>Meaning:</td><td>Start of Zone Authority</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>MNAME</td><td>domain-name</td><td>The name server that was the original or primary source of data for this zone.</td></tr>
 * <tr><td>RNAME</td><td>domain-name</td><td>The mailbox of the person responsible for this zone.</td></tr>
 * <tr><td>SERIAL</td><td>uint32</td><td>Version number of the original copy of the zone.</td></tr>
 * <tr><td>REFRESH</td><td>int32</td><td>Time interval before the zone should be refreshed.</td></tr>
 * <tr><td>RETRY</td><td>int32</td><td>Time interval that should elapse before a failed refresh should be retried.</td></tr>
 * <tr><td>EXPIRE</td><td>int32</td><td>Time value that specifies the upper limit on the time interval that can elapse before the zone is no longer authoritative.</td></tr>
 * <tr><td>MINIMUM</td><td>uint32</td><td>TTL field that should be exported with any RR from this zone.</td></tr>
 * </table>
 * </p>
 *
 */
public final class SOARecord extends Record {

    private final Name masterName;
    private final Name responsibleName;
    private final int serialNumber;
    private final int refreshInterval;
    private final int retryInterval;
    private final int expireInterval;
    private final int minimum;

    public SOARecord(Name name, short recordClass, int timeToLive, Name masterName,
            Name responsibleName, int serialNumber, int refreshInterval, int retryInterval,
            int expireInterval, int minimum) {
        super(name, RecordType.SOA, recordClass, timeToLive);
        if (masterName == null)
            throw new IllegalArgumentException("Master name cannot be null");
        if (responsibleName == null)
            throw new IllegalArgumentException("Responsible name cannot be null");
        this.masterName = masterName;
        this.responsibleName = responsibleName;
        this.serialNumber = serialNumber;
        this.refreshInterval = refreshInterval;
        this.retryInterval = retryInterval;
        this.expireInterval = expireInterval;
        this.minimum = minimum;
    }

    public final Name getMasterName() {
        return masterName;
    }

    public final Name getResponsibleName() {
        return responsibleName;
    }

    public final int getSerialNumber() {
        return serialNumber;
    }

    public final int getRefreshInterval() {
        return refreshInterval;
    }

    public final int getRetryInterval() {
        return retryInterval;
    }

    public final int getExpireInterval() {
        return expireInterval;
    }

    public final int getMinimum() {
        return minimum;
    }

    public final String toString() {
        return super.toString() + ", mname=" + masterName + ", rname=" + responsibleName + ", sn="
                + serialNumber + ", refresh=" + refreshInterval + ", retry=" + retryInterval
                + ", expire=" + expireInterval + ", min=" + minimum;
    }

}
