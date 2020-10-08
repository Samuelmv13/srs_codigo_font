package com.src.builder;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public abstract class ConstrutorDeEntidade<E> {

    private CustomizacaoEntidade<E> customizacao;

    public E construir() throws ParseException {
        final E entidade = construirEntidade();
        if (isCustomizado()) {
            customizacao.executar(entidade);
        }
        return persistir(entidade);
    }


    public ConstrutorDeEntidade<E> customizar(CustomizacaoEntidade<E> customizacao) {
        this.customizacao = customizacao;
        return this;
    }


    protected abstract E construirEntidade() throws ParseException;


    protected abstract E persistir(E entidade);


    protected abstract Collection<E> obterTodos();


    protected abstract E obterPorId(Integer id);

    public boolean isCustomizado() {
        return this.customizacao != null;
    }

    public void setCustomizacao(CustomizacaoEntidade<E> customizacao) {
        this.customizacao = customizacao;
    }
}
