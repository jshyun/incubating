package org.typelibrary.binarystrings;
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


import java.nio.ByteBuffer;
import java.util.Arrays;

public final class ByteString {

    public static final ByteString EMPTY_STRING = new ByteString(0, 0, new byte[0]);
    
    private final byte[] string;
    private final int offset;
    private final int length;

    public ByteString(byte[] string) {
        this(string, 0, string.length);
    }
    
    public ByteString(byte[] string, int offset, int length) {
        if (string == null) throw new IllegalArgumentException("String cannot be null");
        this.string = new byte[length];
        this.offset = 0;
        this.length = length;
        System.arraycopy(string, offset, this.string, 0, length);
    }

    public ByteString(ByteBuffer src) {
        if (src == null) throw new IllegalArgumentException("Src cannot be null");
        this.offset = 0;
        this.length = src.remaining();
        this.string = new byte[this.length];
        if (src.hasArray()) {
            byte[] array = src.array();
            System.arraycopy(array, src.arrayOffset(), this.string, 0, this.length);
        } else {
            src.get(string, 0, this.length);
        }
    }
    
    public ByteString(ByteString string) {
        if (string == null) throw new IllegalArgumentException("String cannot be null");
        // It's assumed you want to make a deep copy
        this.string = new byte[string.length];
        this.offset = 0;
        this.length = string.length;
        System.arraycopy(string.string, string.offset, this.string, 0, string.length);
    }

    public ByteString(ByteString string, int offset, int length) {
        if (string == null) throw new IllegalArgumentException("String cannot be null");
        // It's assumed you want to make a deep copy
        this.string = new byte[length];
        this.offset = 0;
        this.length = length;
        if (length > 0)
            System.arraycopy(string.string, offset, this.string, 0, length);
    }
    
    private ByteString(int offset, int length, byte[] string) {
        if (string == null) throw new IllegalArgumentException("String cannot be null");
        if (offset < 0) throw new IllegalArgumentException("Offset cannot be < 0");
        if (offset > string.length) throw new IllegalArgumentException("Offset cannot be > length");
        if (length < 0) throw new IllegalArgumentException("Length cannot be < 0");
        this.string = string;
        this.offset = offset;
        this.length = length;
    }
    
    public final byte byteAt(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be < 0");
        if (index > string.length) throw new IndexOutOfBoundsException("Index cannot be < 0");
        return string[offset+index];
    }
    
    public final ByteString concat(byte[] bytes) {
        return concat(bytes, 0, bytes.length);
    }
    
    public final ByteString concat(byte[] bytes, int offset, int length) {
        if (length == 0)
            return this;
        byte[] newString = new byte[this.length + length];
        System.arraycopy(this.string, this.offset, newString, 0, this.length);
        System.arraycopy(bytes, offset, newString, this.length, length);
        return new ByteString(0, newString.length, newString);
    }
    
    public final ByteString concat(ByteString... strings) {
        if (strings.length == 0)
            return this;
        int totalLength = string.length;
        for (ByteString string : strings) {
            totalLength += string.length;
        }
        if (totalLength == length)
            return this;
        byte[] newString = new byte[totalLength];
        System.arraycopy(this.string, this.offset, newString, 0, this.length);
        int newOffset = this.length;
        for (ByteString string : strings) {
            System.arraycopy(string.string, string.offset, newString, newOffset, string.length);
            newOffset += string.length;
        }
        return new ByteString(0, newString.length, newString);
    }
    
    public final boolean contains(ByteString string) {
        return indexOf(string.string, string.offset, string.length, 0) > -1;
    }

    public final boolean contains(byte[] bytes) {
        return indexOf(bytes, 0, bytes.length, 0) > -1;
    }
    
    public final boolean contains(byte[] bytes, int offset, int length) {
        return indexOf(bytes, offset, length, 0) > -1;
    }
    
    public final boolean endsWith(ByteString suffix) {
        return startsWith(suffix.string, suffix.offset, suffix.length, length - suffix.length);
    }

    public final boolean endsWith(byte[] bytes) {
        return startsWith(bytes, 0, bytes.length, length - bytes.length);
    }
    
    public final boolean endsWith(byte[] bytes, int offset, int length) {
        return startsWith(bytes, offset, length, this.length - length);
    }
    
    public final int copyTo(byte[] buffer, int offset) {
        System.arraycopy(string, this.offset, buffer, offset, length);
        return length;
    }

    public final int copyTo(ByteBuffer buffer) {
        buffer.put(string, offset, length);
        return length;
    }
    
    public final boolean equals(Object object) {
        if (!(object instanceof ByteString))
            return false;
        ByteString string = (ByteString) object;
        if (this == string)
            return true;
        if (this.length != string.length)
            return false;
        int length = this.length;
        for (int i=offset, j=string.offset, k=0; k<length; ++i, ++j, ++k) {
            if(this.string[i] != string.string[j])
                return false;
        }
        return true;
    }
    
    public final int hashCode() {
        final int prime = 37;
        int result = 1;
        int endIndex = this.offset + this.length;
        for (int i=offset; i<endIndex; ++i) {
            result = prime * result + (int) string[i];
        }
        return result;
    }

    public final int indexOf(byte value) {
        return indexOf(value, 0);
    }
    
    public final int indexOf(byte value, int fromIndex) {
        int endIndex = this.offset + this.length;
        for (int i=offset; i<endIndex; ++i) {
            if (string[i] == value)
                return i - offset;
        }
        return -1;
    }

    public final int indexOf(ByteString string) {
        return indexOf(string, 0);
    }
    
    public final int indexOf(ByteString string, int fromIndex) {
        return indexOf(this.string, offset, length, string.string, string.offset, string.length,
                fromIndex);
    }

    public final int indexOf(byte[] bytes) {
        return indexOf(bytes, 0, bytes.length, 0);
    }
    
    public final int indexOf(byte[] bytes, int offset, int length, int fromIndex) {
        return indexOf(this.string, this.offset, this.length, bytes, offset, length,
                fromIndex);
    }
    
    public final boolean isEmpty() {
        return length == 0;
    }

    public final int lastIndexOf(byte value) {
        return lastIndexOf(value, length - 1);
    }
    
    public final int lastIndexOf(byte value, int fromIndex) {
        int i = offset + ((fromIndex >= length) ? length - 1 : fromIndex);
        for (; i>=offset; --i) {
            if (string[i] == value)
                return i - offset;
        }
        return -1;
    }

    public final int lastIndexOf(ByteString string) {
        return lastIndexOf(this.string, this.offset, this.length,
                string.string, string.offset, string.length, this.offset + this.length);
    }

    public final int lastIndexOf(ByteString string, int fromIndex) {
        return lastIndexOf(this.string, this.offset, this.length,
                           string.string, string.offset, string.length, fromIndex);
    }

    public final int lastIndexOf(byte[] bytes) {
        return lastIndexOf(this.string, offset, length,
                bytes, 0, bytes.length, this.offset + this.length);
    }
    
    public final int lastIndexOf(byte[] bytes, int offset, int length, int fromIndex) {
        return lastIndexOf(this.string, this.offset, this.length,
                           bytes, offset, length, fromIndex);
    }
    
    public final int length() {
        return length;
    }

    public final boolean startsWith(ByteString prefix) {
        return startsWith(prefix.string, prefix.offset, prefix.length, 0);
    }
    
    public final boolean startsWith(ByteString prefix, int toffset) {
        return startsWith(prefix.string, prefix.offset, prefix.length, toffset);
    }
    
    public final boolean startsWith(byte[] prefix) {
        return startsWith(prefix, 0, prefix.length, 0);
    }
    
    public final boolean startsWith(byte[] prefix, int toffset) {
        return startsWith(prefix, 0, prefix.length, toffset);
    }
    
    public final boolean startsWith(byte[] prefix, int offset, int length, int toffset) {
        byte targetArray[] = string;
        int targetOffset = this.offset + toffset;
        byte prefixArray[] = prefix;
        int prefixOffset = offset;
        int prefixLength = length;

        if ((toffset < 0) || (toffset > this.length - prefixLength)) {
            return false;
        }
        while (--prefixLength >= 0) {
            if (targetArray[targetOffset++] != prefixArray[prefixOffset++]) {
                return false;
            }
        }
        return true;
    }
    
    public final ByteString substring(int beginIndex) {
        return substring(beginIndex, this.length);
    }
    
    public final ByteString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0)
            throw new ArrayIndexOutOfBoundsException(beginIndex);
        if (endIndex > this.length)
            throw new ArrayIndexOutOfBoundsException(endIndex);
        if (endIndex < beginIndex)
            throw new ArrayIndexOutOfBoundsException(endIndex - beginIndex);
        if (beginIndex == 0 && endIndex == this.length)
            return this;
        if (beginIndex == endIndex)
            return EMPTY_STRING;
        return new ByteString(offset + beginIndex, endIndex - beginIndex, string);
    }

    public final ByteString toBinaryString() {
        return this;
    }

    public final byte[] toByteArray() {
        return Arrays.copyOfRange(string, offset, offset + length);
    }

    public final ByteBuffer toByteBuffer() {
        return ByteBuffer.wrap(string, offset, length).asReadOnlyBuffer();
    }
    
    public final String toString() {
        int hashCode = hashCode();
        return "[len="+length+", hashCode="+hashCode+"]";
    }
    
    public final ByteString compact() {
        if (offset > 0) {
            byte[] newString = new byte[length];
            System.arraycopy(string, offset, newString, 0, length);
            return new ByteString(newString);
        } else {
            return this;
        }
    }

    public final int footprint() {
        return string.length;
    }
    
    public static final ByteString from(int... bytes) {
        if (bytes.length == 0)
            return EMPTY_STRING;
        byte[] buffer = new byte[bytes.length];
        for (int i=0; i<buffer.length; ++i) {
            buffer[i] = (byte) bytes[i];
        }
        return new ByteString(0, buffer.length, buffer);
    }

    public static final ByteString from(ByteString... strings) {
        if (strings.length == 0)
            return EMPTY_STRING;
        int totalLength = 0;
        for (ByteString string : strings) {
            totalLength += string.length;
        }
        if (totalLength == 0)
            return EMPTY_STRING;
        byte[] buffer = new byte[totalLength];
        int offset = 0;
        for (ByteString string : strings) {
            System.arraycopy(string.string, string.offset, buffer, offset, string.length);
            offset += string.length;
        }
        return new ByteString(0, buffer.length, buffer);
    }
    
    static final int indexOf(byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount, int fromIndex) {

        if (fromIndex >= sourceCount)
            return targetCount == 0 ? sourceCount : -1;
        if (fromIndex < 0)
            fromIndex = 0;
        if (targetCount == 0)
            return fromIndex;
        
        byte first = target[targetOffset];
        int max = sourceOffset + sourceCount - targetCount;
        for (int i = sourceOffset + fromIndex; i <= max; ++i) {
            if (source[i] != first)
                while (++i <= max && source[i] != first);

            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] == target[k]; j++, k++);
                if (j == end)
                    return i - sourceOffset;
            }
        }
        return -1;
    }

    static final int lastIndexOf(byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount, int fromIndex) {

        int rightIndex = sourceCount - targetCount;
        if (fromIndex < 0) {
            return -1;
        }
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }

        if (targetCount == 0) {
            return fromIndex;
        }

        int strLastIndex = targetOffset + targetCount - 1;
        byte strLastByte = target[strLastIndex];
        int min = sourceOffset + targetCount - 1;
        int i = min + fromIndex;

        startSearchForLastByte: while (true) {
            while (i >= min && source[i] != strLastByte) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int start = j - (targetCount - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (source[j--] != target[k--]) {
                    i--;
                    continue startSearchForLastByte;
                }
            }
            return start - sourceOffset + 1;
        }
    }

}
