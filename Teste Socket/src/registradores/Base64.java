package registradores;

public class Base64
{
	private static final String base64code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";
 
    private static final int splitLinesAt = 76;
 
    public static byte[] zeroPad(int length, byte[] bytes) {
        byte[] padded = new byte[length]; // inicializado como zero pela JVM
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }
 
    public static String encode(String string) {
 
        String encoded = "";
        byte[] stringArray;
        try {
            stringArray = string.getBytes("UTF-8");  // use a string de codifica��o adequada!
        } catch (Exception ignored) {
            stringArray = string.getBytes();  // use o local padr�o em vez disso
        }
        // determine quantos bytes de preenchimento voc� ir� adicionar ao resultado
        int paddingCount = (3 - (stringArray.length % 3)) % 3;
        // adicione o preenchimento necess�rio na entrada
        stringArray = zeroPad(stringArray.length + paddingCount, stringArray);
        // processe 3 bytes por vez, criando 4 bytes de resultado
        // se preocupe com as inser��es de nova linha depois
        for (int i = 0; i < stringArray.length; i += 3) {
            int j = ((stringArray[i] & 0xff) << 16) +
                ((stringArray[i + 1] & 0xff) << 8) + 
                (stringArray[i + 2] & 0xff);
            encoded = encoded + base64code.charAt((j >> 18) & 0x3f) +
                base64code.charAt((j >> 12) & 0x3f) +
                base64code.charAt((j >> 6) & 0x3f) +
                base64code.charAt(j & 0x3f);
        }
        // substitua o preenchimento nulo com "="
        return splitLines(encoded.substring(0, encoded.length() -
            paddingCount) + "==".substring(0, paddingCount));
 
    }
    public static String splitLines(String string) {
 
        String lines = "";
        for (int i = 0; i < string.length(); i += splitLinesAt) {
 
            lines += string.substring(i, Math.min(string.length(), i + splitLinesAt));
            lines += "\r\n";
 
        }
        return lines;
 
    }
    public static void main(String[] args) {
 
        for (int i = 0; i < args.length; i++) {
 
            System.err.println("encoding \"" + args[i] + "\"");
            System.out.println(encode(args[i]));
 
        }
 
    }
}
