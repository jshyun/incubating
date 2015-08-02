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
 * An SSH Fingerprint record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="https://tools.ietf.org/html/rfc4255">RFC 4255</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>44</td></tr>
 * <tr><td>Meaning:</td><td>SSH Fingerprint</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>algorithm</td><td>8-bits</td><td></td></tr>
 * <tr><td>fp type</td><td>8-bits</td><td></td></tr>
 * <tr><td>Fingerprint</td><td>{octets}*</td><td></td></tr>
 * </table>
 * </p>
 *
 */
public final class SSHFPRecord extends Record {

    private final byte algorithm;
    private final byte fpType;
    private final ByteString fingerprint;

    public SSHFPRecord(Name name, short recordClass, int timeToLive, byte algorithm,
            byte fpType, ByteString fingerprint) {
        super(name, RecordType.SSHFP, recordClass, timeToLive);
        if (fingerprint == null)
            throw new IllegalArgumentException("Fingerprint cannot be null.");
        this.algorithm = algorithm;
        this.fpType = fpType;
        this.fingerprint = fingerprint;
    }

    public byte getAlgorithm() {
        return algorithm;
    }

    public byte getFpType() {
        return fpType;
    }

    public ByteString getFingerprint() {
        return fingerprint;
    }

    @Override
    public String toString() {
        return super.toString() + ", algorithm=" + algorithm + ", fpType=" + fpType + ", fingerprint="
                + fingerprint + "]";
    }

}
