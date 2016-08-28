package br.jus.trerj.muraleletronico.modelo;

import java.util.Date;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class Advogado {
    private Long id;
    private String nome;
    private String numeroOAB;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroOAB() {
        return numeroOAB;
    }

    public void setNumeroOAB(String numeroOAB) {
        this.numeroOAB = numeroOAB;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Advogado)) {
            return  false;
        }
        Advogado compare = (Advogado) object;
        if (this.id == null || compare.id == null) {
            return false;
        }
        return this.id.equals(compare.id);
    }

    @Override
    public int hashCode() {
        int result = ("" + id).hashCode();
        result = 31 * result + ("" + nome).hashCode();
        result = 31 * result + ("" + numeroOAB).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getNome() + " / OAB: " + this.getNumeroOAB();
    }
}
