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

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;
import org.typelibrary.dns.RecordType;
import org.typelibrary.dns.RawRecord;

public class RawRecordTest extends AbstractRecordTest {

    private final int STD_LEN = 32;
    private final byte[] STD_DATA = createTestByteArray(STD_LEN);
    private final RecordType STD_TYPE = new RecordType(1000, "BOGUS");

    @Test
    public void testBasic() {

        RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, STD_DATA, 0, STD_LEN);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);

        byte[] buffer = r.getData();
        Assert.assertArrayEquals(STD_DATA, buffer);

        // Roundabout way of making sure inner array not accessible
        Assert.assertNotEquals("Should be new instance each invocation.", buffer, r.getData());

        buffer = new byte[STD_LEN];
        r.copyData(buffer, 0);
        Assert.assertArrayEquals(STD_DATA, buffer);

        buffer = new byte[STD_LEN + STD_LEN];
        r.copyData(buffer, 17);
        assertArrayRegionEquals(STD_DATA, 0, buffer, 17, STD_LEN);

        ByteBuffer bb = r.getDataAsByteBuffer();
        Assert.assertTrue("ByteBuffer must be read-only", bb.isReadOnly());
        Assert.assertEquals(STD_LEN, bb.remaining());

        buffer = new byte[STD_LEN];
        bb.get(buffer);
        Assert.assertArrayEquals(STD_DATA, buffer);

    }

    @Test
    public void testInvalidClass() {
        try {
            RawRecord r = new RawRecord(STD_NAME, STD_TYPE, 0x01FFFF, STD_TTL, STD_DATA, 0, STD_LEN);
            Assert.fail("Expected exception because class is too large. r=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
    
    @Test
    public void testInvalidTtl() {
        try {
            RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, 0x01FFFFFFFFL, STD_DATA, 0, STD_LEN);
            Assert.fail("Expected exception because ttl is too large. r=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testInvalidBuffer() {
        try {
            RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, null, 0, STD_LEN);
            Assert.fail("Expected exception because buffer is null. r=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testInvalidBufferUsage1() {
        try {
            RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, STD_DATA, 1, STD_LEN);
            Assert.fail("Expected exception because buffer parameters invalid. r=" + r);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    public void testInvalidBufferUsage2() {
        try {
            RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, STD_DATA, 0, STD_LEN + 1);
            Assert.fail("Expected exception because buffer parameters invalid. r=" + r);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected
        }
    }

}
