/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.test.modello;

import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.*;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class TestModelloAnalizzaMondo extends TestCase{
    
    private Mondo mondo;
    private Set<Cellula> matrice = new HashSet<Cellula>();
    
    @Override
    public void setUp()   {
        mondo = new Mondo();
        matrice.add(new Cellula(4, 5));
        matrice.add(new Cellula(4, 6));
        matrice.add(new Cellula(4, 7));
        matrice.add(new Cellula(3, 7));
        matrice.add(new Cellula(2, 6));
         mondo.addCellule(matrice);
    }
    
    public void testGlinder()   {
        for(int i=0;i<4;i++)   {
            System.out.println("ANALIZZA MONDO Generazione "+i);
            mondo.analizzaMondo();
        }
        
        assertTrue(
                mondo.getGenerazione().contains(new Cellula(4+1, 5+1))&&
                mondo.getGenerazione().contains(new Cellula(4+1, 6+1))&&
                mondo.getGenerazione().contains(new Cellula(4+1, 7+1))&&
                mondo.getGenerazione().contains(new Cellula(3+1, 7+1))&&
                mondo.getGenerazione().contains(new Cellula(2+1, 6+1)));
    }
}
