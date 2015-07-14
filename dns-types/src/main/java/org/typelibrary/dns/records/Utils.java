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

final class Utils {

    private Utils() {}
    
    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F', };
    
    public static final String toGroupedHexArray(byte[] buffer, int interval, char separator) {
        
        if (interval < 1) throw new IllegalArgumentException("Interval must be > 0");
        
        int length = buffer.length;
        int numGroups = length/interval + (((length%interval)>0) ? 1 : 0);
        char[] address = new char[(length<<1)+(numGroups > 1 ? (numGroups-1) : 0)];

        int charPos = 0;
        for(int i=0; i<length; ++i) {
            if (i > 0 && (i%interval == 0)) {
                address[charPos] = separator;
                ++charPos;
            }
            int c = buffer[i];
            int index = (c & 0xF0) >>> 4;
            address[charPos] = HEX[index];
            ++charPos;
            index = c & 0x0F;
            address[charPos] = HEX[index];
            ++charPos;
        }
        
        return new String(address);
    }

}
