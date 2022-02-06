package com.company;
/**
 * Codes d'échappement (escape codes) pour modifier la couleur d'affichage.
 *
 * @author  Eric Drouin
 * @version 0.1
 * @since   2021-03-29
 */
public enum Colors {

    // couleur de la bordure
    BORDER("\033[38;5;250m"),

    // couleur d'une cellule vide
    EMPTY("\033[38;5;250m"),

    // couleur du prompt
    PROMPT("\033[38;5;28m"),

    // couleurs des véhicules
    // ATTENTION : la première lettre de chaque couleur doit OBLIGATOIREMENT être UNIQUE
    ARMY("\u001b[38;5;58m"),      // vert armée
    BABY("\u001b[38;5;74m"),      // bleu bébé
    CREAM("\u001b[38;5;150m"),    // crème (jaune très doux)
    ELECTRIC("\u001b[38;5;26m"),  // électrique (bleu vif)
    GREEN("\u001b[38;5;28m"),
    RESET("\u001b[0m"),
    IRON("\u001b[38;5;239m"),     // gris foncé
    LILAC("\u001b[38;5;140m"),    // lilas
    MINT("\u001b[38;5;36m"),      // menthe
    ORANGE("\u001b[38;5;172m"),
    PINK("\u001b[38;5;175m"),
    RED("\u001b[38;5;124m"),
    SADDLE("\u001b[38;5;95m"),    // brun
    TURQUOISE("\u001b[38;5;72m"),
    VIOLET("\u001b[38;5;99m"),
    WHEAT("\u001b[38;5;137m"),    // marron très doux, beige
    YELLOW("\u001b[38;5;3m");

    private final String code;

    Colors(String code) { this.code = code; }

    @Override
    public String toString() { return code; }
}