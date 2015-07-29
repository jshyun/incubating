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
import org.typelibrary.dns.Name;
import org.typelibrary.dns.RecordType;

public class SIGRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final RecordType typeCovered = RecordType.A;
        final Algorithm algorithm = Algorithm.RSASHA1;
        final byte labels = 3;
        final int originalTtl = 4;
        final int signatureExpiration = 5;
        final int signatureInception = 6;
        final short keyTag = 7;
        final Name signer = Name.fromString("joe.amazon.com");
        final ByteString publicKey = ByteString.from(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        
        SIGRecord r = new SIGRecord(STD_NAME, STD_CLASS, STD_TTL, typeCovered,
                algorithm, labels, originalTtl, signatureExpiration, signatureInception,
                keyTag, signer, publicKey);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(typeCovered, r.getTypeCovered());
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(labels, r.getLabels());
        Assert.assertEquals(originalTtl, r.getOriginalTtl());
        Assert.assertEquals(signatureExpiration, r.getSignatureExpiration());
        Assert.assertEquals(signatureInception, r.getSignatureInception());
        Assert.assertEquals(keyTag, r.getKeyTag());
        Assert.assertEquals(signer, r.getSigner());
        Assert.assertEquals(publicKey, r.getPublicKey());

    }

}
