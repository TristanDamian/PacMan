/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import java.util.ArrayList;


public class Grille {
    private Boolean grille[][];
    private final int WIDTH = 10;
    private final int LENGHT = 10;
    private ArrayList<Entity> list;
    private int nbEntity;
    public int gomme[][];
    
    
    public Grille(){
        list = new ArrayList();
        nbEntity = 0;
    }
    
    public Grille(boolean t){
        gomme=new int[LENGHT][WIDTH];
        grille=new Boolean[LENGHT][WIDTH];
        list = new ArrayList();
        nbEntity = 0;
        for(int i=0; i<=LENGHT-1;i++){
            for(int j=0; j<WIDTH;j++){
                this.grille[i][j] = true;
                this.gomme[i][j]=1;
                if(j==WIDTH-1){
                    this.grille[i][j] = false;
                    this.gomme[i][j]=0;
                }
                if(i==LENGHT-1){
                    this.grille[i][j] = false;
                    this.gomme[i][j]=0;
                }
            }
        }
    }
    
    public Grille(Boolean[][] b){
        grille = b;
        list = new ArrayList();
        nbEntity = 0;
    }
    
    public Grille(Grille g){
        grille = g.getGrille();
        for(int k = 0; k<g.getNbEntity();k++){
            System.out.println("nombre entité base "+g.getNbEntity());
            System.out.println("nombre entité base "+g.getNbEntity());
            list.add(g.getEntity(k));
        }
        nbEntity=g.getNbEntity();
    }
    

    public Boolean[][] getGrille() {
        return grille;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getLENGHT() {
        return LENGHT;
    }
    
    public Entity getEntity(int i){
        return list.get(i);
    }
    
    public int getNbEntity(){
        return nbEntity;
    }
    
    public void getNewE(int x,int y) throws Exception{
        if(grille[x][y]){
            list.add(new Entity(x,y,this));
            nbEntity++;
        }
        else{
            throw new Exception("la case est prise");
        }
    }
    
    
}
