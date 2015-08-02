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

public final class RecordType {

	public static final RecordType A          = new RecordType(1, "A");
	public static final RecordType NS         = new RecordType(2, "NS");
	public static final RecordType MD         = new RecordType(3, "MD");
	public static final RecordType MF         = new RecordType(4, "MF");
	public static final RecordType CNAME      = new RecordType(5, "CNAME");
	public static final RecordType SOA        = new RecordType(6, "SOA");
	public static final RecordType MB         = new RecordType(7, "MB");
	public static final RecordType MG         = new RecordType(8, "MG");
	public static final RecordType MR         = new RecordType(9, "MR");
	public static final RecordType NULL       = new RecordType(10, "NULL");
	public static final RecordType WKS        = new RecordType(11, "WKS");
	public static final RecordType PTR        = new RecordType(12, "PTR");
	public static final RecordType HINFO      = new RecordType(13, "HINFO");
	public static final RecordType MINFO      = new RecordType(14, "MINFO");
	public static final RecordType MX         = new RecordType(15, "MX");
	public static final RecordType TXT        = new RecordType(16, "TXT");
	public static final RecordType RP         = new RecordType(17, "RP");
	public static final RecordType AFSDB      = new RecordType(18, "AFSDB");
	public static final RecordType X25        = new RecordType(19, "X25");
	public static final RecordType ISDN       = new RecordType(20, "ISDN");
	public static final RecordType RT         = new RecordType(21, "RT");
	public static final RecordType NSAP       = new RecordType(22, "NSAP");
	public static final RecordType NSAPPTR    = new RecordType(23, "NSAP-PTR");
	public static final RecordType SIG        = new RecordType(24, "SIG");
	public static final RecordType KEY        = new RecordType(25, "KEY");
	public static final RecordType PX         = new RecordType(26, "PX");
	public static final RecordType GPOS       = new RecordType(27, "GPOS");
	public static final RecordType AAAA       = new RecordType(28, "AAAA");
	public static final RecordType LOC        = new RecordType(29, "LOC");
	public static final RecordType NXT        = new RecordType(30, "NXT");
	public static final RecordType EID        = new RecordType(31, "EID");
	public static final RecordType NIMLOC     = new RecordType(32, "NIMLOC");
	public static final RecordType SRV        = new RecordType(33, "SRV");
	public static final RecordType ATMA       = new RecordType(34, "ATMA");
	public static final RecordType NAPTR      = new RecordType(35, "NAPTR");
	public static final RecordType KX         = new RecordType(36, "KX");
	public static final RecordType CERT       = new RecordType(37, "CERT");
	public static final RecordType A6         = new RecordType(38, "A6");
	public static final RecordType DNAME      = new RecordType(39, "DNAME");
	public static final RecordType SINK       = new RecordType(40, "SINK");
    public static final RecordType OPT        = new RecordType(41, "OPT");
    public static final RecordType APL        = new RecordType(42, "APL");
    public static final RecordType DS         = new RecordType(43, "DS");
    public static final RecordType SSHFP      = new RecordType(44, "SSHFP");
    public static final RecordType IPSECKEY   = new RecordType(45, "IPSECKEY");
    public static final RecordType RRSIG      = new RecordType(46, "RRSIG");
    public static final RecordType NSEC       = new RecordType(47, "NSEC");
    public static final RecordType DNSKEY     = new RecordType(48, "DNSKEY");
    public static final RecordType DHCID      = new RecordType(49, "DHCID");
    public static final RecordType NSEC3      = new RecordType(50, "NSEC3");
    public static final RecordType NSEC3PARAM = new RecordType(51, "NSEC3PARAM");
    public static final RecordType TLSA       = new RecordType(52, "TLSA");
    public static final RecordType HIP        = new RecordType(55, "HIP");
    public static final RecordType NINFO      = new RecordType(56, "NINFO");
    public static final RecordType RKEY       = new RecordType(57, "RKEY");
    public static final RecordType TALINK     = new RecordType(58, "TALINK");
    public static final RecordType CDS        = new RecordType(59, "CDS");
    public static final RecordType CDNSKEY    = new RecordType(60, "CDNSKEY");
    public static final RecordType OPENPGPKEY = new RecordType(61, "OPENPGPKEY");
    public static final RecordType CSYNC      = new RecordType(62, "CSYNC");
    public static final RecordType SPF        = new RecordType(99, "SPF");
    public static final RecordType NID        = new RecordType(104, "NID");
    public static final RecordType L32        = new RecordType(105, "L32");
    public static final RecordType L64        = new RecordType(106, "L64");
    public static final RecordType LP         = new RecordType(107, "LP");
    public static final RecordType EUI49      = new RecordType(108, "EUI48");
    public static final RecordType EUI64      = new RecordType(109, "EUI64");
    public static final RecordType TKEY       = new RecordType(249, "TKEY");
    public static final RecordType TSIG       = new RecordType(250, "TSIG");
    public static final RecordType IXFR       = new RecordType(251, "IXFR");
    public static final RecordType AXFR       = new RecordType(252, "AXFR");
    public static final RecordType MAILB      = new RecordType(253, "MAILB");
    public static final RecordType MAILA      = new RecordType(254, "MAILA");
    public static final RecordType ALL        = new RecordType(255, "ALL");
    public static final RecordType URI        = new RecordType(256, "URI");
    public static final RecordType CAA        = new RecordType(257, "CAA");
    public static final RecordType DLV        = new RecordType(32769, "DLV");
    
	private final short value;
	private final String name;

	public RecordType(int value, String name) {
        if (value >> 16 != 0)
            throw new IllegalArgumentException("Record type must be a 16-bit value. type=" + value);
        if (name == null)
            throw new IllegalArgumentException("Name must not be null");
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
        RecordType other = (RecordType) obj;
        if (value != other.value)
            return false;
        return true;
    }

    public String toString() {
		return (name != null) ? (name + "[" + value + "]") : Integer.toString(value);
	}

    public static RecordType fromShort(short value) {
        return fromInt(value & 0xFFFF);
    }
    
	public static RecordType fromInt(int value) {
        if (value >> 16 != 0)
            throw new IllegalArgumentException("Record type must be a 16-bit value. type=" + value);

	    switch(value) {
		    case 1: return A;
		    case 2: return NS;
		    case 3: return MD;
		    case 4: return MF;
		    case 5: return CNAME;
		    case 6: return SOA;
		    case 7: return MB;
		    case 8: return MG;
		    case 9: return MR;
		    case 10: return NULL;
		    case 11: return WKS;
		    case 12: return PTR;
		    case 13: return HINFO;
		    case 14: return MINFO;
		    case 15: return MX;
		    case 16: return TXT;
		    case 17: return RP;
		    case 18: return AFSDB;
		    case 19: return X25;
		    case 20: return ISDN;
		    case 21: return RT;
		    case 22: return NSAP;
		    case 23: return NSAPPTR;
		    case 24: return SIG;
		    case 25: return KEY;
		    case 26: return PX;
		    case 27: return GPOS;
		    case 28: return AAAA;
		    case 29: return LOC;
		    case 30: return NXT;
		    case 31: return EID;
		    case 32: return NIMLOC;
		    case 33: return SRV;
		    case 34: return ATMA;
		    case 35: return NAPTR;
		    case 36: return KX;
		    case 37: return CERT;
		    case 38: return A6;
		    case 39: return DNAME;
		    case 40: return SINK;
		    case 41: return OPT;
		    case 42: return APL;
		    case 43: return DS;
		    case 44: return SSHFP;
		    case 45: return IPSECKEY;
		    case 46: return RRSIG;
		    case 47: return NSEC;
		    case 48: return DNSKEY;
		    case 49: return DHCID;
		    case 50: return NSEC3;
		    case 51: return NSEC3PARAM;
		    case 52: return TLSA;
		    case 55: return HIP;
		    case 56: return NINFO;
		    case 57: return RKEY;
		    case 58: return TALINK;
		    case 59: return CDS;
		    case 60: return CDNSKEY;
		    case 61: return OPENPGPKEY;
		    case 62: return CSYNC;
		    case 99: return SPF;
		    case 104: return NID;
		    case 105: return L32;
		    case 106: return L64;
		    case 107: return LP;
		    case 108: return EUI49;
		    case 109: return EUI64;
		    case 249: return TKEY;
		    case 250: return TSIG;
		    case 251: return IXFR;
		    case 252: return AXFR;
		    case 253: return MAILB;
		    case 254: return MAILA;
		    case 255: return ALL;
		    case 256: return URI;
		    case 257: return CAA;
		    case 32769: return DLV;
		    default:
		        return new RecordType(value, "?");
		}
	}
}
