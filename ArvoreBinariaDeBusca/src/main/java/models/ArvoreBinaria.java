package models;

import exceptions.NoInexistenteException;
import interfaces.IArvoreBinaria;

public class ArvoreBinaria<T extends Comparable<T>> implements IArvoreBinaria<T> {

    private No<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    @Override
    public void inserir(No<T> no) throws Exception {

    }

    @Override
    public No<T> remover(No<T> no) throws NoInexistenteException {
        return null;
    }

    @Override
    public No buscar(No no) throws NoInexistenteException {
        return null;
    }

    @Override
    public No visitar(No no) throws NoInexistenteException {
        return null;
    }

    @Override
    public boolean estaVazia() {
        return false;
    }

    @Override
    public boolean ehCompleta() {
        return false;
    }

    @Override
    public int altura() {
        return 0;
    }

    @Override
    public void imprimirArvore() {

    }

    @Override
    public void preOrdem(No<T> no) {

    }

    @Override
    public void inOrdem(No<T> no) {

    }

    @Override
    public void posOrdem(No<T> no) {

    }
}
