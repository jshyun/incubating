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

public class LOCRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final byte version = 1;
        final byte size = 2;
        final byte horizontalPrecision = 3;
        final byte verticalPrecision = 4;
        final int latitude = 5;
        final int longitude = 6;
        final int altitude = 7;

        LOCRecord r = new LOCRecord(STD_NAME, STD_CLASS, STD_TTL, version, size, horizontalPrecision, verticalPrecision,
                latitude, longitude, altitude);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(version, r.getVersion());
        Assert.assertEquals(size, r.getSize());
        Assert.assertEquals(horizontalPrecision, r.getHorizontalPrecision());
        Assert.assertEquals(verticalPrecision, r.getVerticalPrecision());
        Assert.assertEquals(latitude, r.getLatitude());
        Assert.assertEquals(longitude, r.getLongitude());
        Assert.assertEquals(altitude, r.getAltitude());

    }

}
