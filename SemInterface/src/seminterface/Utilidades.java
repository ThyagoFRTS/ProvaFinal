/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminterface;

import java.io.IOException;


public class Utilidades {
    
    public void pseudoLimpaTela(){
    for(int i=0;i<10;i++){
        System.out.println("\n\n\n");
    }
    }
    public void limpaTela(){
            try
        {
            Runtime.getRuntime().exec("cls");
        }
        catch(IOException e)
        {
            System.out.println("Erro");
        }
    }
    
    
    
}
