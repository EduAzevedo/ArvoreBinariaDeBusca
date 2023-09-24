package interfaces;

import models.No;

public interface IArvoreBinaria<T extends Comparable<T>> extends IArvore<T> {

  /**
   * Obtém a raiz da árvore.
   *
   * @return O nó raiz da árvore.
   */
  No<T> getRaiz();

  /**
   * Realiza um percurso pré-ordem na árvore, visitando o nó antes de seus filhos.
   *
   * @param no O nó a partir do qual o percurso deve ser iniciado.
   */
  void preOrdem(No<T> no);

  /**
   * Realiza um percurso em ordem na árvore, visitando o nó entre seus filhos esquerdo e direito.
   *
   * @param no O nó a partir do qual o percurso deve ser iniciado.
   */
  void inOrdem(No<T> no);

  /**
   * Realiza um percurso pós-ordem na árvore, visitando os filhos antes do nó.
   *
   * @param no O nó a partir do qual o percurso deve ser iniciado.
   */
  void posOrdem(No<T> no);
}