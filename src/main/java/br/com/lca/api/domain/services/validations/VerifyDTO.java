package br.com.lca.api.domain.services.validations;


public interface VerifyDTO<T> {

   void verify(T t);
}
