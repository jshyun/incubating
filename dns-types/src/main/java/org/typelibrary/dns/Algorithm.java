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

public final class Algorithm {

    public static final Algorithm RSAMD5            = new Algorithm(1, "RSAMD5");
    public static final Algorithm DH                = new Algorithm(2, "DH");
    public static final Algorithm DSA               = new Algorithm(3, "DSA");
    public static final Algorithm ECC               = new Algorithm(4, "ECC");
    public static final Algorithm RSASHA1           = new Algorithm(5, "RSASHA1");
    
    public static final Algorithm DSANSEC3SHA1      = new Algorithm(6, "DSA-NSEC3-SHA1");
    public static final Algorithm RSASHA1NSEC3SHA1  = new Algorithm(7, "RSASHA1-NSEC3-SHA1");
    public static final Algorithm RSASHA256         = new Algorithm(8, "RSASHA256");
    public static final Algorithm RSASHA512         = new Algorithm(10, "RSASHA512");
    public static final Algorithm ECCGOST           = new Algorithm(12, "ECC-GOST");
    public static final Algorithm ECDSAP256SHA256   = new Algorithm(13, "ECDSAP256SHA256");
    public static final Algorithm ECDSAP384SHA384   = new Algorithm(14, "ECDSAP384SHA384");
    
    public static final Algorithm INDIRECT          = new Algorithm(252, "INDIRECT");
    public static final Algorithm PRIVATEDNS        = new Algorithm(253, "PRIVATEDNS");
    public static final Algorithm PRIVATEOID        = new Algorithm(254, "PRIVATEOID");
    
    private final byte value;
    private final String mnemonic;
    
    public Algorithm(int value, String mnemonic) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Algorithm must be an 8-bit value. value=" + value);
        if (mnemonic == null)
            throw new IllegalArgumentException("Mnemonic must not be null.");
        this.value = (byte) value;
        this.mnemonic = mnemonic;
    }
    
    public byte getValue() {
        return value;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public int toInt() {
        return value & 0xFF;
    }
    
    public static Algorithm fromByte(byte value) {
        return fromInt(value & 0xFF);
    }
    
    public static Algorithm fromInt(int value) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Algorithm must be an 8-bit value. value=" + value);

        switch (value) {
            case 1: return RSAMD5;
            case 2: return DH;
            case 3: return DSA;
            case 4: return ECC;
            case 5: return RSASHA1;
            case 6: return DSANSEC3SHA1;
            case 7: return RSASHA1NSEC3SHA1;
            case 8: return RSASHA256;
            case 10: return RSASHA512;
            case 12: return ECCGOST;
            case 13: return ECDSAP256SHA256;
            case 14: return ECDSAP384SHA384;
            case 252: return INDIRECT;
            case 253: return PRIVATEDNS;
            case 254: return PRIVATEOID;
            default:
                return new Algorithm(value, "?");
        }
    }

}
