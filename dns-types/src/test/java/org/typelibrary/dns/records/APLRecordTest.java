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
import org.typelibrary.dns.records.APLRecord.APItem;

public class APLRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final APItem item1 = new APItem((short)1, (byte)2, false, ByteString.from(1, 2, 3, 4));
        final APItem item2 = new APItem((short)5, (byte)6, true, ByteString.from(7));
        final APItem item3 = new APItem((short)8, (byte)9, false, ByteString.from(10, 11));
        
        APLRecord r = new APLRecord(STD_NAME, STD_CLASS, STD_TTL, Arrays.asList(item1,
                item2, item3));
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        List<APItem> items = r.getItems();
        Assert.assertNotNull(items);
        Assert.assertEquals(3, items.size());
        Assert.assertEquals(item1, items.get(0));
        Assert.assertEquals(item2, items.get(1));
        Assert.assertEquals(item3, items.get(2));
    }

}
