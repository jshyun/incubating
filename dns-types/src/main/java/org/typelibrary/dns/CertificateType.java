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
package org.typelibrary.dns;

public final class CertificateType {

    public static final CertificateType PKIX    = new CertificateType(1, "PKIX");
    public static final CertificateType SPKI    = new CertificateType(2, "SPKI");
    public static final CertificateType PGP     = new CertificateType(3, "PGP");
    public static final CertificateType IPKIX   = new CertificateType(4, "IPKIX");
    public static final CertificateType ISPKI   = new CertificateType(5, "ISPKI");
    public static final CertificateType IPGP    = new CertificateType(6, "IPGP");
    public static final CertificateType ACPKIX  = new CertificateType(7, "ACPKIX");
    public static final CertificateType IACPKIX = new CertificateType(8, "IACPKIX");
    
    public static final CertificateType URI     = new CertificateType(253, "URI");
    public static final CertificateType OID     = new CertificateType(254, "OID");
    
    private final short value;
    private final String name;
    
    public CertificateType(int value, String name) {
        if ((value >> 16) != 0)
            throw new IllegalArgumentException("Certificate type must be an 8-bit value. value=" + value);
        if (name == null)
            throw new IllegalArgumentException("Name must not be null.");
        this.value = (short) value;
        this.name = name;
    }
    
    public short getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int toInt() {
        return value & 0xFFFF;
    }
    
    public static CertificateType fromShort(short value) {
        return fromInt(value & 0xFFFF);
    }
    
    public static CertificateType fromInt(int value) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Algorithm must be an 8-bit value. value=" + value);

        switch (value) {
            case 1: return PKIX;
            case 2: return SPKI;
            case 3: return PGP;
            case 4: return IPKIX;
            case 5: return ISPKI;
            case 6: return IPGP;
            case 7: return ACPKIX;
            case 8: return IACPKIX;
            case 253: return URI;
            case 254: return OID;
            default:
                return new CertificateType(value, "?");
        }
    }

}
