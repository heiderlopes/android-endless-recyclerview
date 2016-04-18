package br.com.heiderlopes.endlessrecyclerview.model;

/**
 * Created by heiderlopes on 18/04/16.
 */
public class Carro {

    private static final long serialVersionUID = 1L;
    private String modelo;
    private String fabricante;

    public Carro() {

    }

    public Carro(String modelo, String fabricante) {
        this.modelo = modelo;
        this.fabricante = fabricante;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
