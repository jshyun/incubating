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

public class TXTRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final ByteString txt1 = ByteString.from(1,2,3,4,5,6);
        final ByteString txt2 = ByteString.from(7,8,9);
        final ByteString txt3 = ByteString.from(10,11,12,13,14,15,16);
        
        TXTRecord r = new TXTRecord(STD_NAME, STD_CLASS, STD_TTL, Arrays.asList(txt1, txt2, txt3));
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        List<ByteString> txts = r.getText();
        Assert.assertEquals(3, txts.size());
        Assert.assertEquals(txt1, txts.get(0));
        Assert.assertEquals(txt2, txts.get(1));
        Assert.assertEquals(txt3, txts.get(2));

        try {
            new TXTRecord(null, STD_CLASS, STD_TTL, Arrays.asList(txt1, txt2, txt3));
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }
        
        try {
            new TXTRecord(STD_NAME, STD_CLASS, STD_TTL, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
