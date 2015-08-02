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
 * An Option record.
 * 
 * <p>
 * <table style="width: auto" summary="">
 * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc6891">RFC 6891</a></td></tr>
 * <tr><td>Status:</td><td>Current</td></tr>
 * <tr><td>TYPE:</td><td>41</td></tr>
 * <tr><td>Meaning:</td><td>Option</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * Fields:
 * <table style="width: auto" summary="">
 * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
 * <tr><td>OPTIONS</td><td>{option}*</td><td></td></tr>
 * </table>
 * </p>
 *
 */
public final class OPTRecord extends Record {

    private final List<Option> options;

    public OPTRecord(Name name, short recordClass, int timeToLive, List<Option> options) {
        super(name, RecordType.OPT, recordClass, timeToLive);
        if (options == null)
            throw new IllegalArgumentException("Options cannot be null");
        this.options = Collections.unmodifiableList(new ArrayList<Option>(options));
    }

    public final List<Option> getOptions() {
        return options;
    }

    public final String toString() {
        return super.toString() + ", options=" + options;
    }

    /**
     * An Option.
     * 
     * <p>
     * <table style="width: auto" summary="">
     * <tr><td>Defined in:</td><td><a href="http://tools.ietf.org/html/rfc6891">RFC 6891</a></td></tr>
     * <tr><td>Status:</td><td>Current</td></tr>
     * <tr><td>TYPE:</td><td>N/A</td></tr>
     * <tr><td>Meaning:</td><td>Option</td></tr>
     * </table>
     * </p>
     * 
     * <p>
     * Fields:
     * <table style="width: auto" summary="">
     * <tr><td>Name</td><td>Type</td><td>Description</td></tr>
     * <tr><td>code</td><td>16-bits</td><td></td></tr>
     * <tr><td>data</td><td>{octets}*</td><td></td></tr>
     * </table>
     * </p>
     *
     */
    public static final class Option {
        
        private final short code;
        private final ByteString data;
        
        public Option(short code, ByteString data) {
            if (data == null)
                throw new IllegalArgumentException("Option cannot be null"); 
            if (data.length() > 65535)
                throw new IllegalArgumentException("Option cannot be null"); 
            this.code = code;
            this.data = data;
        }

        public short getCode() {
            return code;
        }

        public ByteString getData() {
            return data;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + code;
            result = prime * result + data.hashCode();
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
            Option other = (Option) obj;
            if (code != other.code)
                return false;
            if (data == null) {
                if (other.data != null)
                    return false;
            } else if (!data.equals(other.data))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "code=" + code + ", data=" + data;
        }
    }

}
