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
 * A Route Through record.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc1183">RFC 1183</a></td></tr>
 * <tr><td>Status:</td><td>Not Common</td></tr>
 * <tr><td>TYPE:</td><td>21</td></tr>
 * <tr><td>Meaning:</td><td>A Route Through record.</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>preference</td><td>uint16</td><td>Preference of the route. Smaller numbers indicate more preferred routes.</td></tr>
 * <tr><td>intermediate-host</td><td>domain-name</td><td>A host which will serve as an intermediate in reaching the host specified by the owner.</td></tr>
 * </table>
 * </p>
 *
 */
public final class RTRecord extends Record {

    private final short preference;
    private final Name intermediateHost;

    public RTRecord(Name name, short recordClass, int timeToLive, short preference, Name intermediateHost) {
        super(name, RecordType.RT, recordClass, timeToLive);
        if (intermediateHost == null)
            throw new IllegalArgumentException("Intermediate host cannot be null");
        this.preference = preference;
        this.intermediateHost = intermediateHost;
    }

    public final short getPreference() {
        return preference;
    }

    public final Name getIntermediateHost() {
        return intermediateHost;
    }

    public final String toString() {
        return super.toString() + ", preference=" + preference + ", intermediateHost="
                + intermediateHost;
    }

}
