package br.jus.trerj.muraleletronico.filter;

import java.util.Date;
import java.util.List;

import br.jus.trerj.muraleletronico.modelo.Advogado;
import br.jus.trerj.muraleletronico.modelo.Municipio;

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
    private List<Advogado> advogadosDisponiveis;
    private Advogado advogado = null;
    private List<Municipio> municipiosDisponiveis;

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

    public List<Advogado> getAdvogadosDisponiveis() {
        return advogadosDisponiveis;
    }

    public void setAdvogadosDisponiveis(List<Advogado> advogadosDisponiveis) {
        this.advogadosDisponiveis = advogadosDisponiveis;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public List<Municipio> getMunicipiosDisponiveis() {
        return municipiosDisponiveis;
    }

    public void setMunicipiosDisponiveis(List<Municipio> municipiosDisponiveis) {
        this.municipiosDisponiveis = municipiosDisponiveis;
    }

    public Boolean hasAdvogados() {
        return this.advogadosDisponiveis != null && this.advogadosDisponiveis.size() > 0;
    }

    public Integer getQuantidadeAdvogadosDisponives() {
        if (!this.hasAdvogados()) {
            return 0;
        }
        return this.advogadosDisponiveis.size();
    }

    public Advogado getAdvogadoAtPosition(int pos) {
        if (pos == 0 || this.advogadosDisponiveis == null || this.advogadosDisponiveis.size() <= 1) {
            return null;
        }
        return this.advogadosDisponiveis.get(pos - 1);
    }
}
