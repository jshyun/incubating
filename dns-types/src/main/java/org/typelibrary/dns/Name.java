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

import java.nio.charset.Charset;

public final class Name {

    private static final Charset ASCII = Charset.forName("ASCII");
    private static final int MAX_LEN = 255;
    private static final int MAX_LABEL = 63;
    
    private final String name;
    private final boolean absolute;
    
    private Name(String name, boolean absolute) {
        this.name = name;
        this.absolute = absolute;
    }
    
    public final String getName() {
        return name;
    }
    
    public final boolean isAbsolute() {
        return absolute;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Name other = (Name) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public final String toString() {
        return name;
    }
    
    public static final Name fromString(String name) {
        
        if (name == null)
            throw new IllegalArgumentException();
        
        name = name.trim();
        if (name.isEmpty())
            throw new ParseException();

        int state = 0, labelLength = 0, totalLength = 0;
        int len = name.length();
        for (int i=0; i<len; ++i) {
            
            char c = name.charAt(i);
            
            switch(state) {
                case 0:
                    if (!((c >= 65 && c <= 90) || (c >=97 && c <= 122)))
                        throw new ParseException();
                    state = 1;
                    ++labelLength;
                    ++totalLength;
                    break;
                case 1:
                    if (!((c >= 65 && c <= 90) || (c >=97 && c <= 122) || (c >= 48 && c <= 57) || c == 45 || c == 46))
                        throw new ParseException();
                    if (c == 45) {
                        state = 2;
                        ++labelLength;
                    } else if (c == 46) {
                        state = 0;
                        labelLength = 0;
                    } else {
                        ++labelLength;
                    }
                    ++totalLength;
                    break;
                case 2:
                    if (!((c >= 65 && c <= 90) || (c >=97 && c <= 122) || (c >= 48 && c <= 57)))
                        throw new ParseException();
                    state = 1;
                    ++labelLength;
                    ++totalLength;
                    break;
            }
            
            if (labelLength > MAX_LABEL)
                throw new ParseException();

            if (totalLength > MAX_LEN)
                throw new ParseException();
        }

        if (state == 2)
            throw new ParseException();
        
        if (name.endsWith("."))
            return new Name(name, true);
        else
            return new Name(name, false);
        
    }
    
    public static final Name fromByteArray(byte[] bytes, int offset, int length) {
        
        if (bytes == null)
            throw new IllegalArgumentException();
        
        int state = 0, labelLength = 0, totalLength = 0;
        int max = offset + length;
        for (int i=offset; i<max; ++i) {
            
            char c = (char) bytes[i];
            
            switch(state) {
                case 0:
                    if (!((c >= 65 && c <= 90) || (c >=97 && c <= 122)))
                        throw new ParseException();
                    state = 1;
                    ++labelLength;
                    ++totalLength;
                    break;
                case 1:
                    if (!((c >= 65 && c <= 90) || (c >=97 && c <= 122) || (c >= 48 && c <= 57) || c == 45 || c == 46))
                        throw new ParseException();
                    if (c == 45) {
                        state = 2;
                        ++labelLength;
                    } else if (c == 46) {
                        state = 0;
                        labelLength = 0;
                    } else {
                        ++labelLength;
                    }
                    ++totalLength;
                    break;
                case 2:
                    if (!((c >= 65 && c <= 90) || (c >=97 && c <= 122) || (c >= 48 && c <= 57)))
                        throw new ParseException();
                    state = 1;
                    ++labelLength;
                    ++totalLength;
                    break;
            }
            
            if (labelLength > MAX_LABEL)
                throw new ParseException();

            if (totalLength > MAX_LEN)
                throw new ParseException();
        }

        if (state == 2)
            throw new ParseException();
        
        String name = new String(bytes, offset, length, ASCII);
        
        if (name.endsWith("."))
            return new Name(name, true);
        else
            return new Name(name, false);
        
    }

}
