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

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.PublicKeyAlgorithm;

public class HIPRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final PublicKeyAlgorithm algorithm = PublicKeyAlgorithm.DSA;
        final ByteString hit = ByteString.from(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final ByteString publicKey = ByteString.from(3, 4, 5, 6, 7, 8);
        final Name server1 = Name.fromString("www.google.com.");
        final Name server2 = Name.fromString("www.microsoft.com.");
        final Name server3 = Name.fromString("www.apple.com.");
        
        HIPRecord r = new HIPRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm,
                hit, publicKey, Arrays.asList(server1, server2, server3));
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(algorithm, r.getAlgorithm());
        List<Name> servers = r.getRendezvousServers();
        Assert.assertNotNull(servers);
        Assert.assertEquals(3, servers.size());
        Assert.assertEquals(server1, servers.get(0));
        Assert.assertEquals(server2, servers.get(1));
        Assert.assertEquals(server3, servers.get(2));

    }

}
