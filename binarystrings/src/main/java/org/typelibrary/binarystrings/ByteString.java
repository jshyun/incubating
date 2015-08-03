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
package org.typelibrary.binarystrings;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Immutable sequence of bytes. Equivalent to java.lang.String but for byte values instead
 * of char. Also takes inspiration from Google Protobuf ByteString.
 *
 * <p>Differences with java.lang.String:</p>
 * 
 * <p>
 * <ul>
 * <li> Doesn't implement Comparable&lt;T&gt;
 * <li> Doesn't implement Serializable
 * <li> Doesn't implement equivalent to CharSequence
 * <li> No equivalent builder classes such as StringBuilder/StringBuffer
 * <li> No intern capability
 * <li> No character/character-string specific methods.
 * <ul>
 * <li> codePointAt/Before/Count, offsetByCodePoints
 * <li> copyValueOf (char), valueOf, trim, format
 * <li> toLowerCase/UpperCase/CharArray
 * <li> regex - matches, replaceAll, replaceFirst, split,
 * </ul>
 * </ul>
 * </p>
 * 
 * <p>Differences with com.google.protobuf.ByteString</p>
 * 
 * <p>
 * <ul>
 * <li> Doesn't implement Iterable.
 * <li> Doesn't implement Utf8 convenience methods
 * <li> Doesn't support CodedInputStream
 * <li> Doesn't implement Rope based ByteString.
 * <ul>
 * <li> Rope based ByteString has roughly O(1) vs. O(n)
 *      performance for concatenation, OutputStream (which concatenates writes) and certain
 *      other operations.
 * </ul>
 * </ul>
 * </p>
 */
public final class ByteString {

    public static final ByteString EMPTY = new ByteString(0, 0, new byte[0]);
    
    private final byte[] string;
    private final int offset;
    private final int length;
    private int hashCodeCache = 0;

    public ByteString(byte[] source) {
        this(source, 0, source != null ? source.length : 0);
    }
    
    public ByteString(byte[] source, int offset, int length) {
        if (source == null) throw new IllegalArgumentException("Source cannot be null");
        this.string = new byte[length];
        this.offset = 0;
        this.length = length;
        System.arraycopy(source, offset, this.string, 0, length);
    }

    public ByteString(ByteString source) {
        if (source == null) throw new IllegalArgumentException("Source cannot be null");
        // It's assumed you want to make a deep copy
        this.string = new byte[source.length];
        this.offset = 0;
        this.length = source.length;
        System.arraycopy(source.string, source.offset, this.string, 0, source.length);
    }

    public ByteString(ByteString source, int offset, int length) {
        if (source == null) throw new IllegalArgumentException("Source cannot be null");
        // It's assumed you want to make a deep copy
        this.string = new byte[length];
        this.offset = 0;
        this.length = length;
        if (length > 0)
            System.arraycopy(source.string, offset, this.string, 0, length);
    }
    
    ByteString(int offset, int length, byte[] source) {
        this.string = source;
        this.offset = offset;
        this.length = length;
    }

    /**
     * Returns the byte value at the specified index. An index ranges from 0 to length() - 1. 
     * The first byte value of the sequence is at index 0, the next at index 1, and so on, as 
     * for array indexing.
     * 
     * <p>(This description from {@link java.lang.String#charAt String.charAt})</p>
     * 
     * @param index the index of the byte value
     * @return the byte value at the specified index of this string. The first byte value is 
     *         at index 0.
     * @throws IndexOutOfBoundsException if the index argument is negative or not less than the 
     *         length of this string.
     * @see java.lang.String#charAt
     * 
     */
    public byte byteAt(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be < 0");
        if (index > string.length)
            throw new IndexOutOfBoundsException("Index cannot be > length. index=" + index + ", length=" + length);
        return string[offset+index];
    }

    /**
     * Concatenates the specified byte array to the end of this string.
     * 
     * <p>
     * If the length of the string is 0, then this ByteString object is returned. 
     * Otherwise, a new ByteString object is created, representing a byte sequence that is 
     * the concatenation of the byte sequence represented by this ByteString object and the 
     * byte sequence represented by the argument byte array.
     * </p>
     * 
     * <p>(This description from {@link java.lang.String#concat String.concat})</p>
     * 
     * @param source The byte array that is to be concatenated to the end of this string.
     * @return a string that represents the concatenation of this object's bytes followed 
     *         by the string argument's bytes.
     * @see java.lang.String#concat
     * 
     */
    public ByteString concat(byte[] source) {
        if (source == null)
            throw new IllegalArgumentException("Source cannot be null");
        return concat(source, 0, source.length);
    }

    /**
     * Concatenates the specified byte array to the end of this string.
     * 
     * <p>
     * If the length of the string is 0, then this ByteString object is returned. 
     * Otherwise, a new ByteString object is created, representing a byte sequence that is 
     * the concatenation of the byte sequence represented by this ByteString object and the 
     * byte sequence represented by the argument byte array.
     * </p>
     * 
     * <p>(This description from {@link java.lang.String#concat String.concat})</p>
     * 
     * @param source The byte array that is to be concatenated to the end of this string.
     * @param offset The index of the first byte to be concatenated
     * @param length The number of bytes to concatenate
     * @return a string that represents the concatenation of this object's bytes followed 
     *         by the string argument's bytes.
     * @see java.lang.String#concat
     * 
     */
    public ByteString concat(byte[] source, int offset, int length) {
        if (source == null)
            throw new IllegalArgumentException("Source cannot be null");
        if (length > source.length)
            throw new IllegalArgumentException("Length cannot be > source length. length=" + length
                    + ", source.length=" + source.length);
        if (length == 0)
            return this;
        byte[] newString = new byte[this.length + length];
        System.arraycopy(this.string, this.offset, newString, 0, this.length);
        System.arraycopy(source, offset, newString, this.length, length);
        return new ByteString(0, newString.length, newString);
    }

    /**
     * Concatenates the specified strings to the end of this string.
     * 
     * <p>
     * If the length of the argument string is 0, then this ByteString object is returned. 
     * Otherwise, a new ByteString object is created, representing a byte sequence that is 
     * the concatenation of the byte sequence represented by this ByteString object and the 
     * byte sequence represented by the argument strings.
     * </p>
     * 
     * <p>(This description from {@link java.lang.String#concat String.concat})</p>
     * 
     * <p>
     * This method differs from String in that it allows you to concatenate multiple strings 
     * at once. This is more efficient since intermediate strings are not created during 
     * concatenation.
     * </p>
     * 
     * @param strings the ByteStrings that are to be concatenated to the end of this string.
     * @return a string that represents the concatenation of this object's bytes followed by 
     *         the string argument's bytes.
     * @see java.lang.String#concat
     * 
     */
    public ByteString concat(ByteString... strings) {
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

    /**
     * Returns true if and only if this string contains the specified sequence of byte values.
     * 
     * <p>(This description from {@link java.lang.String#contains String.contains})</p>
     * 
     * @param string the sequence to search for
     * @return true if this string contains string, false otherwise
     * @throws NullPointerException if string is null
     */
    public boolean contains(ByteString string) {
        if (string == null)
            throw new NullPointerException("String cannot be null");
        return indexOf(string.string, string.offset, string.length, 0) > -1;
    }

    /**
     * Returns true if and only if this string contains the specified sequence of byte values.
     * 
     * <p>(This description from {@link java.lang.String#contains String.contains})</p>
     * 
     * @param bytes the sequence to search for
     * @return true if this string contains string, false otherwise
     * @throws NullPointerException if string is null
     */
    public boolean contains(byte[] bytes) {
        return indexOf(bytes, 0, bytes.length, 0) > -1;
    }

    /**
     * Returns true if and only if this string contains the specified sequence of byte values.
     * 
     * <p>(This description from {@link java.lang.String#contains String.contains})</p>
     * 
     * @param bytes the sequence to search for
     * @param offset the index of the first byte to search for
     * @param length the number of bytes to search for
     * @return true if this string contains string, false otherwise
     * @throws NullPointerException if string is null
     */
    public boolean contains(byte[] bytes, int offset, int length) {
        return indexOf(bytes, offset, length, 0) > -1;
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 
     * <p>(This description from {@link java.lang.String#endsWith String.endsWith})</p>
     * 
     * @param suffix the suffix
     * @return true if the byte sequence represented by the argument is a suffix of the byte 
     *         sequence represented by this object; false otherwise. Note that the result 
     *         will be true if the argument is the empty string or is equal to this ByteString 
     *         object as determined by the {@link #equals} method.
     */
    public boolean endsWith(ByteString suffix) {
        return startsWith(suffix.string, suffix.offset, suffix.length, length - suffix.length);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 
     * <p>(This description from {@link java.lang.String#endsWith String.endsWith})</p>
     * 
     * @param suffix the suffix
     * @return true if the byte sequence represented by the argument is a suffix of the byte 
     *         sequence represented by this object; false otherwise. Note that the result 
     *         will be true if the argument is the empty string or is equal to this ByteString 
     *         object as determined by the {@link #equals} method.
     */
    public boolean endsWith(byte[] suffix) {
        return startsWith(suffix, 0, suffix.length, length - suffix.length);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 
     * <p>(This description from {@link java.lang.String#endsWith String.endsWith})</p>
     * 
     * @param suffix the suffix
     * @return true if the byte sequence represented by the argument is a suffix of the byte 
     *         sequence represented by this object; false otherwise. Note that the result 
     *         will be true if the argument is the empty string or is equal to this ByteString 
     *         object as determined by the {@link #equals} method.
     */
    public boolean endsWith(byte[] suffix, int offset, int length) {
        return startsWith(suffix, offset, length, this.length - length);
    }
    
    public int copyTo(byte[] buffer, int offset) {
        System.arraycopy(string, this.offset, buffer, offset, length);
        return length;
    }

    public int copyTo(ByteBuffer buffer) {
        buffer.put(string, offset, length);
        return length;
    }

    public void forEach(ByteConsumer consumer) {
        for (byte b : string) {
            consumer.accept(b);
        }
    }

    /**
     * Compares this string to the specified object. The result is true if and only if the 
     * argument is not null and is a ByteString object that represents the same sequence of 
     * bytes as this object.
     * 
     * <p>(This description from {@link java.lang.String#equals String.equals})</p>
     * 
     * @param object The object to compare this ByteString against
     * @return true if the given object represents a ByteString equivalent to this string, 
     *         false otherwise
     */
    public boolean equals(Object object) {
        if (!(object instanceof ByteString))
            return false;
        ByteString string = (ByteString) object;
        if (this == string)
            return true;
        if (this.length != string.length)
            return false;
        int localLength = this.length;
        byte[] localThisString = this.string;
        byte[] localThatString = string.string;
        for (int i=offset, j=string.offset, k=0; k<localLength; ++i, ++j, ++k) {
            if(localThisString[i] != localThatString[j])
                return false;
        }
        return true;
    }
    
    public int hashCode() {
        int localHashCode = hashCodeCache;
        if (localHashCode == 0) {
            int endIndex = this.offset + this.length;
            byte[] localThisString = this.string;
            for (int i=offset; i<endIndex; ++i) {
                localHashCode = 37 * localHashCode + (int) localThisString[i];
            }
            hashCodeCache = localHashCode;
        }
        return localHashCode;
    }

    /**
     * Returns the index within this string of the first occurrence of the specified byte.
     * 
     * <p>
     * If a byte with value value occurs in the byte sequence represented by this ByteString 
     * object, then the index of the first such occurrence is returned. This is the smallest 
     * value k such that:
     * </p>
     * 
     * <p><code>
     * this.byteAt(k) == value
     * </code></p>
     * 
     * If no such value occurs in this string, then -1 is returned.
     * 
     * <p>(This description from {@link java.lang.String#indexOf String.indexOf})</p>
     * 
     * @param value a byte value
     * @return the index of the first occurrence of the byte in the byte sequence represented 
     *         by this object, or -1 if the byte does not occur.
     */
    public int indexOf(byte value) {
        return indexOf(value, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the specified byte,
     * starting the search at the specified index.
     * 
     * <p>
     * If a byte with value value occurs in the byte sequence represented by this ByteString 
     * object, then the index of the first such occurrence is returned. This is the smallest 
     * value k such that:
     * </p>
     * 
     * <p><code>
     * (this.charAt(k) == value) && (k >= fromIndex)
     * </code></p>
     * 
     * If no such value occurs in this string, then -1 is returned.
     * 
     * <p>(This description from {@link java.lang.String#indexOf String.indexOf})</p>
     * 
     * @param value a byte value
     * @param fromIndex the index to start the search from
     * @return the index of the first occurrence of the byte in the byte sequence represented 
     *         by this object, or -1 if the byte does not occur.
     */
    public int indexOf(byte value, int fromIndex) {
        int endIndex = this.offset + this.length;
        byte[] localThisString = this.string;
        for (int i=offset; i<endIndex; ++i) {
            if (localThisString[i] == value)
                return i - offset;
        }
        return -1;
    }

    /**
     * Returns the index within this string of the first occurrence of the specified substring.
     * 
     * <p>The returned index is the smallest value k for which:</p>
     * 
     * <p><code>
     * this.startsWith(string, k)
     * </code></p>
     * 
     * <p>If no such value of k exists, then -1 is returned.</p>
     * 
     * @param string the substring to search for
     * @return the index of the first occurrence of the specified substring, or -1 if there is no 
     *         such occurrence.
     */
    public int indexOf(ByteString string) {
        return indexOf(string, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the specified substring,
     * starting the search at the specified index.
     * 
     * <p>The returned index is the smallest value k for which:</p>
     * 
     * <p><code>
     * k >= fromIndex && this.startsWith(string, k)
     * </code></p>
     * 
     * <p>If no such value of k exists, then -1 is returned.</p>
     * 
     * @param string the substring to search for
     * @param fromIndex the index to start the search from
     * @return the index of the first occurrence of the specified substring, or -1 if there is no 
     *         such occurrence.
     */
    public int indexOf(ByteString string, int fromIndex) {
        return indexOf(this.string, offset, length, string.string, string.offset, string.length,
                fromIndex);
    }

    /**
     * Returns the index within this string of the first occurrence of the specified byte array.
     * 
     * <p>The returned index is the smallest value k for which:</p>
     * 
     * <p><code>
     * this.startsWith(bytes, k)
     * </code></p>
     * 
     * <p>If no such value of k exists, then -1 is returned.</p>
     * 
     * @param bytes the byte array to search for
     * @return the index of the first occurrence of the specified byte array, or -1 if there 
     *         is no such occurrence.
     */
    public int indexOf(byte[] bytes) {
        return indexOf(bytes, 0, bytes.length, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the specified byte array,
     * starting the search at the specified index.
     * 
     * <p>The returned index is the smallest value k for which:</p>
     * 
     * <p><code>
     * k >= fromIndex && this.startsWith(bytes, offset, length, k)
     * </code></p>
     * 
     * <p>If no such value of k exists, then -1 is returned.</p>
     * 
     * @param bytes the byte array to search for
     * @param offset the index of the first byte to search for
     * @param length the number of bytes to search for
     * @param fromIndex the index to start the search from
     * @return the index of the first occurrence of the specified byte array, or -1 if there 
     *         is no such occurrence.
     */
    public int indexOf(byte[] bytes, int offset, int length, int fromIndex) {
        return indexOf(this.string, this.offset, this.length, bytes, offset, length,
                fromIndex);
    }

    /**
     * Returns true if, and only if, length() is 0.
     * 
     * @return true is length() is 0, otherwise false.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the index within this string of the last occurrence of the specified byte. 
     * The index returned is the largest value k such that:
     * 
     * <p><code>
     * this.byteAt(k) == value
     * </code></p>
     * 
     * If no such value occurs in this string, then -1 is returned. The ByteString is
     * searched backwards starting at the last character.
     * 
     * <p>(This description from {@link java.lang.String#indexOf String.indexOf})</p>
     * 
     * @param value a byte value
     * @return the index of the last occurrence of the byte in the byte sequence represented 
     *         by this object, or -1 if the byte does not occur.
     */
    public int lastIndexOf(byte value) {
        return lastIndexOf(value, length - 1);
    }

    /**
     * Returns the index within this string of the last occurrence of the specified byte,
     * searching backwards starting at the specified index. 
     * The index returned is the largest value k such that:
     * 
     * <p><code>
     * (this.charAt(k) == value) && (k <= fromIndex)
     * </code></p>
     * 
     * If no such value occurs in this string, then -1 is returned. The ByteString is
     * searched backwards starting at the last character.
     * 
     * <p>(This description from {@link java.lang.String#indexOf String.indexOf})</p>
     * 
     * @param value a byte value
     * @param fromIndex the index to start the search from. There is no restriction on the 
     *        value of fromIndex. If it is greater than or equal to the length of this string, 
     *        it has the same effect as if it were equal to one less than the length of this 
     *        string: this entire string may be searched. If it is negative, it has the same 
     *        effect as if it were -1: -1 is returned.
     * @return the index of the last occurrence of the byte in the byte sequence represented 
     *         by this object, or -1 if the byte does not occur.
     */
    public int lastIndexOf(byte value, int fromIndex) {
        int i = offset + ((fromIndex >= length) ? length - 1 : fromIndex);
        byte[] localThisString = this.string;
        for (; i>=offset; --i) {
            if (localThisString[i] == value)
                return i - offset;
        }
        return -1;
    }

    /**
     * Returns the index within this string of the last occurrence of the specified substring.
     * The last occurrence of the empty string is considered to occur at the index value 
     * this.length().
     *  
     * <p>The index returned is the largest value k such that:</p>
     * 
     * <p><code>
     * this.startsWith(string, k)
     * </code></p>
     * 
     * If no such value k exists, then -1 is returned.
     * 
     * <p>(This description from {@link java.lang.String#lastIndexOf String.lastIndexOf})</p>
     * 
     * @param string the substring to search for
     * @return the index of the last occurrence of the specified substring, or -1 if there is 
     *         no such occrrence.
     */
    public int lastIndexOf(ByteString string) {
        return lastIndexOf(this.string, this.offset, this.length,
                string.string, string.offset, string.length, this.offset + this.length);
    }

    /**
     * Returns the index within this string of the last occurrence of the specified substring,
     * searching backwards starting at the specified index.
     * 
     * <p>The returned index is the largest value k for which:</p>
     * 
     * <p><code>
     * k <= fromIndex && this.startsWith(string, k)
     * </code></p>
     * 
     * If no such value k exists, then -1 is returned.
     * 
     * <p>(This description from {@link java.lang.String#lastIndexOf String.lastIndexOf})</p>
     * 
     * @param string the substring to search for
     * @param fromIndex the index to start the search from
     * @return the index of the last occurrence of the specified substring, searching backwards
     *         from the specified index, or -1 if there is no such occurrence.
     */
    public int lastIndexOf(ByteString string, int fromIndex) {
        return lastIndexOf(this.string, this.offset, this.length,
                           string.string, string.offset, string.length, fromIndex);
    }

    /**
     * Returns the index within this string of the last occurrence of the specified byte array.
     * The last occurrence of the empty byte array is considered to occur at the index value 
     * this.length().
     *  
     * <p>The index returned is the largest value k such that:</p>
     * 
     * <p><code>
     * this.startsWith(string, k)
     * </code></p>
     * 
     * If no such value k exists, then -1 is returned.
     * 
     * <p>(This description from {@link java.lang.String#lastIndexOf String.lastIndexOf})</p>
     * 
     * @param bytes the byte array to search for
     * @return the index of the last occurrence of the specified byte array, or -1 if there is 
     *         no such occurrence.
     */
    public int lastIndexOf(byte[] bytes) {
        return lastIndexOf(this.string, offset, length,
                bytes, 0, bytes.length, this.offset + this.length);
    }

    /**
     * Returns the index within this string of the last occurrence of the specified byte array,
     * searching backwards starting at the specified index.
     * 
     * <p>The returned index is the largest value k for which:</p>
     * 
     * <p><code>
     * k <= fromIndex && this.startsWith(bytes, offset, length, k)
     * </code></p>
     * 
     * If no such value k exists, then -1 is returned.
     * 
     * <p>(This description from {@link java.lang.String#lastIndexOf String.lastIndexOf})</p>
     * 
     * @param bytes the substring to search for
     * @param offset the index of the first byte to search for
     * @param length the number of bytes to search for
     * @param fromIndex the index to start the search from
     * @return the index of the last occurrence of the specified substring, searching backwards
     *         from the specified index, or -1 if there is no such occurrence.
     */
    public int lastIndexOf(byte[] bytes, int offset, int length, int fromIndex) {
        return lastIndexOf(this.string, this.offset, this.length,
                           bytes, offset, length, fromIndex);
    }

    /**
     * Returns the length of this string. The length is equal to the number of bytes in the string.
     * 
     * @return the length of the sequence of bytes represented by this object.
     */
    public int length() {
        return length;
    }

    /**
     * Tests if this string starts with the specified prefix.
     * 
     * <p>(This description from {@link java.lang.String#startsWith String.startsWith})</p>
     * 
     * @param prefix the prefix
     * @return true if the byte sequence represented by the argument is a prefix of the 
     *         byte sequence represented by this string; false otherwise. Note also that 
     *         true will be returned if the argument is an empty string or is equal to 
     *         this ByteString object as determined by the equals(Object) method.
     */
    public boolean startsWith(ByteString prefix) {
        return startsWith(prefix.string, prefix.offset, prefix.length, 0);
    }

    /**
     * Tests if the substring of this string beginning at the specified index starts with the 
     * specified prefix.
     * 
     * <p>(This description from {@link java.lang.String#startsWith String.startsWith})</p>
     * 
     * @param prefix the prefix
     * @return true if the byte sequence represented by the argument is a prefix of the 
     *         substring of this object starting at index toffset; false otherwise. The 
     *         result is false if toffset is negative or greater than the length of this 
     *         ByteString object; otherwise the result is the same as the result of the 
     *         expression
     *         
     *         <p><code>
     *         this.substring(toffset).startsWith(prefix)
     *         </code></p>
     */
    public boolean startsWith(ByteString prefix, int toffset) {
        return startsWith(prefix.string, prefix.offset, prefix.length, toffset);
    }

    /**
     * Tests if this string starts with the specified prefix.
     * 
     * <p>(This description from {@link java.lang.String#startsWith String.startsWith})</p>
     * 
     * @param prefix the prefix
     * @return true if the byte sequence represented by the argument is a prefix of the 
     *         byte sequence represented by this string; false otherwise. Note also that 
     *         true will be returned if the argument is an empty string or is equal to 
     *         this ByteString object as determined by the equals(Object) method.
     */
    public boolean startsWith(byte[] prefix) {
        return startsWith(prefix, 0, prefix.length, 0);
    }

    /**
     * Tests if the substring of this string beginning at the specified index starts with the 
     * specified prefix.
     * 
     * <p>(This description from {@link java.lang.String#startsWith String.startsWith})</p>
     * 
     * @param prefix the prefix
     * @return true if the byte sequence represented by the argument is a prefix of the 
     *         substring of this object starting at index toffset; false otherwise. The 
     *         result is false if toffset is negative or greater than the length of this 
     *         ByteString object; otherwise the result is the same as the result of the 
     *         expression
     *         
     *         <p><code>
     *         this.substring(toffset).startsWith(prefix)
     *         </code></p>
     */
    public boolean startsWith(byte[] prefix, int toffset) {
        return startsWith(prefix, 0, prefix.length, toffset);
    }

    /**
     * Tests if the substring of this string beginning at the specified index starts with the 
     * specified prefix.
     * 
     * <p>(This description from {@link java.lang.String#startsWith String.startsWith})</p>
     * 
     * @param prefix the prefix
     * @param offset the index of the first byte to search for
     * @param length the length of the byte array to search for
     * @return true if the byte sequence represented by the argument is a prefix of the 
     *         substring of this object starting at index toffset; false otherwise. The 
     *         result is false if toffset is negative or greater than the length of this 
     *         ByteString object; otherwise the result is the same as the result of the 
     *         expression
     *         
     *         <p><code>
     *         this.substring(toffset).startsWith(prefix)
     *         </code></p>
     */
    public boolean startsWith(byte[] prefix, int offset, int length, int toffset) {
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

    /**
     * Returns a new string that is a substring of this string. The substring begins 
     * with the byte at the specified index and extends to the end of this string.
     * 
     * <p>(This description from {@link java.lang.String#substring String.substring})</p>
     * 
     * @param beginIndex the beginning index, inclusive.
     * @return the specified substring
     * @throws IndexOutOfBoundsException if beginIndex is negative or larger than the 
     *         length of this String object.
     */
    public ByteString substring(int beginIndex) {
        return substring(beginIndex, this.length);
    }

    /**
     * Returns a new string that is a substring of this string. The substring begins at 
     * the specified beginIndex and extends to the byte at index endIndex - 1. Thus the 
     * length of the substring is endIndex - beginIndex.
     * 
     * @param beginIndex the beginning index, inclusive.
     * @param endIndex the ending index, exclusive.
     * @return the spedcified substring
     * @throws IndexOutOfBoundsException if the beginIndex is negative, or endIndex is 
     *         larger than the length of this String object, or beginIndex is larger 
     *         than endIndex.
     */
    public ByteString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0)
            throw new ArrayIndexOutOfBoundsException(beginIndex);
        if (endIndex > this.length)
            throw new ArrayIndexOutOfBoundsException(endIndex);
        if (endIndex < beginIndex)
            throw new ArrayIndexOutOfBoundsException(endIndex - beginIndex);
        if (beginIndex == 0 && endIndex == this.length)
            return this;
        if (beginIndex == endIndex)
            return EMPTY;
        return new ByteString(offset + beginIndex, endIndex - beginIndex, string);
    }

    public ByteString toBinaryString() {
        return this;
    }

    public byte[] toByteArray() {
        return Arrays.copyOfRange(string, offset, offset + length);
    }

    public ByteBuffer toByteBuffer() {
        return ByteBuffer.wrap(string, offset, length).asReadOnlyBuffer();
    }

    public String toString() {
        int hashCode = hashCode();
        return "[len="+length+", hashCode="+hashCode+"]";
    }

    public String toString(Charset charset) {
        return new String(string, offset, length, charset);
    }

    public String toFullString() {
        return Arrays.toString(string);
    }
    
    public ByteString compact() {
        if (offset > 0) {
            byte[] newString = new byte[length];
            System.arraycopy(string, offset, newString, 0, length);
            return new ByteString(newString);
        } else {
            return this;
        }
    }

    public int footprint() {
        return string.length;
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(string, offset, length);
    }
    
    public InputStream asInputStream() {
        return new ByteStringInputStream(string, offset, length);
    }
    
    public static final ByteString from(int... bytes) {
        if (bytes.length == 0)
            return EMPTY;
        byte[] buffer = new byte[bytes.length];
        for (int i=0; i<buffer.length; ++i) {
            buffer[i] = (byte) bytes[i];
        }
        return new ByteString(0, buffer.length, buffer);
    }

    public static final ByteString from(byte[] byteArray) {
        if (byteArray == null)
            throw new IllegalArgumentException();
        return from(byteArray, 0, byteArray.length);
    }

    public static final ByteString from(byte[] byteArray, int offset, int length) {
        if (byteArray == null)
            throw new IllegalArgumentException();
        if (length > byteArray.length)
            throw new IllegalArgumentException();
        if (length == 0)
            return EMPTY;
        byte[] buffer = new byte[length];
        System.arraycopy(byteArray, offset, buffer, 0, length);
        return new ByteString(0, length, buffer);
    }

    public static final ByteString from(ByteBuffer byteBuffer) {
        if (byteBuffer == null)
            throw new IllegalArgumentException();
        return from(byteBuffer, byteBuffer.remaining());
    }
    
    public static final ByteString from(ByteBuffer byteBuffer, int count) {
        if (byteBuffer == null)
            throw new IllegalArgumentException();
        byte[] buffer = new byte[count];
        byteBuffer.get(buffer);
        return new ByteString(0, count, buffer);
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
