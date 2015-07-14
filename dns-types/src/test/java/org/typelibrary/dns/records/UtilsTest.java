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
import org.typelibrary.dns.records.Utils;

public class UtilsTest {

    @Test
    public void testHexArray() {

        byte[] buffer;

        buffer = new byte[] { 0x00 };
        Assert.assertEquals("00", Utils.toGroupedHexArray(buffer, 1, ':'));
        Assert.assertEquals("00", Utils.toGroupedHexArray(buffer, 2, ':'));
        Assert.assertEquals("00", Utils.toGroupedHexArray(buffer, 3, ':'));

        buffer = new byte[] { 0x00, 0x0F };
        Assert.assertEquals("00:0F", Utils.toGroupedHexArray(buffer, 1, ':'));
        Assert.assertEquals("000F", Utils.toGroupedHexArray(buffer, 2, ':'));
        Assert.assertEquals("000F", Utils.toGroupedHexArray(buffer, 3, ':'));

        buffer = new byte[] { 0x00, 0x0F, 0x10 };
        Assert.assertEquals("00:0F:10", Utils.toGroupedHexArray(buffer, 1, ':'));
        Assert.assertEquals("000F:10", Utils.toGroupedHexArray(buffer, 2, ':'));
        Assert.assertEquals("000F10", Utils.toGroupedHexArray(buffer, 3, ':'));

        buffer = new byte[] { 0x00, 0x0F, 0x10, (byte)0xF0 };
        Assert.assertEquals("00:0F:10:F0", Utils.toGroupedHexArray(buffer, 1, ':'));
        Assert.assertEquals("000F:10F0", Utils.toGroupedHexArray(buffer, 2, ':'));
        Assert.assertEquals("000F10:F0", Utils.toGroupedHexArray(buffer, 3, ':'));

    }

}
