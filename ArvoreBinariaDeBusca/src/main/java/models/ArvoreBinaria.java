package models;

import exceptions.NoExistenteException;
import exceptions.NoInexistenteException;
import interfaces.IArvoreBinaria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArvoreBinaria<T extends Comparable<T>> implements IArvoreBinaria<T> {

    private No<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    @Override
    public void inserir(No<T> no) throws NoExistenteException {
        if (no == null) {
            throw new IllegalArgumentException("O nó não pode ser nulo.");
        }

        if (raiz == null) {
            raiz = new No<T>(no.getValor());
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
                throw new NoExistenteException("O nó com o mesmo valor já existe na árvore.");
            }
        }

        int comparacao = no.getValor().compareTo(noPai.getValor());

        if (comparacao < 0) {
            noPai.setFilhoEsq(new No<T>(no.getValor()));
        } else {
            noPai.setFilhoDir(new No<T>(no.getValor()));
        }
    }

    public No<T> remover(No<T> alvo) throws NoInexistenteException {
        if (raiz == null) {
            throw new NoInexistenteException("A árvore está vazia.");
        }

        No<T> pai = null;
        No<T> atual = raiz;

        while (atual != null && !atual.getValor().equals(alvo.getValor())) {
            pai = atual;
            int comparacao = alvo.getValor().compareTo(atual.getValor());
            if (comparacao < 0) {
                atual = (No<T>) atual.getFilhoEsq();
            } else {
                atual = (No<T>) atual.getFilhoDir();
            }
        }

        if (atual == null) {
            throw new NoInexistenteException("O nó alvo não existe na árvore.");
        }

        if (atual.getFilhoEsq() == null || atual.getFilhoDir() == null) {
            No<T> filho = (No<T>) ((atual.getFilhoEsq() != null) ? atual.getFilhoEsq() : atual.getFilhoDir());
            if (pai == null) {
                raiz = filho;
            } else if (pai.getFilhoEsq() == atual) {
                pai.setFilhoEsq(filho);
            } else {
                pai.setFilhoDir(filho);
            }
        } else {
            No<T> sucessor = encontrarSucessor((No<T>) atual.getFilhoDir());
            atual.setValor(sucessor.getValor());
            atual.setFilhoDir(remover(sucessor));
        }

        return alvo;
    }

    private No<T> encontrarSucessor(No<T> no) {
        while (no.getFilhoEsq() != null) {
            no = (No<T>) no.getFilhoEsq();
        }
        return no;
    }

    @Override
    public No<T> buscar(No<T> no) throws NoInexistenteException {
        if (raiz == null) {
            throw new NoInexistenteException("A árvore está vazia.");
        }

        Queue<No<T>> fila = new LinkedList<>();
        fila.offer(raiz);

        while (!fila.isEmpty()) {
            No<T> noTemp = fila.poll();

            if (noTemp.getValor().equals(no.getValor())) {
                return noTemp;
            }

            if (noTemp.getFilhoEsq() != null) {
                fila.offer((No<T>) noTemp.getFilhoEsq());
            }

            if (noTemp.getFilhoDir() != null) {
                fila.offer((No<T>) noTemp.getFilhoDir());
            }
        }

        throw new NoInexistenteException("O nó alvo não existe na árvore.");
    }

    @Override
    public boolean estaVazia() {
        return raiz == null;
    }

    @Override
    public boolean ehCompleta() {
        if (raiz == null) {
            return true;
        }

        Queue<No<T>> fila = new LinkedList<>();
        fila.offer(raiz);

        while (!fila.isEmpty()) {
            No<T> no = fila.poll();

            if (no.getFilhoEsq() != null) {
                fila.offer((No<T>) no.getFilhoEsq());
            } else if (no.getFilhoDir() != null) {
                return false;
            }

            if (no.getFilhoDir() != null) {
                fila.offer((No<T>) no.getFilhoDir());
            } else {
                while (!fila.isEmpty()) {
                    No<T> n = fila.poll();
                    if (n.getFilhoEsq() != null || n.getFilhoDir() != null) {
                        return false;
                    }
                }
            }
        }

        // Se chegarmos aqui, a árvore é completa
        return true;
    }

    @Override
    public int altura() {
        if (raiz == null) {
            return -1;
        }

        Queue<No<T>> fila = new LinkedList<>();
        fila.offer(raiz);
        int altura = -1;

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();

            for (int i = 0; i < tamanhoNivel; i++) {
                No<T> no = fila.poll();

                if (no != null) {
                    if (no.getFilhoEsq() != null) {
                        fila.offer((No<T>) no.getFilhoEsq());
                    }

                    if (no.getFilhoDir() != null) {
                        fila.offer((No<T>) no.getFilhoDir());
                    }
                }
            }

            altura++;
        }

        return altura;
    }

    @Override
    public void imprimirArvore() {
        if (raiz == null) {
            return;
        }

        Stack<No<T>> pilha = new Stack<>();
        No<T> atual = raiz;

        while (!pilha.isEmpty() || atual != null) {
            if (atual != null) {
                pilha.push(atual);
                atual = (No<T>) atual.getFilhoEsq();
            } else {
                atual = pilha.pop();
                System.out.print(atual.getValor() + " ");
                atual = (No<T>) atual.getFilhoDir();
            }
        }
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
