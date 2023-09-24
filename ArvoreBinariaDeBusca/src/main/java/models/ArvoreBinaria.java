package models;

import exceptions.NoExistenteException;
import exceptions.NoInexistenteException;
import interfaces.IArvoreBinaria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Classe que representa uma árvore binária.
 *
 * @param <T> Tipo dos elementos armazenados na árvore, que devem ser comparáveis.
 */
public class ArvoreBinaria<T extends Comparable<T>> implements IArvoreBinaria<T> {

    private No<T> raiz;

    /**
     * Cria uma nova árvore binária vazia.
     */
    public ArvoreBinaria() {
        this.raiz = null;
    }

    /**
     * Insere um nó na árvore.
     *
     * @param no Nó a ser inserido.
     * @throws NoExistenteException Se o nó com o mesmo valor já existe na árvore.
     */
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

    /**
     * Remove um nó da árvore.
     *
     * @param alvo Nó a ser removido.
     * @return O nó removido.
     * @throws NoInexistenteException Se o nó alvo não existe na árvore.
     */
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

    /**
     * Busca um nó na árvore.
     *
     * @param no Nó a ser buscado.
     * @return O nó encontrado.
     * @throws NoInexistenteException Se o nó alvo não existe na árvore.
     */
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

    /**
     * Verifica se a árvore está vazia.
     *
     * @return true se a árvore estiver vazia, caso contrário, false.
     */
    @Override
    public boolean estaVazia() {
        return raiz == null;
    }

    /**
     * Verifica se a árvore é completa.
     *
     * @return true se a árvore for completa, caso contrário, false.
     */
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

    /**
     * Calcula a altura da árvore.
     *
     * @return A altura da árvore.
     */
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

    /**
     * Imprime os elementos da árvore em ordem.
     */
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

    /**
     * Realiza a travessia em pré-ordem da árvore, imprimindo os elementos.
     *
     * @param no Nó a partir do qual a travessia começa.
     */
    @Override
    public void preOrdem(No<T> no) {
        if (no == null) return;
        System.out.println(no.getValor());
        preOrdem((No<T>) no.getFilhoEsq());
        preOrdem((No<T>) no.getFilhoDir());
    }

    /**
     * Realiza a travessia em ordem da árvore, imprimindo os elementos.
     *
     * @param no Nó a partir do qual a travessia começa.
     */
    @Override
    public void inOrdem(No<T> no) {
        if (no == null) return;
        inOrdem((No<T>) no.getFilhoEsq());
        System.out.println(no.getValor());
        inOrdem((No<T>) no.getFilhoDir());
    }

    /**
     * Realiza a travessia em pós-ordem da árvore, imprimindo os elementos.
     *
     * @param no Nó a partir do qual a travessia começa.
     */
    @Override
    public void posOrdem(No<T> no) {
        if (no == null) return;
        posOrdem((No<T>) no.getFilhoEsq());
        posOrdem((No<T>) no.getFilhoDir());
        System.out.println(no.getValor());
    }

    /**
     * Obtém a raiz da árvore.
     *
     * @return A raiz da árvore.
     */
    @Override
    public No<T> getRaiz() {
        return raiz;
    }
}