package com.first.authetication.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Travel implements Comparable<Travel> {

    private String origem;
    private String destino;
    private String data;
    private String hora;
    private String valor;
    private String descricao;
    private Date date = new Date();

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private boolean visibility;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formato.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Travel o) {
        return this.getDate().compareTo(o.getDate());
    }
}
