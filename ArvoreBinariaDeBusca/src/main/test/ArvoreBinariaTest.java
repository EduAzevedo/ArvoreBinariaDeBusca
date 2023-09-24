import exceptions.NoExistenteException;
import exceptions.NoInexistenteException;
import models.ArvoreBinaria;
import models.No;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArvoreBinariaTest {

    private ArvoreBinaria<Integer> arvore;

    @BeforeEach
    public void setUp() {
        arvore = new ArvoreBinaria<>();
    }

    @Test
    public void testInserir() throws NoExistenteException {
        arvore.inserir(new No<>(10));
        arvore.inserir(new No<>(5));
        arvore.inserir(new No<>(15));
        arvore.inserir(new No<>(3));

        assertEquals(10, (int) arvore.getRaiz().getValor());
        assertEquals(5, (int) arvore.getRaiz().getFilhoEsq().getValor());
        assertEquals(15, (int) arvore.getRaiz().getFilhoDir().getValor());
        assertEquals(3, (int) arvore.getRaiz().getFilhoEsq().getFilhoEsq().getValor());
    }

    @Test
    public void testRemover() throws NoInexistenteException, NoExistenteException {
        arvore.inserir(new No<>(10));
        arvore.inserir(new No<>(5));
        arvore.inserir(new No<>(15));
        arvore.inserir(new No<>(3));

        No<Integer> noParaRemover = new No<>(5);
        No<Integer> noRemovido = arvore.remover(noParaRemover);

        // Verifica se o nó retornado é o mesmo que tentamos remover
        assertEquals(noParaRemover, noRemovido);

        // Verifica a estrutura da árvore após a remoção
        assertEquals(10, (int) arvore.getRaiz().getValor());
        assertEquals(3, (int) arvore.getRaiz().getFilhoEsq().getValor());
        assertEquals(15, (int) arvore.getRaiz().getFilhoDir().getValor());
    }


    @Test
    public void testBuscar() throws NoInexistenteException, NoExistenteException {
        arvore.inserir(new No<>(10));
        arvore.inserir(new No<>(5));
        arvore.inserir(new No<>(15));

        No<Integer> noEncontrado = arvore.buscar(new No<>(5));

        assertNotNull(noEncontrado);
        assertEquals(5, (int) noEncontrado.getValor());
    }

    @Test
    public void testEhCompleta() throws Exception {
        assertTrue(arvore.ehCompleta());

        arvore.inserir(new No<>(10));
        arvore.inserir(new No<>(5));
        arvore.inserir(new No<>(15));

        assertTrue(arvore.ehCompleta());

        arvore.inserir(new No<>(3));
        arvore.inserir(new No<>(7));
        arvore.inserir(new No<>(17));

        assertFalse(arvore.ehCompleta());
    }

    @Test
    public void testAltura() throws Exception {
        assertEquals(-1, arvore.altura());

        arvore.inserir(new No<>(10));
        assertEquals(0, arvore.altura());

        arvore.inserir(new No<>(5));
        arvore.inserir(new No<>(15));
        assertEquals(1, arvore.altura());

        arvore.inserir(new No<>(3));
        arvore.inserir(new No<>(7));
        arvore.inserir(new No<>(12));
        arvore.inserir(new No<>(17));
        assertEquals(2, arvore.altura());
    }
}
