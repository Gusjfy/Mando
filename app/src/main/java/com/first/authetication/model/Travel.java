package com.first.authetication.model;

public class Travel {

    private String origem;
    private String destino;
    private String data;
    private String hora;
    private boolean visibility;
    private int id;
    private String id_entregador;
    private String id_remetente = "";

    public String getId_entregador() {
        return id_entregador;
    }

    public void setId_entregador(String id_entregador) {
        this.id_entregador = id_entregador;
    }

    public String getId_remetente() {
        return id_remetente;
    }

    public void setId_remetente(String id_remetente) {
        this.id_remetente = id_remetente;
    }

    public Travel() {
    }

    public Travel(String origem, String destino, String data, String hora, boolean visibility) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.hora = hora;
        this.visibility = visibility;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
