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

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class BinaryString {

    private final byte[] string;

    public BinaryString(byte[] string, int offset, int length) {
        if (string == null) throw new IllegalArgumentException("String cannot be null");
        this.string = new byte[length];
        System.arraycopy(string, offset, this.string, 0, length);
    }

    public BinaryString(ByteBuffer src, int length) {
        if (src == null) throw new IllegalArgumentException("Src cannot be null");
        this.string = new byte[length];
        src.get(string, 0, length);
    }

    public final byte[] getCopy() {
        return Arrays.copyOf(string, string.length);
    }

    public final ByteBuffer getAsByteBuffer() {
        return ByteBuffer.wrap(string).asReadOnlyBuffer();
    }

    public final void copyTo(byte[] buffer, int offset) {
        System.arraycopy(string, 0, buffer, offset, string.length);
    }
    
}
