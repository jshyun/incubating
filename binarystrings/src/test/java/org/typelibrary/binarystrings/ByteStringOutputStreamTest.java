package org.typelibrary.binarystrings;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;



public class ByteStringOutputStreamTest {

    @Test
    public void testBasic() throws IOException {
        
        ByteStringOutputStream bout = new ByteStringOutputStream();
        bout.write(1);
        bout.write(2);
        bout.write(3);
        bout.write(4);
        
        Assert.assertEquals(4, bout.size());
        Assert.assertEquals(0, bout.numChunks());
        
        ByteString string = bout.toByteString();
        Assert.assertEquals(4, bout.size());
        Assert.assertEquals(4, string.length());
        Assert.assertEquals(1, bout.numChunks());
        
        Assert.assertArrayEquals(new byte[] { 1, 2, 3, 4 } , string.toByteArray());
        
    }

    @Test
    public void testBuffer() throws IOException {
        
        ByteStringOutputStream bout = new ByteStringOutputStream();
        bout.write(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 3, 2);
        bout.write(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 0, 1);
        bout.write(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 7, 2);
        bout.write(100);
        
        Assert.assertEquals(6, bout.size());
        Assert.assertEquals(0, bout.numChunks());
        
        ByteString string = bout.toByteString();
        Assert.assertEquals(6, bout.size());
        Assert.assertEquals(6, string.length());
        Assert.assertEquals(1, bout.numChunks());
        
        Assert.assertArrayEquals(new byte[] { 4, 5, 1, 8, 9, 100 } , string.toByteArray());
        
    }

    @Test
    public void testChunkFlush() throws IOException {
        
        ByteStringOutputStream bout = new ByteStringOutputStream(8);
        bout.write(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 });

        Assert.assertEquals(8, bout.size());
        Assert.assertEquals(0, bout.numChunks());
        
        bout.write(9);
        Assert.assertEquals(9, bout.size());
        Assert.assertEquals(1, bout.numChunks());
        
        ByteString string = bout.toByteString();
        Assert.assertEquals(9, bout.size());
        Assert.assertEquals(9, string.length());
        
        Assert.assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 } , string.toByteArray());
        
    }

    @Test
    public void testChunkOverflow() throws IOException {
        
        ByteStringOutputStream bout = new ByteStringOutputStream(8);
        bout.write(new byte[] { 1, 2, 3, 4, 5, 6, 7 });

        Assert.assertEquals(7, bout.size());
        Assert.assertEquals(0, bout.numChunks());
        
        bout.write(new byte[] { 8, 9, 10, 11, 12, 13, 14, 15, 16 });
        Assert.assertEquals(16, bout.size());
        Assert.assertEquals(1, bout.numChunks());
        
        ByteString string = bout.toByteString();
        Assert.assertEquals(16, bout.size());
        Assert.assertEquals(16, string.length());
        
        Assert.assertArrayEquals(
                new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 },
                string.toByteArray());
        
    }

    @Test
    public void testChunkOversize() throws IOException {
        
        ByteStringOutputStream bout = new ByteStringOutputStream(4);
        bout.write(new byte[] { 1, 2, 3 });

        Assert.assertEquals(3, bout.size());
        Assert.assertEquals(0, bout.numChunks());
        
        bout.write(new byte[] { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
        Assert.assertEquals(16, bout.size());
        Assert.assertEquals(1, bout.numChunks());
        
        ByteString string = bout.toByteString();
        Assert.assertEquals(16, bout.size());
        Assert.assertEquals(16, string.length());
        
        Assert.assertArrayEquals(
                new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 },
                string.toByteArray());
        
    }

}
