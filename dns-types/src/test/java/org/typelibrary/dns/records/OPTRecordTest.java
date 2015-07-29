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
import org.typelibrary.dns.records.OPTRecord.Option;

public class OPTRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final Option option1 = new Option((short)1, ByteString.from(1, 2));
        final Option option2 = new Option((short)3, ByteString.from(1, 2, 3, 4));
        final Option option3 = new Option((short)5, ByteString.from(1, 2, 3, 4, 5, 6));
        
        OPTRecord r = new OPTRecord(STD_NAME, STD_CLASS, STD_TTL, Arrays.asList(option1, option2,
                option3));
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        List<Option> options = r.getOptions();
        Assert.assertNotNull(options);
        Assert.assertEquals(3, options.size());
        Assert.assertEquals(option1, options.get(0));
        Assert.assertEquals(option2, options.get(1));
        Assert.assertEquals(option3, options.get(2));
    }

}
