package br.jus.trerj.muraleletronico.filter;

import java.util.Date;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class PublicacaoFiltro {
    private static final PublicacaoFiltro INSTANCE = new PublicacaoFiltro();

    public static PublicacaoFiltro getInstance() {
        return INSTANCE;
    }

    private String numeroProcesso = "";
    private String numeroProtocolo = "";
    private Date dataPublicacao = new Date();
    private Boolean isSJD = false;

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public Boolean getSJD() {
        return isSJD;
    }

    public void setSJD(Boolean SJD) {
        isSJD = SJD;
    }
}
