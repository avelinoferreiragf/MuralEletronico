package br.jus.trerj.muraleletronico.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.jus.trerj.muraleletronico.AndamentoActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.enumerations.TipoPublicacao;
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
        Button btnVerAndamentos;
        Button btnVerIntimacao;
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
            this.publicacaoViewHolder.btnVerAndamentos = (Button) v.findViewById(R.id.lista_ver_andamento);
            this.publicacaoViewHolder.btnVerIntimacao = (Button) v.findViewById(R.id.lista_ver_intimacao);
            v.setTag(this.publicacaoViewHolder);
        } else {
            this.publicacaoViewHolder = (PublicacaoViewHolder) v.getTag();
        }

        final Publicacao publicacao = this.publicacacoes.get(position);

        if (publicacao != null) {
            this.publicacaoViewHolder.descricaoPublicacao.setText(publicacao.toString());
            this.publicacaoViewHolder.origemPublicacao.setText(publicacao.getOrigem());
            this.publicacaoViewHolder.tipoPublicacao.setText(publicacao.getTipo().getDescricao());
            this.publicacaoViewHolder.dataPublicacao.setText(SDF_DATA_PUBLICACAO.format(publicacao.getDataPublicacao()));

            this.publicacaoViewHolder.btnVerAndamentos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), AndamentoActivity.class);
                    intent.putExtra("numeroProcesso", publicacao.getNumeroDoProcesso());
                    intent.putExtra("siglaClasseProcesso", publicacao.getSiglaClasseProcesso());
                    intent.putExtra("numeroProtocolo", publicacao.getNumeroProtocolo().toString());

                    getContext().startActivity(intent);
                }
            });
            if(publicacao.getTipo().equals(TipoPublicacao.DECISAO)) {
                this.publicacaoViewHolder.btnVerIntimacao.setVisibility(View.INVISIBLE);
            }
        }

        return v;
    }
}
