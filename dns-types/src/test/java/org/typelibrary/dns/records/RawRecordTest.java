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
import org.typelibrary.dns.RawRecord;
import org.typelibrary.dns.RecordType;

public class RawRecordTest extends AbstractRecordTest {

    private final int STD_LEN = 32;
    private final ByteString STD_DATA = createTestByteArray(STD_LEN);
    private final RecordType STD_TYPE = new RecordType(1000, "BOGUS");

    @Test
    public void testBasic() {
        RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, STD_DATA);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        ByteString buffer = r.getData();
        Assert.assertEquals(STD_DATA, buffer);
        Assert.assertEquals(STD_LEN, buffer.length());
    }

    @Test
    public void testEmpty() {
        RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, ByteString.EMPTY_STRING);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        ByteString buffer = r.getData();
        Assert.assertEquals(ByteString.EMPTY_STRING, buffer);
        Assert.assertEquals(0, buffer.length());
    }
    
    @Test
    public void testInvalidBuffer() {
        try {
            RawRecord r = new RawRecord(STD_NAME, STD_TYPE, STD_CLASS, STD_TTL, null);
            Assert.fail("Expected exception because buffer is null. r=" + r);
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

}
