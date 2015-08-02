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
 * A Location record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc1876">RFC 1876</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>29</td></tr>
 * <tr><td>Meaning:</td><td>Transaction Key</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>VERSION</td><td>uint8</td><td></td></tr>
 * <tr><td>SIZE</td><td>8-bits</td><td></td></tr>
 * <tr><td>HORIZ PRE</td><td>uint8</td><td>Horizontal Precision</td></tr>
 * <tr><td>VERT PRE</td><td>uint8</td><td>Vertical Precision</td></tr>
 * <tr><td>LATITUTE</td><td>uint32</td><td></td></tr>
 * <tr><td>LONGITUDE</td><td>uint32</td><td></td></tr>
 * <tr><td>ALTITUDE</td><td>uint32</td><td></td></tr>
 * </table>
 * </p>
 *
 */
public final class LOCRecord extends Record {

    private final byte version;
    private final byte size;
    private final byte horizontalPrecision;
    private final byte verticalPrecision;
    private final int latitude;
    private final int longitude;
    private final int altitude;

    public LOCRecord(Name name, short recordClass, int timeToLive, byte version, byte size,
            byte horizontalPrecision, byte verticalPrecision, int latitude, int longitude,
            int altitude) {
        super(name, RecordType.LOC, recordClass, timeToLive);
        this.version = version;
        this.size = size;
        this.horizontalPrecision = horizontalPrecision;
        this.verticalPrecision = verticalPrecision;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public byte getVersion() {
        return version;
    }

    public byte getSize() {
        return size;
    }

    public byte getHorizontalPrecision() {
        return horizontalPrecision;
    }

    public byte getVerticalPrecision() {
        return verticalPrecision;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    @Override
    public String toString() {
        return super.toString() + ", version=" + version + ", size=" + size + ", horizontalPrecision="
                + horizontalPrecision + ", verticalPrecision=" + verticalPrecision + ", latitude="
                + latitude + ", longitude=" + longitude + ", altitude=" + altitude;
    }

}
