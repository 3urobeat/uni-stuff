class Teil {
    
    protected int links;
    protected int rechts;
    
    public Teil(int links, int rechts) {
        this.links = links;
        this.rechts = rechts;
    }
    
    protected boolean passt(char seite, Teil nachbar) {
        if (seite == 'L') {
            return this.links == nachbar.rechts;
        }
        
        if (seite == 'R') {
            return this.rechts == nachbar.links;
        }
        
        return false; //invalid character
    }
}