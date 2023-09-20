package models;

import interfaces.INo;

public class No<T extends Comparable<T>> implements INo<T> {
    private T valor;
    private INo<T> pai;
    private INo<T> filhoEsq;
    private INo<T> filhoDir;

    public No() {
        this.valor = null;
        this.pai = null;
        this.filhoEsq = null;
        this.filhoDir = null;
    }

    public No(T valor) {
        this.valor = valor;
        this.pai = null;
        this.filhoEsq = null;
        this.filhoDir = null;
    }

    @Override
    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public void setPai(INo<T> no) {
        this.pai = no;
    }

    @Override
    public void setFilhoEsq(INo<T> no) {
        this.filhoEsq = no;
    }

    @Override
    public void setFilhoDir(INo<T> no) {
        this.filhoDir = no;
    }

    @Override
    public T getValor() {
        return valor;
    }

    @Override
    public INo<T> getPai() {
        return pai;
    }

    @Override
    public INo<T> getFilhoEsq() {
        return filhoEsq;
    }

    @Override
    public INo<T> getFilhoDir() {
        return filhoDir;
    }
}
