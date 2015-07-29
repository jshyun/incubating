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

import org.junit.Assert;
import org.junit.Test;
import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.Algorithm;
import org.typelibrary.dns.Protocol;

public class KEYRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final short flags = 1;
        final Protocol protocol = Protocol.DNSSEC;
        final Algorithm algorithm = Algorithm.RSASHA1;
        final ByteString publicKey = ByteString.from(4, 5, 6, 7, 8, 9);
        
        KEYRecord r = new KEYRecord(STD_NAME, STD_CLASS, STD_TTL, flags, protocol,
                algorithm, publicKey);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(flags, r.getFlags());
        Assert.assertEquals(protocol, r.getProtocol());
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(publicKey, r.getPublicKey());

    }

}
