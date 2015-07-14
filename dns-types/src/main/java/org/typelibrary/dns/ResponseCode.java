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

public final class ResponseCode {

    public static final ResponseCode NOERROR   = new ResponseCode(0, "NOERROR");
    public static final ResponseCode FORMERR   = new ResponseCode(1, "FORMERR");
    public static final ResponseCode SERVFAIL  = new ResponseCode(2, "SERVFAILE");
    public static final ResponseCode NXDOMAIN  = new ResponseCode(3, "NXDOMAIN");
    public static final ResponseCode NOTIMP    = new ResponseCode(4, "NOTIMP");
    public static final ResponseCode REFUSED   = new ResponseCode(5, "REFUSED");
    public static final ResponseCode YXDOMAIN  = new ResponseCode(6, "YXDOMAIN");
    public static final ResponseCode YXRRSET   = new ResponseCode(7, "YXRRSET");
    public static final ResponseCode NXRRSET   = new ResponseCode(8, "NXRRSET");
    public static final ResponseCode NOTAUTH   = new ResponseCode(9, "NOTAUTH");
    public static final ResponseCode NOTZONE   = new ResponseCode(10, "NOTZONE");

    public static final ResponseCode BADVERS   = new ResponseCode(16, "BADVERS");
    public static final ResponseCode BADSIG    = new ResponseCode(16, "BADSIG");
    public static final ResponseCode BADKEY    = new ResponseCode(17, "BADKEY");
    public static final ResponseCode BADTIME   = new ResponseCode(18, "BADTIME");
    public static final ResponseCode BADMODE   = new ResponseCode(19, "BADMODE");
    public static final ResponseCode BADNAME   = new ResponseCode(20, "BADNAME");
    public static final ResponseCode BADALG    = new ResponseCode(21, "BADALG");
    public static final ResponseCode BADTRUNC  = new ResponseCode(22, "BADTRUNC");
    
    private final int value;
    private final String name;

    public ResponseCode(int value, String name) {
        if (value >> 4 != 0)
            throw new IllegalArgumentException("Response code must be 4-bit value. value=" + value);
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
        ResponseCode other = (ResponseCode) obj;
        if (value != other.value)
            return false;
        return true;
    }

    public final String toString() {
        return (name != null) ? (name + "[" + value + "]") : Integer.toString(value);
    }

    public static final ResponseCode fromInt(int value) {
        if (value >> 4 != 0)
            throw new IllegalArgumentException("Response code must be 4-bit value. value=" + value);

        switch(value) {
        case 0: return NOERROR;
        case 1: return FORMERR;
        case 2: return SERVFAIL;
        case 3: return NXDOMAIN;
        case 4: return NOTIMP;
        case 5: return REFUSED;
        case 6: return YXDOMAIN;
        case 7: return YXRRSET;
        case 8: return NXRRSET;
        case 9: return NOTAUTH;
        case 10: return NOTZONE;
        case 16: return BADVERS;
        case 17: return BADKEY;
        case 18: return BADTIME;
        case 19: return BADMODE;
        case 20: return BADNAME;
        case 21: return BADALG;
        case 22: return BADTRUNC;
        default:
            return new ResponseCode(value, "?");
        }
    }

}
