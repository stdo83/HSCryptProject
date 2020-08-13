import java.util.*;

class AsciiCharSequence implements java.lang.CharSequence/* extends/implements */ {
    byte[] seq;

    AsciiCharSequence(byte[] seq) {
        this.seq = seq;
    }

    @Override
    public int length() {
        return this.seq.length;
    }

    @Override
    public char charAt(int i) {
        return (char) this.seq[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        int subSeqLen = i1 - i;
        byte[] subSeqArray = new byte[subSeqLen];
        System.out.println(subSeqLen);
        System.out.println(subSeqArray);
        for (int j = 0; j < subSeqLen; j++) {
            subSeqArray[j] = this.seq[i + j];
        }
        AsciiCharSequence result = new AsciiCharSequence(subSeqArray);
        return result;
    }

    @Override
    public String toString() {
        String result = new String(seq);
        return result;
    }
    // implementation
}
//
//class Main {
//    public static void main(String[] args) {
//        AsciiCharSequence test = new AsciiCharSequence(new byte[]{'a', 'b', 'c'});
//        System.out.println(test.charAt(1));
//        System.out.println(test.toString());
//        System.out.println(test.subSequence(1, 2).toString());
//    }
//}