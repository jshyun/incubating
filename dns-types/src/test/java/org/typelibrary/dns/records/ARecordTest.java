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

import java.net.InetAddress;

import org.junit.Assert;
import org.junit.Test;
import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.RecordType;

public class ARecordTest extends AbstractRecordTest {

    private final ByteString STD_ADDRESS = createTestByteArray(4);

    @Test 
    public void testBasic() {

        ARecord r = new ARecord(STD_NAME, STD_CLASS, STD_TTL, STD_ADDRESS);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        ByteString buffer = r.getAddress();
        Assert.assertEquals(STD_ADDRESS, buffer);

        InetAddress addr = r.toInetAddress(STD_NAME);
        Assert.assertEquals(STD_NAME.toString(), addr.getHostName());
        Assert.assertEquals(STD_ADDRESS, new ByteString(addr.getAddress()));

        String string = "name="+STD_NAME+", type="+RecordType.A+", class="+STD_CLASS+", ttl="+STD_TTL+", address=";
        Assert.assertEquals(string + "0.1.2.3", r.toString());
    }

    @Test
    public void testAddressTooShort() {
        try {
            ByteString address = ByteString.from( 1, 2, 3 );
            ARecord r = new ARecord(STD_NAME, STD_CLASS, STD_TTL, address);
            Assert.fail("Expected exception because address is too short. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddressTooLong() {
        try {
            ByteString address = ByteString.from( 1, 2, 3, 4, 5 );
            ARecord r = new ARecord(STD_NAME, STD_CLASS, STD_TTL, address);
            Assert.fail("Expected exception because address is too long. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddressNull() {
        try {
            ByteString address = null;
            ARecord r = new ARecord(STD_NAME, STD_CLASS, STD_TTL, address);
            Assert.fail("Expected exception because address is too short. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

}
