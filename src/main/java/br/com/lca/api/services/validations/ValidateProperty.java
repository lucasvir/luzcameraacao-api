package br.com.lca.api.services.validations;

public record ValidateProperty<T> (Class<T> tClass, String name, String value) {
}
