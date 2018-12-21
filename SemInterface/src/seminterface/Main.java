package seminterface;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int op1, codigo, quantidade, i, resposta = 0, codcliente, codproduto, codvenda, codfuncionario, id, indiceprod = 0;
        double preco, valortotal;
        String nome;

        File clientes = new File("Clientes.txt");
        File produtos = new File("Produtos.txt");
        File funcionarios = new File("Funcionarios.txt");
        File vendas = new File("Vendas.txt");

        Utilidades util = new Utilidades();
        Venda auxven = new Venda();
        Produto auxpro = new Produto();
        Cliente auxcli = new Cliente();
        Funcionario auxfunc = new Funcionario();
        Scanner input = new Scanner(System.in);
        List<Venda> listven = new ArrayList<>();
        List<Produto> listprod = new ArrayList<>();
        ArrayList<Cliente> listcli = new ArrayList<>();
        List<Funcionario> listfunc = new ArrayList<>();

        //LENDO O ARQUIVO DE CLIENTES
        try {
            FileReader rcli = new FileReader(clientes);
            BufferedReader rcli2 = new BufferedReader(rcli);
            String temporaria;
            while ((temporaria = rcli2.readLine()) != null) {
                int a = Integer.parseInt(temporaria);
                temporaria = rcli2.readLine();
                String nom = temporaria;
                listcli.add(new Cliente(a, nom));
            }
            rcli2.close();
            rcli.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LENDO O ARQUIVO DE FUNCIONARIOS
        try {
            FileReader rfunc = new FileReader(funcionarios);
            BufferedReader rfunc2 = new BufferedReader(rfunc);
            String temporaria;
            while ((temporaria = rfunc2.readLine()) != null) {
                int a = Integer.parseInt(temporaria);
                temporaria = rfunc2.readLine();
                String nom = temporaria;
                listfunc.add(new Funcionario(a, nom));
            }
            rfunc2.close();
            rfunc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LENDO O ARQUIVO DE PRODUTOS
        try {
            FileReader rprod = new FileReader(produtos);
            BufferedReader rprod2 = new BufferedReader(rprod);
            String temporaria;
            while ((temporaria = rprod2.readLine()) != null) {
                id = Integer.parseInt(temporaria);
                temporaria = rprod2.readLine();
                nome = temporaria;
                temporaria = rprod2.readLine();
                quantidade = Integer.parseInt(temporaria);
                temporaria = rprod2.readLine();
                preco = Double.parseDouble(temporaria.replace(',', '.'));
                listprod.add(new Produto(preco, nome, quantidade, id));
            }
            rprod2.close();
            rprod.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LENDO ARQUIVO VENDAS
        try {
            FileReader rvend = new FileReader(vendas);
            BufferedReader rvend2 = new BufferedReader(rvend);
            String temporaria;
            while ((temporaria = rvend2.readLine()) != null) {
                codvenda = Integer.parseInt(temporaria);
                temporaria = rvend2.readLine();
                codcliente = Integer.parseInt(temporaria);
                temporaria = rvend2.readLine();
                codfuncionario = Integer.parseInt(temporaria);
                temporaria = rvend2.readLine();
                codproduto = Integer.parseInt(temporaria);
                temporaria = rvend2.readLine();
                quantidade = Integer.parseInt(temporaria);
                temporaria = rvend2.readLine();
                valortotal = Double.parseDouble(temporaria.replace(',', '.'));
                listven.add(new Venda(codcliente, codfuncionario, codproduto, quantidade, valortotal, codvenda));
            }
            rvend2.close();
            rvend.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        do {
            System.out.println("\t\t\t\t\t\t1-Cadastrar Cliente\n\t\t\t\t\t\t2-Alterar Cliente\n\t\t\t\t\t\t3-Excluir Cliente\n\t\t\t\t\t\t4-Pesquisar Cliente");
            System.out.println("\t\t\t\t\t||------------------------------------||");
            System.out.println("\t\t\t\t\t\t5-Cadastrar Funcionario\n\t\t\t\t\t\t6-Alterar Funcionario\n\t\t\t\t\t\t7-Excluir Funcionario\n\t\t\t\t\t\t8-Pesquisar Funcionario");
            System.out.println("\t\t\t\t\t||------------------------------------||");
            System.out.println("\t\t\t\t\t\t9-Cadastrar Produto\n\t\t\t\t\t\t10-Alterar Produto\n\t\t\t\t\t\t11-Excluir Produto\n\t\t\t\t\t\t12-Pesquisar Produto");
            System.out.println("\t\t\t\t\t||------------------------------------||");
            System.out.println("\t\t\t\t\t\t13-Vender\n\t\t\t\t\t\t14-Histórico de venda\n\t\t\t\t\t\t15-Ver Clientes\n\t\t\t\t\t\t16-Ver Funcionários\n\t\t\t\t\t\t17-Ver Produtos");
            System.out.println("\t\t\t\t\t||------------------------------------||");
            System.out.println("\t\t\t\t\t\t-1-Sair");
            System.out.print("Select:");
            op1 = input.nextInt();
            switch (op1) {
                case 1:
                    //CADASTRO PRODUTO
                    input.nextLine();
                    System.out.print("Digitar o nome: ");
                    nome = input.nextLine();
                    if (listcli.isEmpty()) {
                        //PARA LISTA VAZIA
                        System.out.print("Digitar o codigo: ");
                        id = input.nextInt();
                        listcli.add(new Cliente(id, nome));
                        System.out.println("Adicionado");
                    } else {
                        //PARA LISTA NÃO VAZIA
                        do {
                            resposta = 0;
                            System.out.print("Digitar o codigo, digitar outro se indisponível: ");
                            codigo = input.nextInt();
                            //VERIFICAÇÃO DE CÓDIGOS IGUAIS
                            for (i = 0; i < listcli.size(); i++) {
                                if (listcli.get(i).id == codigo) {
                                    resposta = -1;
                                    System.out.println("Indisponível");
                                    break;
                                }
                            }

                        } while (resposta != 0);
                        id = codigo;
                        listcli.add(new Cliente(id, nome));
                        System.out.println("Adicionado");
                    }
                    resposta = 0;
                    break;
                case 2:
                    //ALTERAÇÃO CLIENTE
                    System.out.println("Digite o codigo do cliente para a alteração: ");
                    codigo = input.nextInt();
                    if (!listcli.isEmpty()) {
                        for (i = 0; i < listcli.size(); i++) {
                            if (codigo == listcli.get(i).id) {
                                resposta = i;
                                break;
                            }
                        }
                        if (codigo == listcli.get(resposta).id) {
                            System.out.println("Deseja mudar o nome?\n1-Sim\n2-Nao");
                            if (input.nextInt() == 1) {
                                input.nextLine();
                                System.out.println("Digite o novo nome");
                                listcli.get(resposta).nome = input.nextLine();
                                System.out.println("Alterado");
                            }
                        } else {
                            System.out.println("Não encontrado");
                        }
                    } else {
                        System.out.println("Lista Vazia");
                    }
                    break;
                case 3:
                    //EXCLUSÃO CLIENTE
                    System.out.println("Digite o codigo do cliente a ser excluido");
                    codigo = input.nextInt();
                    for (i = 0; i < listcli.size(); i++) {
                        if (listcli.get(i).id == codigo) {
                            resposta = 1;
                            auxcli = listcli.get(i);
                            break;
                        }
                    }
                    if (resposta == 1) {
                        if (!listcli.remove(auxcli)) {
                            System.out.println("Erro");
                        } else {
                            System.out.println("Removido");
                        }
                    } else {
                        System.out.println("Não encontrado");
                    }
                    resposta = 0;
                    break;
                case 4:
                    //PESQUISA CLIENTE
                    System.out.println("Digite o codigo do cliente a ser pesquisado");
                    codigo = input.nextInt();
                    for (i = 0; i < listcli.size(); i++) {
                        if (listcli.get(i).id == codigo) {
                            System.out.println("Cliente: " + listcli.get(i).nome + " Codigo: " + listcli.get(i).id);
                            resposta = -1;
                            break;
                        }
                    }
                    if (resposta == 0) {
                        System.out.println("Não encontrado");
                    }
                    resposta = 0;
                    break;
                case 5:
                    //CADASTRO FUNCIONARIO
                    input.nextLine();
                    System.out.print("Digitar o nome ");
                    nome = input.nextLine();
                    if (listfunc.isEmpty()) {
                        //PARA LISTA VAZIA
                        System.out.print("Digitar o codigo");
                        id = input.nextInt();
                        listfunc.add(new Funcionario(id, nome));
                        System.out.println("Adicionado");
                    } else {
                        //PARA LISTA NÃO VAZIA
                        do {
                            resposta = 0;
                            System.out.print("Digitar o codigo, digitar outro se indisponível: ");
                            codigo = input.nextInt();
                            //VERIFICAÇÃO DE CÓDIGOS IGUAIS
                            for (i = 0; i < listfunc.size(); i++) {
                                if (listfunc.get(i).id == codigo) {
                                    resposta = -1;
                                    System.out.println("Indisponível");
                                    break;
                                }
                            }
                        } while (resposta != 0);
                        id = codigo;
                        listfunc.add(new Funcionario(id, nome));
                        System.out.println("Adicionado");
                    }
                    resposta = 0;
                    break;
                case 6:
                    //ALTERAÇÃO FUNCIONÁRIO
                    System.out.println("Digite o codigo do funcionario: ");
                    codigo = input.nextInt();
                    if (!listfunc.isEmpty()) {
                        for (i = 0; i < listfunc.size(); i++) {
                            if (codigo == listfunc.get(i).id) {
                                resposta = i;
                                break;
                            }
                        }
                        if (codigo == listfunc.get(resposta).id) {
                            System.out.println("Deseja mudar o nome?\n1-Sim\n2-Nao");
                            if (input.nextInt() == 1) {
                                input.nextLine();
                                System.out.println("Digite o novo nome");
                                listfunc.get(resposta).nome = input.nextLine();
                                System.out.println("Alterado");
                            }
                        } else {
                            System.out.println("Não encontrado");
                        }
                    } else {
                        System.out.println("Lista Vazia");
                    }
                    break;
                case 7:
                    //EXLUSÃO FUNCIONÁRIO
                    System.out.println("Digite o codigo do funcionario a ser excluido");
                    codigo = input.nextInt();
                    for (i = 0; i < listfunc.size(); i++) {
                        if (listfunc.get(i).id == codigo) {
                            resposta = 1;
                            auxfunc = listfunc.get(i);
                            break;
                        }
                    }
                    if (resposta == 1) {
                        if (!listfunc.remove(auxfunc)) {
                            System.out.println("Erro");
                        } else {
                            System.out.println("Removido");
                        }
                    } else {
                        System.out.println("Não encontrado");
                    }
                    resposta = 0;
                    break;
                case 8:
                    //PESQUISA FUNCIONÁRIO
                    System.out.println("Digite o codigo do funcionario a ser pesquisado");
                    codigo = input.nextInt();
                    for (i = 0; i < listfunc.size(); i++) {
                        if (listfunc.get(i).id == codigo) {
                            System.out.println("Cliente: " + listfunc.get(i).nome + " Codigo: " + listfunc.get(i).id);
                            resposta = -1;
                            break;
                        }
                    }
                    if (resposta == 0) {
                        System.out.println("Não encontrado");
                    }
                    resposta = 0;
                    break;
                case 9:
                    //CADASTRA PRODUTO
                    input.nextLine();
                    System.out.print("Digitar o nome do produto: ");
                    nome = input.nextLine();
                    //PARA LISTA VAZIA
                    if (listprod.isEmpty()) {
                        System.out.print("Digitar o codigo: ");
                        id = input.nextInt();
                        System.out.print("Digitar a quantidade: ");
                        quantidade = input.nextInt();
                        System.out.print("Digitar o preco: ");
                        preco = input.nextDouble();
                        listprod.add(new Produto(preco, nome, quantidade, id));
                        System.out.println("Adicionado");
                    } else {
                        //PARA LISTA NÃO VAZIA
                        do {
                            resposta = 0;
                            System.out.print("Digitar o codigo, digitar outro se indisponível: ");
                            codigo = input.nextInt();
                            //VERIFICAÇÃO DE CÓDIGOS IGUAIS
                            for (i = 0; i < listprod.size(); i++) {
                                if (listprod.get(i).id == codigo) {
                                    resposta = -1;
                                    System.out.println("Indisponível");
                                    break;
                                }
                            }

                        } while (resposta != 0);
                        id = codigo;
                        System.out.print("Digitar a quantidade");
                        quantidade = input.nextInt();
                        System.out.print("Digitar o preco");
                        preco = input.nextDouble();
                        listprod.add(new Produto(preco, nome, quantidade, id));
                        System.out.println("Adicionado");
                    }
                    resposta = 0;
                    break;
                case 10:
                    //ALTERA PRODUTO
                    System.out.println("Digite o codigo do produto para a alteração: ");
                    codigo = input.nextInt();
                    if (!listprod.isEmpty()) {
                        for (i = 0; i < listprod.size(); i++) {
                            if (codigo == listprod.get(i).id) {
                                resposta = i;
                                break;
                            }
                        }
                        if (codigo == listprod.get(resposta).id) {
                            System.out.println("Deseja mudar o nome?\n1-Sim\n2-Nao");
                            if (input.nextInt() == 1) {
                                input.nextLine();
                                System.out.println("Digite o novo nome");
                                listprod.get(resposta).nome = input.nextLine();
                                System.out.println("Alterado");
                            }
                            System.out.println("Deseja mudar o preco?\n1-Sim\n2-Nao");
                            if (input.nextInt() == 1) {
                                System.out.println("Digite o novo preco");
                                listprod.get(resposta).preco = input.nextDouble();
                                System.out.println("Alterado");
                            }
                            System.out.println("Deseja mudar a quantidade?\n1-Sim\n2-Nao");
                            if (input.nextInt() == 1) {
                                System.out.println("Digite a nova quantidade");
                                listprod.get(resposta).quantidade = input.nextInt();
                                System.out.println("Alterado");
                            }
                        } else {
                            System.out.println("Não encontrado");
                        }
                    } else {
                        System.out.println("Lista Vazia");
                    }
                    break;
                case 11:
                    //EXCLUSÃO PRODUTO
                    System.out.println("Digite o codigo do produto a ser excluido: ");
                    codigo = input.nextInt();
                    for (i = 0; i < listprod.size(); i++) {
                        if (listprod.get(i).id == codigo) {
                            resposta = 1;
                            auxpro = listprod.get(i);
                            break;
                        }
                    }
                    if (resposta == 1) {
                        if (!listprod.remove(auxpro)) {
                            System.out.println("Erro");
                        } else {
                            System.out.println("Removido");
                        }
                    } else {
                        System.out.println("Não encontrado");
                    }
                    resposta = 0;

                    break;
                case 12:
                    //PESQUISA PRODUTO
                    System.out.println("Digite o codigo do produto a ser pesquisado");
                    codigo = input.nextInt();
                    for (i = 0; i < listprod.size(); i++) {
                        if (listprod.get(i).id == codigo) {
                            System.out.println("Produto: " + listprod.get(i).nome + " Codigo: " + listprod.get(i).id + " Quantidade: " + listprod.get(i).quantidade + " Preço: " + listprod.get(i).preco);
                            resposta = -1;
                            break;
                        }
                    }
                    if (resposta == 0) {
                        System.out.println("Não encontrado");
                    }
                    resposta = 0;
                    break;
                case 13:
                    //REALIZA VENDA
                    resposta = 0;
                    if (listven.isEmpty()) {
                        //PARA LISTA VAZIA
                        System.out.print("Digite o codigo da venda: ");
                        codvenda = input.nextInt();
                        do {
                            System.out.print("Digite o codigo do funcionario, digitar outro caso não encontrado: ");
                            codfuncionario = input.nextInt();
                            //VERIFICAÇÃO DE EXISTÊNCIA CÓDIGOS
                            for (i = 0; i < listfunc.size(); i++) {
                                if (listfunc.get(i).id == codfuncionario) {
                                    resposta = -1;
                                    break;
                                }
                            }
                        } while (resposta == 0);
                        resposta = 0;
                        do {
                            System.out.print("Digite o codigo do cliente, digitar outro caso não encontrado: ");
                            codcliente = input.nextInt();
                            //VERIFICAÇÃO DE EXISTÊNCIA CÓDIGOS
                            for (i = 0; i < listcli.size(); i++) {
                                if (listcli.get(i).id == codcliente) {
                                    resposta = -1;
                                    break;
                                }
                            }
                        } while (resposta == 0);
                        resposta = 0;
                        do {
                            System.out.print("Digite o codigo do produto, digitar outro caso não encontrado: ");
                            codproduto = input.nextInt();
                            //VERIFICAÇÃO DE EXISTÊNCIA CÓDIGOS
                            for (i = 0; i < listprod.size(); i++) {
                                if (listprod.get(i).id == codproduto) {
                                    resposta = -1;
                                    indiceprod = i;
                                    break;
                                }
                            }
                        } while (resposta == 0);
                        resposta = 0;
                        System.out.print("Digite a quantidade que deseja comprar: ");
                        quantidade = input.nextInt();
                        if (listprod.get(indiceprod).quantidade - quantidade < 0) {
                            System.out.println("Não é possível vender essa quantidade");
                        } else {
                            listprod.get(indiceprod).quantidade = listprod.get(indiceprod).quantidade - quantidade;
                        }
                        valortotal = quantidade * listprod.get(indiceprod).preco;
                        listven.add(new Venda(codcliente, codfuncionario, codproduto, quantidade, valortotal, codvenda));
                        System.out.println("Venda realizada");
                    } else {
                        //PARA LISTA NÃO VAZIA
                        do {
                            resposta = 0;
                            System.out.print("Digite o codigo da venda, digitar outro se indisponível: ");
                            codvenda = input.nextInt();
                            //VERIFICAÇÃO DE CÓDIGOS IGUAIS
                            for (i = 0; i < listven.size(); i++) {
                                if (listven.get(i).codvend == codvenda) {
                                    resposta = -1;
                                    System.out.println("Indisponível");
                                    break;
                                }
                            }
                        } while (resposta != 0);
                        do {
                            System.out.print("Digite o codigo do funcionario, digitar outro caso não encontrado: ");
                            codfuncionario = input.nextInt();
                            //VERIFICAÇÃO DE EXISTÊNCIA CÓDIGOS
                            for (i = 0; i < listfunc.size(); i++) {
                                if (listfunc.get(i).id == codfuncionario) {
                                    resposta = -1;
                                    break;
                                }
                            }
                        } while (resposta == 0);
                        resposta = 0;
                        do {
                            System.out.print("Digite o codigo do cliente, digitar outro caso não encontrado: ");
                            codcliente = input.nextInt();
                            //VERIFICAÇÃO DE EXISTÊNCIA CÓDIGOS
                            for (i = 0; i < listcli.size(); i++) {
                                if (listcli.get(i).id == codcliente) {
                                    resposta = -1;
                                    break;
                                }
                            }
                        } while (resposta == 0);
                        resposta = 0;
                        do {
                            System.out.print("Digite o codigo do produto, digitar outro caso não encontrado: ");
                            codproduto = input.nextInt();
                            //VERIFICAÇÃO DE EXISTÊNCIA CÓDIGOS
                            for (i = 0; i < listprod.size(); i++) {
                                if (listprod.get(i).id == codproduto) {
                                    resposta = -1;
                                    indiceprod = i;
                                    break;
                                }
                            }
                        } while (resposta == 0);
                        resposta = 0;
                        System.out.print("Digite a quantidade que deseja comprar: ");
                        quantidade = input.nextInt();
                        if (listprod.get(indiceprod).quantidade - quantidade < 0) {
                            System.out.println("Não é possível vender essa quantidade");
                        } else {
                            listprod.get(indiceprod).quantidade = listprod.get(indiceprod).quantidade - quantidade;
                        }
                        valortotal = quantidade * listprod.get(indiceprod).preco;
                        listven.add(new Venda(codcliente, codfuncionario, codproduto, quantidade, valortotal, codvenda));
                        System.out.println("Venda realizada");
                    }
                    break;
                case 14:
                    //HISTÓRICO VENDAS

                    for (i = 0; i < listven.size(); i++) {
                        System.out.println("ID Venda " + listven.get(i).codvend + " ID Cliente: " + listven.get(i).codcli + " ID Funcionario: " + listven.get(i).codfunc + " ID Produto: " + listven.get(i).codprod + " Quantidade: " + listven.get(i).quantidade + " Valor Total: " + listven.get(i).valor);
                    }
                    break;
                case 15:
                    //VER CLIENTES
                    for (i = 0; i < listcli.size(); i++) {
                        System.out.println("Nome:" + listcli.get(i).nome + " de ID " + listcli.get(i).id);
                    }
                    break;
                case 16:
                    //VER FUNCIONÁRIOS
                    for (i = 0; i < listfunc.size(); i++) {
                        System.out.println("Nome:" + listfunc.get(i).nome + " de ID " + listfunc.get(i).id);
                    }
                    break;
                case 17:
                    //VER PRODUTOS
                    for (i = 0; i < listprod.size(); i++) {
                        System.out.println("Nome:" + listprod.get(i).nome + " Preço:" + listprod.get(i).preco + " Quantidade:" + listprod.get(i).quantidade + " ID:" + listprod.get(i).id);

                    }
                    break;
            }
        } while (op1 != -1);

        //SALVAR CLIENTES
        try {
            FileWriter wcli = new FileWriter(clientes);
            PrintWriter wcli2 = new PrintWriter(wcli);
            for (i = 0; i < listcli.size(); i++) {
                if (i == ((listcli.size()) - 1)) {
                    wcli2.flush();
                    wcli2.printf("%d%n%s", listcli.get(i).id, listcli.get(i).nome);
                } else {
                    wcli2.flush();
                    wcli2.printf("%d%n%s%n", listcli.get(i).id, listcli.get(i).nome);
                }
            }
            wcli.close();
            wcli2.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //SALVAR FUNCIONÁRIOS
        try {
            FileWriter wfunc = new FileWriter(funcionarios);
            PrintWriter wfunc2 = new PrintWriter(wfunc);
            for (i = 0; i < listfunc.size(); i++) {
                if (i == ((listfunc.size()) - 1)) {
                    wfunc2.flush();
                    wfunc2.printf("%d%n%s", listfunc.get(i).id, listfunc.get(i).nome);
                } else {
                    wfunc2.flush();
                    wfunc2.printf("%d%n%s%n", listfunc.get(i).id, listfunc.get(i).nome);
                }
            }
            wfunc.close();
            wfunc2.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //SALVAR PRODUTOS
        try {
            FileWriter wprod = new FileWriter(produtos);
            PrintWriter wprod2 = new PrintWriter(wprod);
            for (i = 0; i < listprod.size(); i++) {
                if (i == ((listprod.size()) - 1)) {
                    wprod2.flush();
                    wprod2.printf("%d%n%s%n%d%n%.2f", listprod.get(i).id, listprod.get(i).nome, listprod.get(i).quantidade, listprod.get(i).preco);
                } else {
                    wprod2.flush();
                    wprod2.printf("%d%n%s%n%d%n%.2f%n", listprod.get(i).id, listprod.get(i).nome, listprod.get(i).quantidade, listprod.get(i).preco);
                }
            }
            wprod.close();
            wprod2.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //SALVAR VENDAS
        try {
            FileWriter wvend = new FileWriter(vendas);
            PrintWriter wvend2 = new PrintWriter(wvend);
            for (i = 0; i < listven.size(); i++) {
                if (i == ((listven.size()) - 1)) {
                    //wcli.flush();
                    wvend2.flush();
                    wvend2.printf("%d%n%d%n%d%n%d%n%d%n%.2f", listven.get(i).codvend, listven.get(i).codcli, listven.get(i).codfunc, listven.get(i).codprod, listven.get(i).quantidade, listven.get(i).valor);
                } else {
                    //wcli.flush();
                    wvend2.flush();
                    wvend2.printf("%d%n%d%n%d%n%d%n%d%n%.2f%n", listven.get(i).codvend, listven.get(i).codcli, listven.get(i).codfunc, listven.get(i).codprod, listven.get(i).quantidade, listven.get(i).valor);
                }
            }
            wvend.close();
            wvend2.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        listven.clear();
        listfunc.clear();
        listprod.clear();
        listcli.clear();
    }

}
