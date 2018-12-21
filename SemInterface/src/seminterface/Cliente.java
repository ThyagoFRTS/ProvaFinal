
package seminterface;

import java.io.Serializable;


public class Cliente implements Serializable{
    int id;
    String nome;
    public Cliente(){
    
    }
    public Cliente(int id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }
    
}
