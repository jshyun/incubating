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

public class MDRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final Name dname = Name.fromString("ec2.aws.amazon.com.");

        MDRecord r = new MDRecord(STD_NAME, STD_CLASS, STD_TTL, dname);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(dname, r.getMailDestination());

        try {
            new MDRecord(null, STD_CLASS, STD_TTL, dname);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new MDRecord(STD_NAME, STD_CLASS, STD_TTL, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
