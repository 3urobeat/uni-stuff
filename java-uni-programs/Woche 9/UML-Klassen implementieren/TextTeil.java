class TextTeil extends Teil implements Beschriftet {
    
    private char aufschrift;
    
    public TextTeil(int links, int rechts, char aufschrift) {
        super(links, rechts);
        
        this.aufschrift = aufschrift;
    }
    
    public char getAufschrift() { return aufschrift; }
    
    protected boolean passt(char seite, Teil nachbar) {
        if (seite == 'L') {
            return super.passt('L', nachbar) && !super.passt('R', nachbar);
        }
        
        if (seite == 'R') {
            return super.passt('R', nachbar) && !super.passt('L', nachbar);
        }
        
        return false; //invalid character
    }
    
}