package br.com.lca.api.domain.model.enums;

public enum Type {

    CABO("CB"),
    PEDESTAL("PD"),
    CAIXA("CX"),
    MICROFONE("MF"),
    LENTE("LT"),
    BATERIA("BT"),
    SWITCHER("SW"),
    CONTROLLER("CT"),
    MONITOR("MN"),
    PROCESSADOR("PS"),
    TELEVISAO("TV"),
    FLASH("FL"),
    LED("LD"),
    TUNGSTENIO("TG"),
    FLUORECENTE("FR"),
    MEDIDOR("MD");

    private final String code;

    Type(String code) {
        this.code = code;
    }

    public static Type fromCode(final String code) {
        for (final Type t : Type.values()) {
            if (t.code.equalsIgnoreCase(code)) {
                return t;
            }
        }
        throw new IllegalArgumentException(code);
    }
}
