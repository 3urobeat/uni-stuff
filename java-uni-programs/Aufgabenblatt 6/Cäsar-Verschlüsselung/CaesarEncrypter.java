public class CaesarEncrypter implements Encrypter {
    
    private int offset;
    
    private String alphabet    = "abcdefghijklmnopqrstuvwxyz .,!?";
    private char[] alphabetArr = alphabet.toCharArray();
    
    public CaesarEncrypter(int offset) {
        this.offset = offset;
    }
    
    public String encrypt(String message) {
        String tempStr = "";
        
        for (String e : message.split("")) {
            int index = alphabet.indexOf(e);

            //Check if we have to jump to the beginning
            if (index + offset > alphabet.length() - 1) {
                tempStr += alphabetArr[offset - (alphabet.length() - index)];
            } else {
                tempStr += alphabetArr[index + offset];
            }
        }
        
        return tempStr;
    }
    
    public String decrypt(String message) {
        String tempStr = "";
        
        for (String e : message.split("")) {
            int index = alphabet.indexOf(e);

            //Check if we have to jump to the end
            if (index - offset < 0) {
                tempStr += alphabetArr[alphabet.length() - (offset - index)];
            } else {
                tempStr += alphabetArr[index - offset];
            }
        }
        
        return tempStr;
    }
    
}