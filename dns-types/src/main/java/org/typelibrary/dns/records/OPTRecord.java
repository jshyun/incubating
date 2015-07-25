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

    public static final class Option {
        private final short code;
        private final short length;
        private final ByteString data;
        public Option(short code, short length, ByteString data) {
            this.code = code;
            this.length = length;
            this.data = data;
        }
        public short getCode() {
            return code;
        }
        public short getLength() {
            return length;
        }
        public ByteString getData() {
            return data;
        }
        @Override
        public String toString() {
            return "code=" + code + ", length=" + length + ", data=" + data;
        }
    }

}
