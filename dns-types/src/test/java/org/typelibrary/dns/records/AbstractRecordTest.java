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

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.typelibrary.dns.Record;

public class AbstractRecordTest {

    protected final String STD_NAME = "Name.";
    protected final int STD_CLASS = 1;
    protected final long STD_TTL = TimeUnit.MINUTES.toMillis(5);

    protected void assertBaseRecord(String name, int rclass, long ttl, Record r) {
        Assert.assertEquals(name, r.getName());
        Assert.assertEquals(rclass, r.getRecordClass());
        Assert.assertEquals(ttl, r.getTimeToLive());
    }
    
    protected void assertArrayRegionEquals(byte[] buf1, int offset1, byte[] buf2, int offset2, int length) {
        for (int i=0,index1=offset1,index2=offset2; i<length; ++i, ++index1, ++index2) {
            Assert.assertEquals("Arrays not equal. src=" + buf1[index1] + ", index=" + index1
                    + "dst=" + buf2[index2] + ", index=" + index2, buf1[index1], buf2[index2]);
        }
    }

    static byte[] createTestByteArray(int length) {
        byte[] array = new byte[length];
        for(int i=0; i<length; ++i) {
            array[i] = (byte)i;
        }
        return array;
    }
}
