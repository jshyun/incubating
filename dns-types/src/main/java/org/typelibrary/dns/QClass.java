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

public final class QClass {

    public static final QClass IN    = new QClass(1,   "IN");
    public static final QClass CH    = new QClass(3,   "CH");
    public static final QClass HS    = new QClass(4,   "HS");
    public static final QClass NONE  = new QClass(254, "NONE");
    public static final QClass ANY   = new QClass(255, "ANY");
    
    private final int value;
    private final String name;

    public QClass(int value, String name) {
        if (value >> 16 != 0)
            throw new IllegalArgumentException("QClass must be a 16-bit value. qclass=" + value);
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
        QClass other = (QClass) obj;
        if (value != other.value)
            return false;
        return true;
    }

    public final String toString() {
        return (name != null) ? (name + "[" + value + "]") : Integer.toString(value);
    }

    public static final QClass fromInt(int value) {
        if (value >> 16 != 0)
            throw new IllegalArgumentException("QClass must be a 16 bit value. qclass=" + value);

        switch(value) {
        case 1: return IN;
        case 3: return CH;
        case 4: return HS;
        case 254: return NONE;
        case 255: return ANY;
        default:
            return new QClass(value, "?");
        }
    }

}
