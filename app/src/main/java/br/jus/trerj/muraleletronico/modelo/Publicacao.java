package br.jus.trerj.muraleletronico.modelo;

import java.io.Serializable;
import java.util.Date;

import br.jus.trerj.muraleletronico.enumerations.TipoPublicacao;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class Publicacao implements Serializable {

    private static final long serialVersionUID = 1920366665528352850L;

    private String id;
    private String numeroDoProcesso;
    private String numeroDoProcessoCompleto;
    private String descricaoNumeroProcesso;
    private String siglaClasseProcesso;
    private Long numeroProtocolo;
    private String origem;
    private Date dataPublicacao;
    private Date dataAndamento;
    private Municipio municipio;
    private String siglaUnidadePublicadora;
    private Boolean isSJD;
    private TipoPublicacao tipo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroDoProcesso() {
        return numeroDoProcesso;
    }

    public void setNumeroDoProcesso(String numeroDoProcesso) {
        this.numeroDoProcesso = numeroDoProcesso;
    }

    public String getNumeroDoProcessoCompleto() {
        return numeroDoProcessoCompleto;
    }

    public void setNumeroDoProcessoCompleto(String numeroDoProcessoCompleto) {
        this.numeroDoProcessoCompleto = numeroDoProcessoCompleto;
    }

    public String getDescricaoNumeroProcesso() {
        return descricaoNumeroProcesso;
    }

    public void setDescricaoNumeroProcesso(String descricaoNumeroProcesso) {
        this.descricaoNumeroProcesso = descricaoNumeroProcesso;
    }

    public String getSiglaClasseProcesso() {
        return siglaClasseProcesso;
    }

    public void setSiglaClasseProcesso(String siglaClasseProcesso) {
        this.siglaClasseProcesso = siglaClasseProcesso;
    }

    public Long getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(Long numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Date getDataAndamento() {
        return dataAndamento;
    }

    public void setDataAndamento(Date dataAndamento) {
        this.dataAndamento = dataAndamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getSiglaUnidadePublicadora() {
        return siglaUnidadePublicadora;
    }

    public void setSiglaUnidadePublicadora(String siglaUnidadePublicadora) {
        this.siglaUnidadePublicadora = siglaUnidadePublicadora;
    }

    public Boolean getSJD() {
        return isSJD;
    }

    public void setSJD(Boolean SJD) {
        isSJD = SJD;
    }

    public TipoPublicacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoPublicacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.getDescricaoNumeroProcesso();
    }
}
