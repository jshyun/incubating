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

public class SOARecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final Name master = Name.fromString("dns1.amazon.com.");
        final Name responsible = Name.fromString("dns-backup.amazon.com.");
        final int serialNumber = 1;
        final int refreshInterval = 2;
        final int retryInterval = 3;
        final int expireInterval = 4;
        final int minimum = 5;

        SOARecord r = new SOARecord(STD_NAME, STD_CLASS, STD_TTL, master, responsible, serialNumber,
                refreshInterval, retryInterval, expireInterval, minimum);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(master, r.getMasterName());
        Assert.assertEquals(responsible, r.getResponsibleName());
        Assert.assertEquals(serialNumber, r.getSerialNumber());
        Assert.assertEquals(refreshInterval, r.getRefreshInterval());
        Assert.assertEquals(retryInterval, r.getRetryInterval());
        Assert.assertEquals(expireInterval, r.getExpireInterval());
        Assert.assertEquals(minimum, r.getMinimum());

        try {
            new SOARecord(null, STD_CLASS, STD_TTL, master, responsible, serialNumber,
                    refreshInterval, retryInterval, expireInterval, minimum);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new SOARecord(STD_NAME, STD_CLASS, STD_TTL, null, responsible, serialNumber,
                    refreshInterval, retryInterval, expireInterval, minimum);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new SOARecord(STD_NAME, STD_CLASS, STD_TTL, master, null, serialNumber,
                    refreshInterval, retryInterval, expireInterval, minimum);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
