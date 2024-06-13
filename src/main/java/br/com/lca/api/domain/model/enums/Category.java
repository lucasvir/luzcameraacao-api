package br.com.lca.api.domain.model.enums;

public enum Category {

    LUZ("LZ"),
    CAMERA("CM"),
    AUDIO("AD");

    private final String code;

    Category(String code) {
        this.code = code;
    }

    public static Category fromCode(final String code) {
        for (final Category category : Category.values()) {
            if (category.code.equalsIgnoreCase(code)) {
                return category;
            }
        }
        throw new IllegalArgumentException(code);
    }
}
