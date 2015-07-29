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
import org.typelibrary.dns.PublicKeyAlgorithm;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

public final class HIPRecord extends Record {

    private final PublicKeyAlgorithm algorithm;
    private final ByteString hit;
    private final ByteString publicKey;
    private final List<Name> rendezvousServers;

    public HIPRecord(Name name, short recordClass, int timeToLive, PublicKeyAlgorithm algorithm,
            ByteString hit, ByteString publicKey, List<Name> rendezvousServers) {
        super(name, RecordType.HIP, recordClass, timeToLive);
        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null.");
        if (hit == null)
            throw new IllegalArgumentException("Hit cannot be null.");
        if (hit.length() > 255)
            throw new IllegalArgumentException("Hit cannot be > 255 bytes.");
        if (publicKey == null)
            throw new IllegalArgumentException("Public key cannot be null.");
        if (publicKey.length() > 65535)
            throw new IllegalArgumentException("Public key cannot be > 65535 bytes.");
        if (rendezvousServers == null)
            throw new IllegalArgumentException("Rendezvous servers list cannot be null.");
        this.algorithm = algorithm;
        this.hit = hit;
        this.publicKey = publicKey;
        this.rendezvousServers = Collections.unmodifiableList(new ArrayList<Name>(rendezvousServers));
    }

    public PublicKeyAlgorithm getAlgorithm() {
        return algorithm;
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
        return super.toString() + ", algorithm=" + algorithm 
                + ", hit=" + hit + ", publicKey=" + publicKey + ", rendezvousServers="
                + rendezvousServers;
    }

}
