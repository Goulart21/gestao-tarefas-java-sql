package br.com.tarefas.model;

public class Tarefas {

    private String descricao;
    private boolean concluida;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Tarefas(){
        this.descricao = descricao;
        this.concluida = concluida;
    }
}
