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

import org.junit.Assert;
import org.junit.Test;

public class NameTest {

    @Test
    public void testName() {
        
        assertValid("www.amazon.com.", true);
        assertValid("www.amazon.com", false);
        assertValid("com.", true);
        assertValid("com", false);
        assertValid("A1.com.", true);
        assertValid("A1.com", false);
        assertValid("A-A1.com.", true);
        assertValid("A-A1.com", false);
        assertValid("A-1.com.", true);
        assertValid("A-1.com", false);
        assertValid("A-1-A.com.", true);
        assertValid("A-1-A.com", false);
        assertValid("A01234567890123456789012345678901234567890123456789012345678901.", true);
        assertValid("A01234567890123456789012345678901234567890123456789012345678901", false);
        assertParseException("A012345678901234567890123456789012345678901234567890123456789012.");
        assertParseException("A012345678901234567890123456789012345678901234567890123456789012");
        
        assertException(null, IllegalArgumentException.class);
        assertParseException("");

        assertParseException("1.com");
        assertParseException("1-.com");
        assertParseException("-.com");
        assertParseException("-1.com");
        assertParseException("-A.com");
        assertParseException("A-.com");
        assertParseException("A--A.com");
        assertParseException("A..A.com");
        assertParseException(".com");
        
        char[] str = new char[256];
        str[0] = 'A';
        for (int i=1; i<256; ++i) {
            if (i%63 == 0)
                str[i] = '.';
            else
                str[i] = 'B';
        }
        assertValid(new String(str, 0, 255), false);
        assertParseException(new String(str, 0, 256));
        
    }

    protected void assertValid(String str, boolean absolute) {
        Name name = Name.fromString(str);
        Assert.assertEquals(absolute, name.isAbsolute());
        Assert.assertEquals(str, name.getName());
    }
    
    protected void assertParseException(String name) {
        try {
            Name aname = Name.fromString(name);
            Assert.fail("Expected parse exception. Got: " + aname);
        } catch (ParseException e) {
            // Expected
        }
    }
    
    protected void assertException(String name, Class<? extends RuntimeException> etype) {
        try {
            Name aname = Name.fromString(name);
            Assert.fail("Expected parse exception. Got: " + aname);
        } catch (RuntimeException e) {
            if (!etype.isInstance(e))
                Assert.fail("Expected exception of type " + etype + ", got: " + e);
        }
    }

}
