package br.jus.trerj.muraleletronico.modelo;

import java.io.Serializable;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class Advogado implements Serializable, Comparable<Advogado> {

    private static final long serialVersionUID = -5290522180419993968L;

    private Long idAdvogado;
    private String nomeAdvogado;
    private String numeroOAB;

    public Long getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Long id) {
        this.idAdvogado = id;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nome) {
        this.nomeAdvogado = nomeAdvogado;
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
        if (this.idAdvogado == null || compare.idAdvogado == null) {
            return false;
        }
        return this.idAdvogado.equals(compare.idAdvogado);
    }

    @Override
    public int hashCode() {
        int result = ("" + idAdvogado).hashCode();
        result = 31 * result + ("" + nomeAdvogado).hashCode();
        result = 31 * result + ("" + numeroOAB).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getNomeAdvogado() + " / OAB: " + this.getNumeroOAB();
    }

    @Override
    public int compareTo(Advogado another) {
        if (another == null) {
            return 1;
        }
        return this.toString().compareTo(another.toString());
    }
}
