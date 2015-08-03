package org.typelibrary.binarystrings;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;

public class ByteStringTest {

    protected final ByteString STR0 = ByteString.from(0); 
    protected final ByteString STR1 = ByteString.from(1);
    protected final ByteString STR2 = ByteString.from(2);
    protected final ByteString STR3 = ByteString.from(3);
    protected final ByteString STR01 = ByteString.from(0,1);
    protected final ByteString STR12 = ByteString.from(1,2);
    protected final ByteString STR123 = ByteString.from(1,2,3);
    protected final ByteString STR321 = ByteString.from(3,2,1);
    protected final ByteString STR1TO9 = ByteString.from(1,2,3,4,5,6,7,8,9);
    protected final ByteString STR9TO1 = ByteString.from(9,8,7,6,5,4,3,2,1);
    protected final ByteString STR4TO9 = ByteString.from(4,5,6,7,8,9);
    
    protected final byte[] B = new byte[0];
    protected final byte[] B0 = new byte[] { 0 };
    protected final byte[] B1 = new byte[] { 1 };
    protected final byte[] B2 = new byte[] { 2 };
    protected final byte[] B3 = new byte[] { 3 };
    protected final byte[] B01 = new byte[] { 0, 1 };
    protected final byte[] B12 = new byte[] { 1, 2 };
    protected final byte[] B123 = new byte[] { 1, 2, 3 };
    protected final byte[] B321 = new byte[] { 3, 2, 1 };
    protected final byte[] B1TO9 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    protected final byte[] B4TO9 = new byte[] { 4, 5, 6, 7, 8, 9 };

    protected ByteString string;
    
    @Test
    public void testConstructors() {
        
        string = new ByteString(B1TO9);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(9, string.length());
        Assert.assertEquals(9, string.footprint());
        assertByteAt(string, B1TO9);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        string = new ByteString(string);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(9, string.length());
        Assert.assertEquals(9, string.footprint());
        assertByteAt(string, B1TO9);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        string = new ByteString(string, 2, 3);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(3, string.length());
        Assert.assertEquals(3, string.footprint());
        Assert.assertEquals(4, string.byteAt(1));
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        string = new ByteString(B1TO9, 2, 3);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(3, string.length());
        Assert.assertEquals(3, string.footprint());
        Assert.assertEquals(4, string.byteAt(1));
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(9);
        byteBuffer.put(B1TO9);
        byteBuffer.flip();
        string = ByteString.from(byteBuffer);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(9, string.length());
        Assert.assertEquals(9, string.footprint());
        assertByteAt(string, B1TO9);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        byteBuffer = ByteBuffer.allocateDirect(9);
        byteBuffer.put(B1TO9);
        byteBuffer.flip();
        string = ByteString.from(byteBuffer);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(9, string.length());
        Assert.assertEquals(9, string.footprint());
        assertByteAt(string, B1TO9);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

        string = ByteString.from(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(9, string.length());
        Assert.assertEquals(9, string.footprint());
        assertByteAt(string, B1TO9);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

    }

    @Test
    public void testConstructorsZeroLength() {
        
        string = new ByteString(B);
        Assert.assertEquals(true, string.isEmpty());
        Assert.assertEquals(0, string.length());
        Assert.assertEquals(0, string.footprint());
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR0, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        string = new ByteString(ByteString.EMPTY);
        Assert.assertEquals(true, string.isEmpty());
        Assert.assertEquals(0, string.length());
        Assert.assertEquals(0, string.footprint());
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR0, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        string = new ByteString(string, 4, 0);
        Assert.assertEquals(true, string.isEmpty());
        Assert.assertEquals(0, string.length());
        Assert.assertEquals(0, string.footprint());
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR0, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        string = new ByteString(B1TO9, 4, 0);
        Assert.assertEquals(true, string.isEmpty());
        Assert.assertEquals(0, string.length());
        Assert.assertEquals(0, string.footprint());
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR0, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(0);
        byteBuffer.flip();
        string = ByteString.from(byteBuffer);
        Assert.assertEquals(true, string.isEmpty());
        Assert.assertEquals(0, string.length());
        Assert.assertEquals(0, string.footprint());
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR0, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());
        
        byteBuffer = ByteBuffer.allocateDirect(0);
        byteBuffer.flip();
        string = ByteString.from(byteBuffer);
        Assert.assertEquals(true, string.isEmpty());
        Assert.assertEquals(0, string.length());
        Assert.assertEquals(0, string.footprint());
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR0, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

    }
    
    @Test
    public void testByteAt() {

        string = ByteString.EMPTY;
        assertByteAtThrows(string, 0);
        assertByteAtThrows(string, 1);
        assertByteAtThrows(string, -1);

        string = STR1TO9;
        assertByteAt(string, B1TO9);
        assertByteAtThrows(string, 9);
        assertByteAtThrows(string, -1);

        string = STR1TO9.substring(3);
        assertByteAt(string, B4TO9);
        assertByteAtThrows(string, 6);
        assertByteAtThrows(string, -1);

    }

    @Test
    public void testConcat() {

        string = STR1.concat(STR2).concat(STR3);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(3, string.length());
        Assert.assertEquals(3, string.footprint());
        assertByteAt(string, B123);
        assertByteAtThrows(string, 3);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

        string = ByteString.EMPTY.concat(STR1,STR2,STR3);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(3, string.length());
        Assert.assertEquals(3, string.footprint());
        assertByteAt(string, B123);
        assertByteAtThrows(string, 3);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

        string = ByteString.EMPTY.concat(B123);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(3, string.length());
        Assert.assertEquals(3, string.footprint());
        assertByteAt(string, B123);
        assertByteAtThrows(string, 3);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

        string = ByteString.EMPTY.concat(B1TO9, 0, 3);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(3, string.length());
        Assert.assertEquals(3, string.footprint());
        assertByteAt(string, B123);
        assertByteAtThrows(string, 3);
        assertByteAtThrows(string, -1);
        Assert.assertEquals(string, string);
        Assert.assertEquals(string, new ByteString(string));
        Assert.assertNotEquals(STR9TO1, string);
        Assert.assertTrue(string == string.toBinaryString());
        Assert.assertTrue(string == string.compact());

        string = ByteString.EMPTY;
        Assert.assertTrue(string == string.concat(ByteString.EMPTY));
        Assert.assertTrue(string == string.concat(ByteString.EMPTY,ByteString.EMPTY));
        Assert.assertTrue(string == string.concat(new byte[0]));
        Assert.assertTrue(string == string.concat(B1TO9, 5, 0));

    }

    @Test
    public void testContains() {

        string = ByteString.EMPTY;
        Assert.assertEquals(true, string.contains(ByteString.EMPTY));
        Assert.assertEquals(false, string.contains(STR0));
        Assert.assertEquals(false, string.contains(STR1));
        Assert.assertEquals(false, string.contains(STR123));
        Assert.assertEquals(true, string.contains(B));
        Assert.assertEquals(false, string.contains(B0));
        Assert.assertEquals(false, string.contains(B01));
        Assert.assertEquals(true, string.contains(B01, 0, 0));
        Assert.assertEquals(false, string.contains(B01, 0, 1));
        
        string = STR0;
        Assert.assertEquals(true, string.contains(ByteString.EMPTY));
        Assert.assertEquals(true, string.contains(string));
        Assert.assertEquals(true, string.contains(STR0));
        Assert.assertEquals(false, string.contains(STR1));
        Assert.assertEquals(false, string.contains(STR123));
        Assert.assertEquals(true, string.contains(B));
        Assert.assertEquals(true, string.contains(B0));
        Assert.assertEquals(false, string.contains(B1));
        Assert.assertEquals(false, string.contains(B123));
        Assert.assertEquals(true, string.contains(B01, 0, 0));
        Assert.assertEquals(true, string.contains(B01, 0, 1));
        Assert.assertEquals(false, string.contains(B01, 1, 1));
        
        string = STR1TO9;
        Assert.assertEquals(true, string.contains(ByteString.EMPTY));
        Assert.assertEquals(true, string.contains(string));
        Assert.assertEquals(false, string.contains(STR0));
        Assert.assertEquals(true, string.contains(STR1));
        Assert.assertEquals(true, string.contains(STR123));
        Assert.assertEquals(false, string.contains(STR321));
        Assert.assertEquals(true, string.contains(STR4TO9));
        Assert.assertEquals(true, string.contains(B));
        Assert.assertEquals(false, string.contains(B0));
        Assert.assertEquals(true, string.contains(B1));
        Assert.assertEquals(true, string.contains(B123));
        Assert.assertEquals(false, string.contains(B321));
        Assert.assertEquals(true, string.contains(B4TO9));
        Assert.assertEquals(true, string.contains(B01, 0, 0));
        Assert.assertEquals(false, string.contains(B01, 0, 1));
        Assert.assertEquals(true, string.contains(B01, 1, 1));

    }

    @Test
    public void testIndexOf() {

        string = ByteString.EMPTY;
        Assert.assertEquals(0, string.indexOf(ByteString.EMPTY));
        Assert.assertEquals(-1, string.indexOf(STR0));
        Assert.assertEquals(-1, string.indexOf(STR1));
        Assert.assertEquals(-1, string.indexOf(STR123));
        Assert.assertEquals(0, string.indexOf(B));
        Assert.assertEquals(-1, string.indexOf(B0));
        Assert.assertEquals(-1, string.indexOf(B01));
        Assert.assertEquals(0, string.indexOf(B01, 0, 0, 0));
        Assert.assertEquals(-1, string.indexOf(B01, 0, 1, 0));
        
        string = STR0;
        Assert.assertEquals(0, string.indexOf(ByteString.EMPTY));
        Assert.assertEquals(0, string.indexOf(string));
        Assert.assertEquals(0, string.indexOf(STR0));
        Assert.assertEquals(-1, string.indexOf(STR1));
        Assert.assertEquals(-1, string.indexOf(STR123));
        Assert.assertEquals(0, string.indexOf(B));
        Assert.assertEquals(0, string.indexOf(B0));
        Assert.assertEquals(-1, string.indexOf(B1));
        Assert.assertEquals(-1, string.indexOf(B123));
        Assert.assertEquals(0, string.indexOf(B01, 0, 0, 0));
        Assert.assertEquals(0, string.indexOf(B01, 0, 1, 0));
        Assert.assertEquals(-1, string.indexOf(B01, 1, 1, 0));
        
        string = STR1TO9;
        Assert.assertEquals(0, string.indexOf(ByteString.EMPTY));
        Assert.assertEquals(0, string.indexOf(string));
        Assert.assertEquals(-1, string.indexOf(STR0));
        Assert.assertEquals(0, string.indexOf(STR1));
        Assert.assertEquals(0, string.indexOf(STR123));
        Assert.assertEquals(-1, string.indexOf(STR321));
        Assert.assertEquals(3, string.indexOf(STR4TO9));
        Assert.assertEquals(0, string.indexOf(B));
        Assert.assertEquals(-1, string.indexOf(B0));
        Assert.assertEquals(0, string.indexOf(B1));
        Assert.assertEquals(0, string.indexOf(B123));
        Assert.assertEquals(-1, string.indexOf(B321));
        Assert.assertEquals(3, string.indexOf(B4TO9));
        Assert.assertEquals(0, string.indexOf(B01, 0, 0, 0));
        Assert.assertEquals(-1, string.indexOf(B01, 0, 1, 0));
        Assert.assertEquals(0, string.indexOf(B01, 1, 1, 0));
        Assert.assertEquals(6, string.indexOf(B4TO9, 3, 1, 0));
        Assert.assertEquals(1, string.indexOf(B01, 0, 0, 1));
        Assert.assertEquals(-1, string.indexOf(B01, 0, 1, 1));
        Assert.assertEquals(-1, string.indexOf(B01, 1, 1, 1));
        Assert.assertEquals(6, string.indexOf(B4TO9, 3, 1, 3));

    }

    @Test
    public void testStartsWith() {

        string = ByteString.EMPTY;
        Assert.assertEquals(true, string.startsWith(ByteString.EMPTY));
        Assert.assertEquals(false, string.startsWith(STR0));
        Assert.assertEquals(false, string.startsWith(STR1));
        Assert.assertEquals(false, string.startsWith(STR123));
        Assert.assertEquals(true, string.startsWith(B));
        Assert.assertEquals(false, string.startsWith(B0));
        Assert.assertEquals(false, string.startsWith(B01));
        Assert.assertEquals(true, string.startsWith(B01, 0, 0, 0));
        Assert.assertEquals(false, string.startsWith(B01, 0, 1, 0));
        
        string = STR0;
        Assert.assertEquals(true, string.startsWith(ByteString.EMPTY));
        Assert.assertEquals(true, string.startsWith(string));
        Assert.assertEquals(true, string.startsWith(STR0));
        Assert.assertEquals(false, string.startsWith(STR1));
        Assert.assertEquals(false, string.startsWith(STR123));
        Assert.assertEquals(true, string.startsWith(B));
        Assert.assertEquals(true, string.startsWith(B0));
        Assert.assertEquals(false, string.startsWith(B1));
        Assert.assertEquals(false, string.startsWith(B123));
        Assert.assertEquals(true, string.startsWith(B01, 0, 0, 0));
        Assert.assertEquals(true, string.startsWith(B01, 0, 1, 0));
        Assert.assertEquals(false, string.startsWith(B01, 1, 1, 0));
        
        string = STR1TO9;
        Assert.assertEquals(true, string.startsWith(ByteString.EMPTY));
        Assert.assertEquals(true, string.startsWith(string));
        Assert.assertEquals(false, string.startsWith(STR0));
        Assert.assertEquals(true, string.startsWith(STR1));
        Assert.assertEquals(true, string.startsWith(STR123));
        Assert.assertEquals(false, string.startsWith(STR321));
        Assert.assertEquals(false, string.startsWith(STR4TO9));
        Assert.assertEquals(true, string.startsWith(B));
        Assert.assertEquals(false, string.startsWith(B0));
        Assert.assertEquals(true, string.startsWith(B1));
        Assert.assertEquals(true, string.startsWith(B123));
        Assert.assertEquals(false, string.startsWith(B321));
        Assert.assertEquals(false, string.startsWith(B4TO9));
        Assert.assertEquals(false, string.startsWith(STR1, 1));
        Assert.assertEquals(true, string.startsWith(B4TO9, 3));
        Assert.assertEquals(true, string.startsWith(B01, 0, 0, 0));
        Assert.assertEquals(false, string.startsWith(B01, 0, 1, 0));
        Assert.assertEquals(true, string.startsWith(B01, 1, 1, 0));
        Assert.assertEquals(false, string.startsWith(B01, 1, 1, 1));

    }

    @Test
    public void testEndsWith() {

        string = ByteString.EMPTY;
        Assert.assertEquals(true, string.endsWith(ByteString.EMPTY));
        Assert.assertEquals(false, string.endsWith(STR0));
        Assert.assertEquals(false, string.endsWith(STR1));
        Assert.assertEquals(false, string.endsWith(STR123));
        Assert.assertEquals(true, string.endsWith(B));
        Assert.assertEquals(false, string.endsWith(B0));
        Assert.assertEquals(false, string.endsWith(B01));
        Assert.assertEquals(true, string.endsWith(B01, 0, 0));
        Assert.assertEquals(false, string.endsWith(B01, 0, 1));
        
        string = STR0;
        Assert.assertEquals(true, string.endsWith(ByteString.EMPTY));
        Assert.assertEquals(true, string.endsWith(string));
        Assert.assertEquals(true, string.endsWith(STR0));
        Assert.assertEquals(false, string.endsWith(STR1));
        Assert.assertEquals(false, string.endsWith(STR123));
        Assert.assertEquals(true, string.endsWith(B));
        Assert.assertEquals(true, string.endsWith(B0));
        Assert.assertEquals(false, string.endsWith(B1));
        Assert.assertEquals(false, string.endsWith(B123));
        Assert.assertEquals(true, string.endsWith(B01, 0, 0));
        Assert.assertEquals(true, string.endsWith(B01, 0, 1));
        Assert.assertEquals(false, string.endsWith(B01, 1, 1));
        
        string = STR1TO9;
        Assert.assertEquals(true, string.endsWith(ByteString.EMPTY));
        Assert.assertEquals(true, string.endsWith(string));
        Assert.assertEquals(false, string.endsWith(STR0));
        Assert.assertEquals(false, string.endsWith(STR1));
        Assert.assertEquals(false, string.endsWith(STR123));
        Assert.assertEquals(false, string.endsWith(STR321));
        Assert.assertEquals(true, string.endsWith(STR4TO9));
        Assert.assertEquals(true, string.endsWith(B));
        Assert.assertEquals(false, string.endsWith(B0));
        Assert.assertEquals(false, string.endsWith(B1));
        Assert.assertEquals(false, string.endsWith(B123));
        Assert.assertEquals(false, string.endsWith(B321));
        Assert.assertEquals(true, string.endsWith(B4TO9));
        Assert.assertEquals(true, string.endsWith(B01, 0, 0));
        Assert.assertEquals(false, string.endsWith(B01, 0, 1));
        Assert.assertEquals(false, string.endsWith(B01, 1, 1));
        Assert.assertEquals(false, string.endsWith(B01, 1, 1));

    }

    @Test
    public void testLastIndexOf() {

        string = ByteString.EMPTY;
        Assert.assertEquals(0, string.lastIndexOf(ByteString.EMPTY));
        Assert.assertEquals(-1, string.lastIndexOf(STR0));
        Assert.assertEquals(-1, string.lastIndexOf(STR1));
        Assert.assertEquals(-1, string.lastIndexOf(STR123));
        Assert.assertEquals(0, string.lastIndexOf(B));
        Assert.assertEquals(-1, string.lastIndexOf(B0));
        Assert.assertEquals(-1, string.lastIndexOf(B01));
        Assert.assertEquals(0, string.lastIndexOf(B01, 0, 0, 8));
        Assert.assertEquals(-1, string.lastIndexOf(B01, 0, 1, 8));
        
        string = STR0;
        Assert.assertEquals(1, string.lastIndexOf(ByteString.EMPTY));
        Assert.assertEquals(0, string.lastIndexOf(string));
        Assert.assertEquals(0, string.lastIndexOf(STR0));
        Assert.assertEquals(-1, string.lastIndexOf(STR1));
        Assert.assertEquals(-1, string.lastIndexOf(STR123));
        Assert.assertEquals(1, string.lastIndexOf(B));
        Assert.assertEquals(0, string.lastIndexOf(B0));
        Assert.assertEquals(-1, string.lastIndexOf(B1));
        Assert.assertEquals(-1, string.lastIndexOf(B123));
        Assert.assertEquals(1, string.lastIndexOf(B01, 0, 0, 8));
        Assert.assertEquals(0, string.lastIndexOf(B01, 0, 1, 8));
        Assert.assertEquals(-1, string.lastIndexOf(B01, 1, 1, 8));
        
        string = STR1TO9;
        Assert.assertEquals(9, string.lastIndexOf(ByteString.EMPTY));
        Assert.assertEquals(0, string.lastIndexOf(string));
        Assert.assertEquals(-1, string.lastIndexOf(STR0));
        Assert.assertEquals(0, string.lastIndexOf(STR1));
        Assert.assertEquals(0, string.lastIndexOf(STR123));
        Assert.assertEquals(-1, string.lastIndexOf(STR321));
        Assert.assertEquals(3, string.lastIndexOf(STR4TO9));
        Assert.assertEquals(9, string.lastIndexOf(B));
        Assert.assertEquals(-1, string.lastIndexOf(B0));
        Assert.assertEquals(0, string.lastIndexOf(B1));
        Assert.assertEquals(0, string.lastIndexOf(B123));
        Assert.assertEquals(-1, string.lastIndexOf(B321));
        Assert.assertEquals(3, string.lastIndexOf(B4TO9));
        Assert.assertEquals(8, string.lastIndexOf(B01, 0, 0, 8));
        Assert.assertEquals(-1, string.lastIndexOf(B01, 0, 1, 8));
        Assert.assertEquals(0, string.lastIndexOf(B01, 1, 1, 8));
        Assert.assertEquals(6, string.lastIndexOf(B4TO9, 3, 1, 8));
        Assert.assertEquals(8, string.lastIndexOf(B01, 0, 0, 8));
        Assert.assertEquals(-1, string.lastIndexOf(B01, 0, 1, 8));
        Assert.assertEquals(0, string.lastIndexOf(B01, 1, 1, 8));
        Assert.assertEquals(6, string.lastIndexOf(B4TO9, 3, 1, 6));
        Assert.assertEquals(-1, string.lastIndexOf(B4TO9, 3, 1, 5));

    }

    @Test
    public void testSubstring() {
        
        string = STR1TO9.substring(3);
        Assert.assertEquals(false, string.isEmpty());
        Assert.assertEquals(STR4TO9, string);
        Assert.assertEquals(STR4TO9.hashCode(), string.hashCode());
        Assert.assertEquals(9, string.footprint());
   
    }

    @Test
    public void testToByteArray() {
        
        string = STR1;
        Assert.assertArrayEquals(B1, string.toByteArray());

        string = STR1TO9;
        Assert.assertArrayEquals(B1TO9, string.toByteArray());
        
    }

    @Test
    public void testToByteBuffer() {
        
        ByteBuffer buffer;
        
        buffer = STR1.toByteBuffer();
        Assert.assertEquals(1, buffer.remaining());
        Assert.assertEquals(0, buffer.position());
        Assert.assertEquals(1, buffer.limit());
        Assert.assertEquals(true, buffer.isReadOnly());
        Assert.assertEquals(false, buffer.hasArray());

        buffer = STR1TO9.toByteBuffer();
        Assert.assertEquals(9, buffer.remaining());
        Assert.assertEquals(0, buffer.position());
        Assert.assertEquals(9, buffer.limit());
        Assert.assertEquals(true, buffer.isReadOnly());
        Assert.assertEquals(false, buffer.hasArray());

    }
    
    @Test
    public void testToString() {

        Charset charset = Charset.forName("ASCII");
        string = new ByteString("Hello World".getBytes(charset));
        Assert.assertEquals("Hello World", string.toString(charset));

    }
    
    protected void assertByteAt(ByteString string, byte[] bytes) {
        int length = bytes.length;
        for (int i=0; i<length; ++i) {
            Assert.assertEquals(bytes[i], string.byteAt(i));
        }
    }
    
    protected void assertByteAtThrows(ByteString string, int index) {
        try {
            byte b = string.byteAt(index);
            Assert.fail("Expected IndexOutOfBoundsException. got=" + b);
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }
    
    protected void assertSubstringThrows(ByteString string, int beginIndex) {
        try {
            string.substring(beginIndex);
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    protected void assertSubstringThrows(ByteString string, int beginIndex, int endIndex) {
        try {
            string.substring(beginIndex, endIndex);
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

}
