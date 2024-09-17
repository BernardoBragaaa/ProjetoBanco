package pkg;

import java.util.List;

public class Banco {
    public String nome;
    public List<Conta> contas;

    public Banco(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    
}
