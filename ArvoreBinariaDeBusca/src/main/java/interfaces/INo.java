package interfaces;

public interface INo<T extends Comparable<T>> {

  void setValor(T valor);
  void setPai(INo<T> no);
  void setFilhoEsq(INo<T> no);
  void setFilhoDir(INo<T> no);

  T getValor();
  INo<T> getPai();
  INo<T> getFilhoEsq();
  INo<T> getFilhoDir();
}
