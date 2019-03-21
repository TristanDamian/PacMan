/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Graphique extends Application{
    //modèle ( le jeu qui s'execute)

    public final int SIZE_X =10;
    public final int SIZE_Y = 10;
    
 Jeu game;
 int column=0;
 int row=0;
 
 @Override
 public void start(Stage primaryStage){
   
    game= new Jeu();
 
    BorderPane border = new BorderPane();
    GridPane gPane = new GridPane();
 
    Image imPM = new Image("pacman.png"); // préparation des images
    Image imVide = new Image("Vide.png");
    Image imMur = new Image("mur.png");
    Image imGomme=new Image("gomme.png");
        
        
    
    ImageView[][] tab = new ImageView[SIZE_X][SIZE_Y];
   
   
        for (int i = 0; i < SIZE_X; i++) { // initialisation de la grille (sans image)
            for (int j = 0; j < SIZE_Y; j++) {
                ImageView img = new ImageView();
                
                tab[i][j] = img;
                
                gPane.add(img, i, j);
            }
            
        }
        
    
    
         
        Observer o =  new Observer() { // l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )
            @Override
            public void update(Observable o, Object arg) {
                for (int i = 0; i < SIZE_X; i++) { // rafraichissement graphique
                    for (int j = 0; j < SIZE_Y; j++) {
                        
                        try{
                         for(int k = 0; k<game.getGrille().getNbEntity(); k++){
                    //System.out.println(game.getGrille().getEntity(0).getX() + " " + game.getGrille().getEntity(0).getY());
                         //dans le cas de pacman k vaut 0
                         if (game.getGrille().getEntity(k).getX() == i && game.getGrille().getEntity(k).getY() == j) { // spm est à la position i, j => le dessiner
                          
                            tab[i][j].setImage(imPM);
                            
                        } else{
                            if(game.getGrille().getGrille()[i][j]&&game.getGrille().gomme[i][j]==0){
                            tab[i][j].setImage(imVide);
                            }
                            else if(game.getGrille().getGrille()[i][j]&&game.getGrille().gomme[i][j]==1){
                                tab[i][j].setImage(imGomme);
                            }
                            else{
                                 tab[i][j].setImage(imMur);
                            }
                        }
                         
                         }
                            
                           
                        }catch(IndexOutOfBoundsException exception){
                        System.out.println("c ca le probleme");
                        }
                       
                        
                    }
                }    
            }
        };
        
        game.addObserver(o);
        game.start();
        StackPane root = new StackPane();
        root.getChildren().add(gPane);
        gPane.setGridLinesVisible(true);

        
    Scene scene = new Scene(root,300,250);
    
          
    primaryStage.setTitle("PACMAN");
    primaryStage.setScene(scene);
    primaryStage.show();
    
  
         root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on écoute le clavier
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                Entity pacMan=game.getGrille().getEntity(0);
                switch(event.getCode()){
                    case UP:
                        pacMan.SetCurrentDir(Dir.haut);
                    break;
                    case DOWN:
                        pacMan.SetCurrentDir(Dir.bas);
                    break;
                    case RIGHT:
                        pacMan.SetCurrentDir(Dir.droite);
                    break;
                    case LEFT:
                        pacMan.SetCurrentDir(Dir.gauche);
                    break;
                    default:
                    break;
                }
            }
        });
    
    
    gPane.requestFocus();//?
 }
   
     public static void main(String[] args){
      launch(args);
    } 
   
   
}
