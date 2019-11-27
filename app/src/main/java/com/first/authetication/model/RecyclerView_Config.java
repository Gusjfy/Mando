package com.first.authetication.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.first.authetication.MessageActivity;
import com.first.authetication.R;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private TravelAdapter mTravelsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Travel> travels, List<User> users , List<String> keys){
        mContext = context;
        mTravelsAdapter = new TravelAdapter(travels, users, keys);
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

            mOrigem = itemView.findViewById(R.id.Origem);
            mDestino = itemView.findViewById(R.id.Destino);
            mHorario = itemView.findViewById(R.id.horario);

        }

        public void bind(final Travel t , String key, final User user){
            mOrigem.setText(t.getOrigem());
            mDestino.setText(t.getDestino());
            mHorario.setText(t.getHora());
            this.key = key;
        }

    }
    class TravelAdapter extends RecyclerView.Adapter<TravelItemView>{

        private List<Travel> mTravelList;
        private List<String> mKey;
        private List<User> mUsers;


        public TravelAdapter(List<Travel> mTravelList, List<User> mUsers, List<String> mKey) {
            this.mTravelList = mTravelList;
            this.mUsers = mUsers;
            this.mKey = mKey;
        }


        @NonNull
        @Override
        public TravelItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TravelItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TravelItemView holder, final int position) {
           holder.bind(mTravelList.get(position), mKey.get(position), mUsers.get(position));

           holder.itemView.setOnClickListener(new View.OnClickListener(){

               @Override
               public void onClick(View v) {
                   //Aqui tu cria a activity que tu quer
//                   Intent intent = new Intent(mContext, A activity que tu criou.class);
//                   intent.putExtra("travelid", mKey.get(position));
//                   mContext.startActivity(intent);
               }

           });
        }

        @Override
        public int getItemCount() {
            return mTravelList.size();
        }
    }
}
