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
import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;
import org.typelibrary.dns.RecordType;
import org.typelibrary.dns.records.AAAARecord;

public class AAARecordTest extends AbstractRecordTest {

    private final byte[] STD_ADDRESS = createTestByteArray(16);

    @Test 
    public void testBasic() {

        AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, STD_ADDRESS);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        byte[] buffer = r.getAddress();
        Assert.assertArrayEquals(STD_ADDRESS, buffer);

        // Roundabout way of making sure inner array not accessible
        Assert.assertNotEquals("Should be new instance each invocation.", buffer, r.getAddress());
        
        buffer = new byte[16];
        r.copyAddress(buffer, 0);
        Assert.assertArrayEquals(STD_ADDRESS, buffer);
        
        buffer = new byte[64];
        r.copyAddress(buffer, 9);
        assertArrayRegionEquals(STD_ADDRESS, 0, buffer, 9, 16);

        ByteBuffer bb = r.getAddressAsByteBuffer();
        Assert.assertTrue("ByteBuffer must be read-only", bb.isReadOnly());
        Assert.assertEquals(16, bb.remaining());
        
        buffer = new byte[16];
        bb.get(buffer);
        Assert.assertArrayEquals(STD_ADDRESS, buffer);
        
        InetAddress addr = r.toInetAddress(STD_NAME);
        Assert.assertEquals(STD_NAME, addr.getHostName());
        Assert.assertArrayEquals(STD_ADDRESS, addr.getAddress());

        String string = "name="+STD_NAME+", type="+RecordType.AAAA+", class="+STD_CLASS+", ttl="+STD_TTL+", address=";
        String ip6Addr = "[0001:0203:0405:0607:0809:0A0B:0C0D:0E0F]";
        Assert.assertEquals(string + ip6Addr, r.toString());
    }
    
    @Test
    public void testAddressTooShort() {
        try {
            AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, new byte[15]);
            Assert.fail("Expected exception because address is too short. addr=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddressInvalidRange() {
        try {
            AAAARecord r = new AAAARecord(STD_NAME, STD_CLASS, STD_TTL, STD_ADDRESS, 1);
            Assert.fail("Expected exception because address offset invalid. addr=" + r);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected
        }
    }

}
