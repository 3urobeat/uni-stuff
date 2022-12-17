/**
 * Das Interface Encrypter repraesentiert Verschluesselungsverfahren, die
 * Zeichenketten zu anderen Zeichenketten verschluesseln. Es bietet zwei
 * Methoden zum Ver- und Entschluesseln.
 */
public interface Encrypter {
    /**
     * Verschluessele eine geheime Botschaft als Geheimtext.
     * @param message Die zu verschluesselnde Botschaft als Klartext.
     * @return Die verschluesselte Botschaft, der unlesbare Geheimtext.
     */
    public String encrypt(String message);
    
    /**
     * Entschluessele eine geheime Botschaft zu Klartext.
     * @param message verschluesselte Botschaft, der unlesbare Geheimtext.
     * @return Die zu verschluesselnde Botschaft als Klartext.
     */
    public String decrypt(String message);
}
