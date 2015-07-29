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

public class TKEYRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final Name algorithm = Name.fromString("rsa.verisign.com.");
        final int inception = 1;
        final int expiration = 2;
        final short mode = 3;
        final short keySize = 4;
        final ByteString keyData = ByteString.from(1, 2, 3, 4);
        final ByteString otherData = ByteString.from(1, 2, 3, 4, 5, 6, 7, 8);
        
        TKEYRecord r = new TKEYRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm, inception, expiration,
                mode, keySize, keyData, otherData);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(inception, r.getInception());
        Assert.assertEquals(expiration, r.getExpiration());
        Assert.assertEquals(mode, r.getMode());
        Assert.assertEquals(keySize, r.getKeySize());
        Assert.assertEquals(keyData, r.getKeyData());
        Assert.assertEquals(otherData, r.getOtherData());

        try {
            new TKEYRecord(null, STD_CLASS, STD_TTL, algorithm, inception, expiration,
                    mode, keySize, keyData, otherData);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new TKEYRecord(STD_NAME, STD_CLASS, STD_TTL, null, inception, expiration,
                    mode, keySize, keyData, otherData);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new TKEYRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm, inception, expiration,
                    mode, keySize, null, otherData);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new TKEYRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm, inception, expiration,
                    mode, keySize, keyData, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
