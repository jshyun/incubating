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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.typelibrary.binarystrings.ByteString;
import org.typelibrary.dns.Name;
import org.typelibrary.dns.Record;
import org.typelibrary.dns.RecordType;

/**
 * An Address Prefix List record.
 *  
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc3123">RFC 3123</a></td></tr>
 * <tr><td>Status:</td><td>Experimental</td></tr>
 * <tr><td>TYPE:</td><td>42</td></tr>
 * <tr><td>Meaning:</td><td>Address Prefix List</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>Items</td><td>{apitem}*</td><td>Zero or more AP items.</td></tr>
 * </table>
 * </p>
 *
 */
public final class APLRecord extends Record {

    private final List<APItem> items;

    public APLRecord(Name name, short recordClass, int timeToLive, List<APItem> items) {
        super(name, RecordType.APL, recordClass, timeToLive);
        if (items == null)
            throw new IllegalArgumentException("AP items cannot be null");
        this.items = Collections.unmodifiableList(new ArrayList<APItem>(items));
    }

    public List<APItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return super.toString() + ", items=" + items;
    }

    /**
     * An Address Prefix List item.
     *  
     * <p>
     * <table style="width: auto" summary="">
     * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc3123">RFC 3123</a></td></tr>
     * <tr><td>Status:</td><td>Experimental</td></tr>
     * <tr><td>TYPE:</td><td>N/A</td></tr>
     * <tr><td>Meaning:</td><td>Address Prefix Item</td></tr>
     * </table>
     * </p>
     * 
     * <p>
     * Fields:
     * <table style="width: auto" summary="">
     * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
     * <tr><td>ADDRESSFAMILY</td><td>16-bits</td><td>Address family.</td></tr>
     * <tr><td>PREFIX</td><td>8-bits</td><td>Prefix.</td></tr>
     * <tr><td>N</td><td>boolean</td><td>Negation flag.</td></tr>
     * <tr><td>AFDPART</td><td>{octets}*</td><td>Address Family Dependent Part.</td></tr>
     * </table>
     * </p>
     *
     */
    public static class APItem {
        
        private final short addressFamily;
        private final byte prefix;
        private final boolean negate;
        private final ByteString afdPart;

        public APItem(short addressFamily, byte prefix, boolean negate, ByteString afdPart) {
            if (afdPart == null)
                throw new IllegalArgumentException("AFD Part cannot be null.");
            if (afdPart.length() > 127)
                throw new IllegalArgumentException("AFD Part cannot be > 127 bytes.");
            this.addressFamily = addressFamily;
            this.prefix = prefix;
            this.negate = negate;
            this.afdPart = afdPart;
        }

        public short getAddressFamily() {
            return addressFamily;
        }

        public byte getPrefix() {
            return prefix;
        }

        public boolean isNegate() {
            return negate;
        }

        public ByteString getAfdPart() {
            return afdPart;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + addressFamily;
            result = prime * result + afdPart.hashCode();
            result = prime * result + (negate ? 1231 : 1237);
            result = prime * result + prefix;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            APItem other = (APItem) obj;
            if (addressFamily != other.addressFamily)
                return false;
            if (afdPart == null) {
                if (other.afdPart != null)
                    return false;
            } else if (!afdPart.equals(other.afdPart))
                return false;
            if (negate != other.negate)
                return false;
            if (prefix != other.prefix)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "addressFamily=" + addressFamily + ", prefix=" + prefix + ", negate=" + negate
                    + ", afdPart=" + afdPart;
        }
    }
}
