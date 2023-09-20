package interfaces;
import exceptions.NoInexistenteException;
import models.No;

public interface IArvore<T extends Comparable<T>> {

  void inserir(No<T> no) throws Exception;
  No<T> remover(No<T> no) throws NoInexistenteException;
  No<T> buscar(No<T> no) throws NoInexistenteException;

  No<T> visitar(No<T> no) throws NoInexistenteException;
  
  boolean estaVazia();
  boolean ehCompleta();
  int altura();
  
  void imprimirArvore();
}