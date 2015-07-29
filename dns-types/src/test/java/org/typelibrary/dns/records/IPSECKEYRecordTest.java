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

import java.net.InetAddress;

import org.junit.Assert;
import org.junit.Test;
import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.GatewayType;
import org.typelibrary.dns.PublicKeyAlgorithm;

public class IPSECKEYRecordTest extends AbstractRecordTest {

    @Test 
    public void testBasic() {

        final byte precedence = 1;
        final GatewayType gatewayType = GatewayType.IPV4;
        final PublicKeyAlgorithm algorithm = PublicKeyAlgorithm.RSA;
        final InetAddress gateway = getByAddress(new byte[] { (byte) 192, (byte) 168, 0, 1 });
        final ByteString publicKey = ByteString.from(4, 5, 6, 7, 8, 9);
        
        IPSECKEYRecord r = new IPSECKEYRecord(STD_NAME, STD_CLASS, STD_TTL, precedence, algorithm,
                gateway, publicKey);
        assertBaseRecord(STD_NAME, STD_CLASS, STD_TTL, r);
        
        Assert.assertEquals(precedence, r.getPrecedence());
        Assert.assertEquals(gatewayType, r.getGatewayType());
        Assert.assertEquals(algorithm, r.getAlgorithm());
        Assert.assertEquals(gateway, r.getGateway());
        Assert.assertEquals(publicKey, r.getPublicKey());

    }

}
