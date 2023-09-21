package models;

import exceptions.NoInexistenteException;
import interfaces.IArvoreBinaria;

public class ArvoreBinaria<T extends Comparable<T>> implements IArvoreBinaria<T> {

    private No<T> raiz;
    private int tamanho = 0;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    @Override
    public void inserir(No<T> no) throws Exception {
        if (no == null) {
            throw new IllegalArgumentException("O nó não pode ser nulo.");
        }

        if (raiz == null) {
            raiz = new No<T>(no.getValor());
            this.tamanho++;
            return;
        }

        No<T> noAtual = raiz;
        No<T> noPai = null;

        while (noAtual != null) {
            noPai = noAtual;

            int comparacao = no.getValor().compareTo(noAtual.getValor());

            if (comparacao < 0) {
                noAtual = (No<T>) noAtual.getFilhoEsq();
            } else if (comparacao > 0) {
                noAtual = (No<T>) noAtual.getFilhoDir();
            } else {
                throw new Exception("O nó com o mesmo valor já existe na árvore.");
            }
        }

        int comparacao = no.getValor().compareTo(noPai.getValor());

        if (comparacao < 0) {
            noPai.setFilhoEsq(new No<T>(no.getValor()));
            this.tamanho++;
        } else {
            noPai.setFilhoDir(new No<T>(no.getValor()));
            this.tamanho++;
        }

    }

    @Override
    public No<T> remover(No<T> no) throws NoInexistenteException {
        return null;
    }

    @Override
    public No<T> buscar(No<T> no) throws NoInexistenteException {
        return null;
    }

    @Override
    public No<T> visitar(No<T> no) throws NoInexistenteException {
        return null;
    }

    @Override
    public boolean estaVazia() {
        return this.tamanho <= 0;
    }

    @Override
    public boolean ehCompleta() {
        return false;
    }

    @Override
    public int altura() {
        return this.tamanho;
    }

    @Override
    public void imprimirArvore() {


    }
    @Override
    public void preOrdem(No<T> no) {
        if (no == null) return;
        System.out.println(no.getValor());
        preOrdem((No<T>) no.getFilhoEsq());
        preOrdem((No<T>) no.getFilhoDir());

    }

    @Override
    public void inOrdem(No<T> no) {
        if (no == null) return;
        inOrdem((No<T>) no.getFilhoEsq());
        System.out.println(no.getValor());
        inOrdem((No<T>) no.getFilhoDir());

    }

    @Override
    public void posOrdem(No<T> no) {
        if (no == null) return;
        posOrdem((No<T>) no.getFilhoEsq());
        posOrdem((No<T>) no.getFilhoDir());
        System.out.println(no.getValor());
    }

    @Override
    public No<T> getRaiz() {
        return raiz;
    }
}
