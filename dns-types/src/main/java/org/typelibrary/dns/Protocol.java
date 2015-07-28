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

public final class Protocol {

    public static final Protocol TLS = new Protocol(1, "TLS");
    public static final Protocol EMAIL = new Protocol(2, "EMAIL");
    public static final Protocol DNSSEC = new Protocol(3, "DNSSEC");
    public static final Protocol IPSEC = new Protocol(4, "IPSEC");
    public static final Protocol ALL = new Protocol(255, "ALL");
    
    private final byte value;
    private final String name;
    
    private Protocol(int value, String name) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Protocol must be an 8-bit value. value=" + value);
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

    public final int toInt() {
        return value & 0xFF;
    }

    public static Protocol fromByte(byte value) {
        return fromInt(value & 0xFF);
    }
    
    public static Protocol fromInt(int value) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Protocol must be an 8-bit value. value=" + value);

        switch (value) {
            case 1: return TLS;
            case 2: return EMAIL;
            case 3: return DNSSEC;
            case 4: return IPSEC;
            case 255: return ALL;
            default:
                return new Protocol(value, "?");
        }
    }

}
