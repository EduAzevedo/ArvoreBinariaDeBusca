import models.ArvoreBinaria;
import models.No;

class Main {
  public static void main(String[] args) throws Exception {
    ArvoreBinaria arvore = new ArvoreBinaria();
    No novoNo = new No();
    No novoNoo = new No();
    No novoN = new No();

    novoNo.setValor(2);
    novoNoo.setValor(5);
    novoN.setValor(4);

    arvore.inserir(novoNo);
    arvore.inserir(novoNoo);
    arvore.inserir(novoN);

    //System.out.println(arvore.estaVazia());
   // System.out.println(arvore.altura());
    System.out.println("\n Pos ordem:");
    arvore.posOrdem(arvore.getRaiz());
    System.out.println("\n In ordem:");
    arvore.inOrdem(arvore.getRaiz());
    System.out.println("\n Pre ordem:");
    arvore.preOrdem(arvore.getRaiz());
  }
}