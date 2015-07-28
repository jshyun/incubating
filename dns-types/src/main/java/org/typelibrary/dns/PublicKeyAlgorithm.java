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

public final class PublicKeyAlgorithm {
    
    public static final PublicKeyAlgorithm NONE = new PublicKeyAlgorithm(0, "NONE");
    public static final PublicKeyAlgorithm DSA = new PublicKeyAlgorithm(1, "DSA");
    public static final PublicKeyAlgorithm RSA = new PublicKeyAlgorithm(2, "RSA");

    private final byte value;
    private final String name;
    
    private PublicKeyAlgorithm(int value, String name) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Public key algorithm must be an 8-bit value. value=" + value);
        if (name == null)
            throw new IllegalArgumentException("Name must not be null.");
        this.value = (byte) value;
        this.name = name;
    }
    
    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int toInt() {
        return value & 0xFF;
    }

    public static PublicKeyAlgorithm fromByte(byte value) {
        return fromInt(value & 0xFF);
    }
    
    public static PublicKeyAlgorithm fromInt(int value) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Digest type must be an 8-bit value. value=" + value);

        switch (value) {
            case 0: return NONE;
            case 1: return DSA;
            case 2: return RSA;
            default:
                return new PublicKeyAlgorithm(value, "?");
        }
    }

}