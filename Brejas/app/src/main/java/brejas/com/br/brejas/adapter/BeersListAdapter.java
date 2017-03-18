package brejas.com.br.brejas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import brejas.com.br.brejas.R;
import brejas.com.br.brejas.model.Beer;

/**
 * Created by rnas on 18/03/17.
 */

public class BeersListAdapter extends RecyclerView.Adapter<BeersListAdapter.BeersViewHolder>  {

    private Context context;
    private List<Beer> beers;

    public BeersListAdapter(Context context, List<Beer> beers) {
        this.context    = context;
        this.beers      = beers;
    }

    @Override
    public BeersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.beers_row, parent, false);

        return new BeersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BeersViewHolder holder, final int position) {

        holder.tvName.setText( beers.get(position).getName() + " - " + beers.get(position).getContent() + "ml" );
        holder.tvBrand.setText( beers.get(position).getBrand() );
        holder.tvInStock.setText( beers.get(position).getUnits() + " X " + beers.get(position).getType());

    }

    public Beer getItem(int position) {
        return beers.get(position);
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public static class BeersViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvBrand;
        TextView tvInStock;

        public BeersViewHolder(View itemView) {
            super(itemView);

            tvName      = (TextView) itemView.findViewById(R.id.tvName);
            tvBrand     = (TextView) itemView.findViewById(R.id.tvBrand);
            tvInStock   = (TextView) itemView.findViewById(R.id.tvInStock);

        }
    }
}
