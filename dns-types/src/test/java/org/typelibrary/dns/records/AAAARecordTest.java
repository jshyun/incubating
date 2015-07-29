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

public class AAAARecordTest extends AbstractRecordTest {

    private final ByteString STD_ADDRESS = createTestByteArray(16);

    @Test 
    public void testBasic() {

        AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, STD_ADDRESS);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        ByteString buffer = r.getAddress();
        Assert.assertEquals(STD_ADDRESS, buffer);

        InetAddress addr = r.toInetAddress(STD_NAME);
        Assert.assertEquals(STD_NAME.toString(), addr.getHostName());
        Assert.assertEquals(STD_ADDRESS, new ByteString(addr.getAddress()));

        String string = "name="+STD_NAME+", type="+RecordType.AAAA+", class="+STD_CLASS+", ttl="+STD_TTL+", address=";
        String ip6Addr = "[0001:0203:0405:0607:0809:0A0B:0C0D:0E0F]";
        Assert.assertEquals(string + ip6Addr, r.toString());
        
        try {
            new AAAARecord(null, STD_CLASS, STD_TTL, STD_ADDRESS);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }
    
    @Test
    public void testAddressTooShort() {
        try {
            ByteString address = ByteString.from( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 );
            AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, address);
            Assert.fail("Expected exception because address is too short. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddressTooLong() {
        try {
            ByteString address = ByteString.from( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 );
            AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, address);
            Assert.fail("Expected exception because address is too long. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddressNull() {
        try {
            ByteString address = null;
            AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, address);
            Assert.fail("Expected exception because address is null. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

}
