package br.com.lca.api.domain.services.validations;

public record ValidateProperty<T> (Class<T> tClass, String name, String value) {
}
