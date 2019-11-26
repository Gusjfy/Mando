package com.first.authetication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.first.authetication.R;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private TravelAdapter mTravelsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Travel> travels, List<String> keys){
        mContext = context;
        mTravelsAdapter = new TravelAdapter(travels, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mTravelsAdapter);

    }



    class TravelItemView extends RecyclerView.ViewHolder {
        private TextView mOrigem;
        private TextView mDestino;
        private TextView mHorario;

        private String key;

        public TravelItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.travel_list_item, parent, false));

            mOrigem = (TextView) itemView.findViewById(R.id.Origem);
            mDestino = (TextView) itemView.findViewById(R.id.Destino);
            mHorario = (TextView) itemView.findViewById(R.id.horario);
        }

        public void bind(Travel t , String key){
            mOrigem.setText(t.getOrigem());
            mDestino.setText(t.getDestino());
            mHorario.setText(t.getHora());
            this.key = key;
        }

    }
    class TravelAdapter extends RecyclerView.Adapter<TravelItemView>{

        private List<Travel> mTravelList;
        private List<String> mKey;

        public TravelAdapter(List<Travel> mTravelList, List<String> mKey) {
            this.mTravelList = mTravelList;
            this.mKey = mKey;
        }


        @NonNull
        @Override
        public TravelItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TravelItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TravelItemView holder, int position) {
            holder.bind(mTravelList.get(position), mKey.get(position));
        }

        @Override
        public int getItemCount() {
            return mTravelList.size();
        }
    }
}
