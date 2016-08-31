package br.jus.trerj.muraleletronico.modelo;

import java.io.Serializable;
import java.util.Date;

import br.jus.trerj.muraleletronico.enumerations.TipoPublicacao;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class Publicacao implements Serializable, Comparable<Publicacao> {

    private static final long serialVersionUID = 1920366665528352850L;

    private String id;
    private String numeroProcesso;
    private String numeroProcessoCompleto;
    private String descricaoNumeroDoProcesso;
    private String siglaClasseProcesso;
    private Long numeroProtocolo;
    private String origem;
    private Date dataPublicacao;
    private Date dataAndamento;
    private Municipio municipio;
    private String siglaUnidadePublicadora;
    private Boolean isSJD;
    private TipoPublicacao tipoPublicacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroProcessoCompleto() {
        return numeroProcessoCompleto;
    }

    public void setNumeroProcessoCompleto(String numeroProcessoCompleto) {
        this.numeroProcessoCompleto = numeroProcessoCompleto;
    }

    public String getDescricaoNumeroDoProcesso() {
        return descricaoNumeroDoProcesso;
    }

    public void setDescricaoNumeroDoProcesso(String descricaoNumeroDoProcesso) {
        this.descricaoNumeroDoProcesso = descricaoNumeroDoProcesso;
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

    public TipoPublicacao getTipoPublicacao() {
        return tipoPublicacao;
    }

    public void setTipoPublicacao(TipoPublicacao tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }

    @Override
    public String toString() {
        return this.getDescricaoNumeroDoProcesso();
    }

    @Override
    public int compareTo(Publicacao another) {
        if (another == null) {
            return 1;
        }
        return this.toString().compareTo(another.toString());
    }
}
