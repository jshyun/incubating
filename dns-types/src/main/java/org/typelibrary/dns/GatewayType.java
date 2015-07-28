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

public final class GatewayType { 

    public static final GatewayType NONE = new GatewayType(0, "NONE");
    public static final GatewayType IPV4 = new GatewayType(1, "IPV4");
    public static final GatewayType IPV6 = new GatewayType(2, "IPV6");
    public static final GatewayType DOMAIN_NAME = new GatewayType(2, "DOMAIN_NAME");

    private final byte value;
    private final String name;
    
    private GatewayType(int value, String name) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Gateway type must be an 8-bit value. value=" + value);
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
    
    public static GatewayType fromByte(byte value) {
        return fromInt(value & 0xFF);
    }
    
    public static GatewayType fromInt(int value) {
        if ((value >> 8) != 0)
            throw new IllegalArgumentException("Digest type must be an 8-bit value. value=" + value);

        switch (value) {
            case 0: return NONE;
            case 1: return IPV4;
            case 2: return IPV6;
            case 3: return DOMAIN_NAME;
            default:
                return new GatewayType(value, "?");
        }
    }

}