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

public final class OpCode {

    public static final OpCode QUERY  = new OpCode(0, "QUERY");
    public static final OpCode IQUERY = new OpCode(1, "IQUERY");
    public static final OpCode STATUS = new OpCode(2, "STATUS");
    public static final OpCode NOTIFY = new OpCode(4, "NOTIFY");
    public static final OpCode UPDATE = new OpCode(5, "UPDATE");

    private final int value;
    private final String name;

    public OpCode(int value, String name) {
        if (value >> 4 != 0)
            throw new IllegalArgumentException("OpCode must be 4-bit value. value=" + value);
        if (name == null)
            throw new IllegalArgumentException("Name must not be null");
        this.value = value;
        this.name = name;
    }

    public final int toInt() {
        return value;
    }

    public final String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OpCode other = (OpCode) obj;
        if (value != other.value)
            return false;
        return true;
    }

    public final String toString() {
        return (name != null) ? (name + "[" + value + "]") : Integer.toString(value);
    }

    public static final OpCode fromInt(int value) {
        if (value >> 4 != 0)
            throw new IllegalArgumentException("OpCode must be 4-bit value. value=" + value);

        switch (value) {
            case 0: return QUERY;
            case 1: return IQUERY;
            case 2: return STATUS;
            case 4: return NOTIFY;
            case 5: return UPDATE;
            default:
                return new OpCode(value, "?");
        }
    }
}
