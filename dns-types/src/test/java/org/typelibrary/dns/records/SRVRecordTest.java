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

public class SRVRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final short priority = 1;
        final short weight = 2;
        final short port = 3;
        final Name target = Name.fromString("smtp.amazon.com.");
        
        SRVRecord r = new SRVRecord(STD_NAME, STD_CLASS, STD_TTL, priority, weight, port,
                target);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(priority, r.getPriority());
        Assert.assertEquals(weight, r.getWeight());
        Assert.assertEquals(port, r.getPort());
        Assert.assertEquals(target, r.getTarget());

        try {
            new SRVRecord(null, STD_CLASS, STD_TTL, priority, weight, port,
                    target);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new SRVRecord(STD_NAME, STD_CLASS, STD_TTL, priority, weight, port,
                    null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
