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
