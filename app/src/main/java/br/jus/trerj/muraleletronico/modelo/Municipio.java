package br.jus.trerj.muraleletronico.modelo;

import java.io.Serializable;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class Municipio implements Serializable, Comparable<Municipio> {

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

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Advogado)) {
            return  false;
        }
        Municipio compare = (Municipio) object;
        if (this.id == null || compare.id == null) {
            return false;
        }
        return this.id.equals(compare.id);
    }

    @Override
    public int hashCode() {
        int result = ("" + id).hashCode();
        result = 31 * result + ("" + nome).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    @Override
    public int compareTo(Municipio another) {
        if (another == null) {
            return 1;
        }
        return this.toString().compareTo(another.toString());
    }

}
