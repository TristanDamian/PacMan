/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import static java.lang.Thread.*;
import java.util.Observable;


public class Jeu extends Observable implements Runnable{
    private Grille grille;
    private final int WIDTH = 10;
    private final int LENGHT = 10;
    
    public Jeu(){
        boolean a=true;
        grille = new Grille(a);
        
    }
    
    public void start(){
    new Thread(this).start();
    }

    public Grille getGrille() {
        return grille;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getLENGHT() {
        return LENGHT;
    }
    
    
    
    @Override
    public void run(){
        try{
            getGrille().getNewE(2,2);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        while(true){
            System.out.println(getGrille().getNbEntity());
            Entity ent = getGrille().getEntity(0);
            setChanged();
            notifyObservers();
            try{
                Thread.sleep(750);
                if(ent.currentDir!=null){
                ent.depl(ent.currentDir);
                }
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());            
            }
        }
    }
}
