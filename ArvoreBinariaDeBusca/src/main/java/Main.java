import exceptions.NoInexistenteException;
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

    System.out.println("\n Pos ordem:");
    arvore.posOrdem(arvore.getRaiz());
    System.out.println("\n In ordem:");
    arvore.inOrdem(arvore.getRaiz());
    System.out.println("\n Pre ordem:");
    arvore.preOrdem(arvore.getRaiz());

    try {
      arvore.remover(novoNo);
      System.out.println("\n\nRemovido o nó com valor: " + novoNo.getValor());

      System.out.println("\nÁrvore em ordem após a remoção:");
      arvore.imprimirArvore();
    } catch (NoInexistenteException e) {
      System.out.println("\nErro ao remover o nó: " + e.getMessage());
    }

    boolean ehCompleta = arvore.ehCompleta();
    System.out.println("A árvore é completa? " + ehCompleta);

    // Removendo um nó para tornar a árvore incompleta

    try {
      arvore.remover(novoN);
      System.out.println("Nó " + novoN.getValor() + " removido.");
    } catch (NoInexistenteException e) {
      System.out.println("Erro ao remover o nó: " + e.getMessage());
    }
    arvore.remover(novoNoo);
    // Verificando novamente se a árvore é completa após a remoção
    ehCompleta = arvore.ehCompleta();
    System.out.println("A árvore é completa após a remoção? " + ehCompleta);


  }
}