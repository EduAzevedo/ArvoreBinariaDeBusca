package interfaces;

import models.No;

public interface IArvoreBinaria<T extends Comparable<T>> extends IArvore<T> {
  
  void preOrdem(No<T> no);
  void inOrdem(No<T> no);
  void posOrdem(No<T> no);
}