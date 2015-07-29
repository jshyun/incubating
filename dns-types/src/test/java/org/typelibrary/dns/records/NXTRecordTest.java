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
import org.typelibrary.dns.Name;

public class NXTRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final Name nextName = Name.fromString("ns1.amazon.com.");
        final ByteString typeBitmap = ByteString.from(4, 5, 6, 7, 8, 9);

        NXTRecord r = new NXTRecord(STD_NAME, STD_CLASS, STD_TTL, nextName, typeBitmap);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(nextName, r.getNextDomainName());
        Assert.assertEquals(typeBitmap, r.getTypeBitmap());

        try {
            new NXTRecord(null, STD_CLASS, STD_TTL, nextName, typeBitmap);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new NXTRecord(STD_NAME, STD_CLASS, STD_TTL, null, typeBitmap);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new NXTRecord(STD_NAME, STD_CLASS, STD_TTL, nextName, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
