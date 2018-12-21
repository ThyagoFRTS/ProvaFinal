package seminterface;

import java.io.Serializable;

public class Produto implements Serializable{
    double preco;
    String nome;
    int quantidade;
    int id;

    public Produto(double preco, String nome, int quantidade, int id) {
        super();
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
        this.id = id;
    }
    public Produto(){
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setPreco(double preco){
        this.preco=preco;
    }
    public void setQuantidade(int quantidade){
        this.quantidade=quantidade;
    }

    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco;
    }
    public int getQuantidade(){
        return quantidade;
    }

}
