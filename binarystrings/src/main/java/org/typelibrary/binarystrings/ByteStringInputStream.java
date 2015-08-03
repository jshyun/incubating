package org.typelibrary.binarystrings;

import java.io.IOException;
import java.io.InputStream;

final class ByteStringInputStream extends InputStream {

    private final byte[] buffer;
    private final int offset;
    private final int length;
    private int pos;

    ByteStringInputStream(byte[] buffer, int offset, int length) {
        this.buffer = buffer;
        this.offset = offset;
        this.length = length;
    }
    
    @Override
    public int available() throws IOException {
        return length - pos;
    }

    @Override
    public int read() throws IOException {

        if (pos == length)
            return -1;

        return buffer[offset + pos++];
    }

    @Override
    public int read(byte[] b, int offset, int length) throws IOException {

        if (b == null)
            throw new NullPointerException();
        if (offset < 0)
            throw new IndexOutOfBoundsException();
        if (b.length < (offset + length))
            throw new IndexOutOfBoundsException();
        
        length = Math.min(length, this.length - this.pos);
        
        System.arraycopy(this.buffer, this.offset + this.pos, b, offset, length);
        
        return length;
    }

    @Override
    public long skip(long count) throws IOException {
        if (count < 0)
            return 0;
        
        count = Math.min(count, length - pos);
        
        pos += count;
        
        return count;
    }
    
}
