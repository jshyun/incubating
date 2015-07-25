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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

public final class HIPRecord extends Record {

    private final byte hitLength;
    private final byte algorithm;
    private final short pkLength;
    private final ByteString hit;
    private final ByteString publicKey;
    private final List<Name> rendezvousServers;

    public HIPRecord(Name name, short recordClass, int timeToLive, byte hitLength, byte algorithm,
            short pkLength, ByteString hit, ByteString publicKey, List<Name> rendezvousServers) {
        super(name, RecordType.HIP, recordClass, timeToLive);
        this.hitLength = hitLength;
        this.algorithm = algorithm;
        this.pkLength = pkLength;
        this.hit = hit;
        this.publicKey = publicKey;
        this.rendezvousServers = Collections.unmodifiableList(new ArrayList<Name>(rendezvousServers));
    }

    public byte getHitLength() {
        return hitLength;
    }

    public byte getAlgorithm() {
        return algorithm;
    }

    public short getPkLength() {
        return pkLength;
    }

    public ByteString getHit() {
        return hit;
    }

    public ByteString getPublicKey() {
        return publicKey;
    }

    public List<Name> getRendezvousServers() {
        return rendezvousServers;
    }

    @Override
    public String toString() {
        return super.toString() + ", hitLength=" + hitLength + ", algorithm=" + algorithm + ", pkLength="
                + pkLength + ", hit=" + hit + ", publicKey=" + publicKey + ", rendezvousServers="
                + rendezvousServers + "]";
    }

}
