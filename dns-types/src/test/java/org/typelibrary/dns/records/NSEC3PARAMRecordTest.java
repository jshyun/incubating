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

public class NSEC3PARAMRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final Algorithm algorithm = Algorithm.DSA;
        final byte flags = 1;
        final short iterations = 2;
        final ByteString salt = ByteString.from(1, 2, 3);
        
        NSEC3PARAMRecord r = new NSEC3PARAMRecord(STD_NAME, STD_CLASS, STD_TTL, algorithm,
                flags, iterations, salt);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(flags, r.getFlags());
        Assert.assertEquals(iterations, r.getIterations());
        Assert.assertEquals(salt, r.getSalt());

    }

}
