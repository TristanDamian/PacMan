/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;


public class TPMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Jeu j = new Jeu();
        Console c = new Console(j);
        new Thread(j).start();
    }
    
}
