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
import org.typelibrary.dns.Algorithm;
import org.typelibrary.dns.DigestType;

public class CDSRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final short keyTag = 1;
        final Algorithm algorithm = Algorithm.RSASHA1;
        final DigestType digestType = DigestType.SHA1;
        final ByteString digest = ByteString.from(4, 5, 6, 7, 8, 9);
        
        CDSRecord r = new CDSRecord(STD_NAME, STD_CLASS, STD_TTL, keyTag, algorithm,
                digestType, digest);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(keyTag, r.getKeyTag());
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(digestType, r.getDigestType());
        Assert.assertEquals(digest, r.getDigest());
        
        try {
            new CDSRecord(null, STD_CLASS, STD_TTL, keyTag, algorithm,
                    digestType, digest);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new CDSRecord(STD_NAME, STD_CLASS, STD_TTL, keyTag, null,
                    digestType, digest);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new CDSRecord(STD_NAME, STD_CLASS, STD_TTL, keyTag, algorithm,
                    null, digest);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new CDSRecord(STD_NAME, STD_CLASS, STD_TTL, keyTag, algorithm,
                    digestType, null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
