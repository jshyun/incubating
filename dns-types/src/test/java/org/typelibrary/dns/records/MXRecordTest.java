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
import org.typelibrary.dns.Name;

public class MXRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final short precedence = 250;
        final Name exchange = Name.fromString("smtp.amazon.com.");

        MXRecord r = new MXRecord(STD_NAME, STD_CLASS, STD_TTL, precedence, exchange);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(precedence, r.getPreference());
        Assert.assertEquals(exchange, r.getExchange());

        try {
            new MXRecord(null, STD_CLASS, STD_TTL, precedence, exchange);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new MXRecord(STD_NAME, STD_CLASS, STD_TTL, precedence, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
