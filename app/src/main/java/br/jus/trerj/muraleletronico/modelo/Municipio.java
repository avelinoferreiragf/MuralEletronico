package br.jus.trerj.muraleletronico.modelo;

import java.io.Serializable;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class Municipio implements Serializable {

    private static final long serialVersionUID = -69648032175411668L;

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
