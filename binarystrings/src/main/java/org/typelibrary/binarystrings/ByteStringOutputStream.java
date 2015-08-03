package org.typelibrary.binarystrings;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public final class ByteStringOutputStream extends OutputStream {

    private final LinkedList<byte[]> chunks = new LinkedList<byte[]>();
    private final int chunkSize;
    
    private byte[] currentChunk;
    private int chunkPos;
    private int flushedBytes;    

    public ByteStringOutputStream() {
        this(1024);
    }
    
    public ByteStringOutputStream(int chunkSize) {
        if (chunkSize < 1)
            throw new IllegalArgumentException();
        this.chunkSize = chunkSize;
    }
    
    @Override
    public synchronized void write(int abyte) throws IOException {
        
        checkChunk(1);

        currentChunk[chunkPos++] = (byte) abyte;
    }

    @Override
    public synchronized void write(byte[] bytes, int off, int len) throws IOException {

        if (bytes == null)
            throw new NullPointerException("Bytes cannot be null");
        
        checkChunk(len);

        if (chunkPos + len > currentChunk.length) {
            int topOff = Math.min(len, currentChunk.length - chunkPos);
            System.arraycopy(bytes, off, currentChunk, chunkPos, topOff);
            off += topOff;
            len -= topOff;
            chunkPos += topOff;
            checkChunk(len);
        }

        if (len > 0) {
            System.arraycopy(bytes, off, currentChunk, chunkPos, len);
            chunkPos += len;
        }

    }

    private void checkChunk(int currentWrite) {
        
        if (currentChunk == null) {
            if (currentWrite < chunkSize)
                currentChunk = new byte[chunkSize];
            else
                currentChunk = new byte[currentWrite];
        }

        if (chunkPos == currentChunk.length) {
            chunks.add(currentChunk);
            flushedBytes += currentChunk.length;

            if (currentWrite < chunkSize)
                currentChunk = new byte[chunkSize];
            else
                currentChunk = new byte[currentWrite];
            
            chunkPos = 0;
        }

    }

    synchronized int numChunks() {
        return chunks.size();
    }
    
    public synchronized int size() {
        return flushedBytes + chunkPos;
    }

    public synchronized void reset() {
        chunks.clear();
        currentChunk = null;
        flushedBytes = 0;
        chunkPos = 0;
    }

    public String toString() {
        return "";
    }
    
    public synchronized ByteString toByteString() {

        int totalLength = chunkPos;
        for(byte[] chunk : chunks) {
            totalLength += chunk.length;
        }
        byte[] buffer = new byte[totalLength];
        
        int pos = 0;
        for(byte[] chunk : chunks) {
            System.arraycopy(chunk, 0, buffer, pos, chunk.length);
            pos += chunk.length;
        }
        
        if (chunkPos > 0)
            System.arraycopy(currentChunk, 0, buffer, pos, chunkPos);
        
        reset();
        
        chunks.add(buffer);
        flushedBytes = buffer.length; 
        
        return new ByteString(0, totalLength, buffer);

    }
}
