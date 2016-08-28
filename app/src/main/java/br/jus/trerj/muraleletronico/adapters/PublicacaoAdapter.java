package br.jus.trerj.muraleletronico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.modelo.Publicacao;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public class PublicacaoAdapter extends ArrayAdapter<Publicacao> {
    private static final SimpleDateFormat SDF_DATA_PUBLICACAO = new SimpleDateFormat("dd/MM/yyyy");
    private List<Publicacao> publicacacoes;

    private PublicacaoViewHolder publicacaoViewHolder;

    private class PublicacaoViewHolder {
        TextView descricaoPublicacao;
        TextView origemPublicacao;
        TextView tipoPublicacao;
        TextView dataPublicacao;
    }

    public PublicacaoAdapter(Context context, int resource, List<Publicacao> publicacacoes) {
        super(context, resource, publicacacoes);
        this.publicacacoes = publicacacoes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(this.getContext().LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.lista_publicacoes_item, null);
            this.publicacaoViewHolder = new PublicacaoViewHolder();
            this.publicacaoViewHolder.descricaoPublicacao = (TextView)v.findViewById(R.id.lista_descricao_publicacao);
            this.publicacaoViewHolder.origemPublicacao = (TextView)v.findViewById(R.id.lista_origem_publicacao);
            this.publicacaoViewHolder.tipoPublicacao = (TextView)v.findViewById(R.id.lista_tipo_publicacao);
            this.publicacaoViewHolder.dataPublicacao = (TextView)v.findViewById(R.id.lista_data_publicacao);
            v.setTag(this.publicacaoViewHolder);
        } else {
            this.publicacaoViewHolder = (PublicacaoViewHolder) v.getTag();
        }

        Publicacao publicacao = this.publicacacoes.get(position);

        if (publicacao != null) {
            this.publicacaoViewHolder.descricaoPublicacao.setText(publicacao.toString());
            this.publicacaoViewHolder.origemPublicacao.setText(publicacao.getOrigem());
            this.publicacaoViewHolder.tipoPublicacao.setText(publicacao.getTipo());
            this.publicacaoViewHolder.dataPublicacao.setText(SDF_DATA_PUBLICACAO.format(publicacao.getDataPublicacao()));
        }

        return v;
    }
}
