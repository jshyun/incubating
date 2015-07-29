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

public class NAPTRRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final short order = 1;
        final short preference = 2;
        final ByteString flags = ByteString.from(1, 2, 3, 4);
        final ByteString services = ByteString.from(5, 6, 7, 8, 9);
        final ByteString regexp = ByteString.from(0,1,2,3,4,5,6,7,8,9,10);
        final Name replacement = Name.fromString("www.jet.com.");

        NAPTRRecord r = new NAPTRRecord(STD_NAME, STD_CLASS, STD_TTL, order, preference, flags,
                services, regexp, replacement);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(order, r.getOrder());
        Assert.assertEquals(preference, r.getPreference());
        Assert.assertEquals(flags, r.getFlags());
        Assert.assertEquals(services, r.getServices());
        Assert.assertEquals(regexp, r.getRegexp());
        Assert.assertEquals(replacement, r.getReplacement());

        try {
            new NAPTRRecord(null, STD_CLASS, STD_TTL, order, preference, flags,
                    services, regexp, replacement);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new NAPTRRecord(STD_NAME, STD_CLASS, STD_TTL, order, preference, null,
                    services, regexp, replacement);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new NAPTRRecord(STD_NAME, STD_CLASS, STD_TTL, order, preference, flags,
                    null, regexp, replacement);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new NAPTRRecord(STD_NAME, STD_CLASS, STD_TTL, order, preference, flags,
                    services, null, replacement);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new NAPTRRecord(STD_NAME, STD_CLASS, STD_TTL, order, preference, flags,
                    services, regexp, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
