
package seminterface;

import java.io.Serializable;

public class Funcionario implements Serializable{
    int id;
    String nome;
    public Funcionario(){
    }

    public Funcionario(int id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }
    
}
