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

public class SSHFPRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final byte algorithm = 1;
        final byte fingerprintType = 2;
        final ByteString fingerprint = ByteString.from(1, 2, 3, 4, 5, 6);
        
        SSHFPRecord r = new SSHFPRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm, fingerprintType,
                fingerprint);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(fingerprintType, r.getFpType());
        Assert.assertEquals(fingerprint, r.getFingerprint());

        try {
            new SSHFPRecord(null, STD_CLASS, STD_TTL, algorithm, fingerprintType,
                    fingerprint);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            new SSHFPRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm, fingerprintType,
                    null);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

    }

}
