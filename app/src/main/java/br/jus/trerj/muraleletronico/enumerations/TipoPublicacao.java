package br.jus.trerj.muraleletronico.enumerations;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public enum TipoPublicacao {
    DECISAO("Despacho, Decisão ou Notificação"),
    INTIMACAO("Intimação");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private TipoPublicacao(String descricao) {
        this.descricao = descricao;
    }
}
