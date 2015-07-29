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

public class HINFORecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final ByteString cpu = ByteString.from(1,2,3,4,5);
        final ByteString os = ByteString.from(6,7,8,9,0);

        HINFORecord r = new HINFORecord(STD_NAME, STD_CLASS, STD_TTL, cpu, os);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(cpu, r.getCpu());
        Assert.assertEquals(os, r.getOs());

    }

}
