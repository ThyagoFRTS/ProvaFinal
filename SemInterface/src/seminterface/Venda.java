/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminterface;

import java.io.Serializable;

/**
 *
 * @author hazut
 */
public class Venda implements Serializable{
    int codcli,codfunc,codprod,quantidade,codvend;
    double valor;
    public Venda(){
    }

    public Venda(int codcli, int codfunc, int codprod, int quantidade, double valor,int codvend) {
        super();
        this.codcli = codcli;
        this.codfunc = codfunc;
        this.codprod = codprod;
        this.quantidade = quantidade;
        this.valor = valor;
        this.codvend = codvend;
    }
    
    
}
