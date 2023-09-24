package interfaces;

import exceptions.NoInexistenteException;
import models.No;

public interface IArvore<T extends Comparable<T>> {

  /**
   * Insere um nó na árvore.
   *
   * @param no Nó a ser inserido.
   * @throws Exception Se ocorrer um erro durante a inserção.
   */
  void inserir(No<T> no) throws Exception;

  /**
   * Remove um nó da árvore.
   *
   * @param no Nó a ser removido.
   * @return O nó removido.
   * @throws NoInexistenteException Se o nó alvo não existe na árvore.
   */
  No<T> remover(No<T> no) throws NoInexistenteException;

  /**
   * Busca um nó na árvore.
   *
   * @param no Nó a ser buscado.
   * @return O nó encontrado.
   * @throws NoInexistenteException Se o nó alvo não existe na árvore.
   */
  No<T> buscar(No<T> no) throws NoInexistenteException;

  /**
   * Verifica se a árvore está vazia.
   *
   * @return true se a árvore estiver vazia, caso contrário, false.
   */
  boolean estaVazia();

  /**
   * Verifica se a árvore é completa.
   *
   * @return true se a árvore for completa, caso contrário, false.
   * @throws NoInexistenteException Se o nó alvo não existe na árvore.
   */
  boolean ehCompleta() throws NoInexistenteException;

  /**
   * Calcula a altura da árvore.
   *
   * @return A altura da árvore.
   */
  int altura();

  /**
   * Imprime os elementos da árvore.
   */
  void imprimirArvore();
}