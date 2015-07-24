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

public final class TXTRecord extends Record {

    private final List<ByteString> text;

    public TXTRecord(Name name, short recordClass, int timeToLive, List<ByteString> text) {
        super(name, RecordType.TXT, recordClass, timeToLive);
        if (text == null)
            throw new IllegalArgumentException("Text cannot be null");
        this.text = Collections.unmodifiableList(new ArrayList<ByteString>(text));
    }

    public final List<ByteString> getText() {
        return text;
    }

    public final String toString() {
        return super.toString() + ", text=" + text;
    }

}
