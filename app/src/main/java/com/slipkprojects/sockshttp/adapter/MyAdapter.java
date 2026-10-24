package com.slipkprojects.sockshttp.adapter;

import xyz.devxyz.vpn.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import android.graphics.Color;
import android.view.animation.*;
import com.slipkprojects.sockshttp.MyApplication;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<HashMap<String, String>> data;
    private HashMap<String, String> resultp = new HashMap<String, String>();
    private Context cont;
    private static boolean isServer;
    private static int defpos;

    public class MyViewHolder extends RecyclerView.ViewHolder {

		public TextView country;
        public ImageView flag;
        public com.google.android.material.card.MaterialCardView ll;
		public ImageView checker;
		public TextView spInfo;
		public TextView cat;
		public LinearLayout cat_bg;

        public MyViewHolder(View view) {
            super(view);
            ll = (com.google.android.material.card.MaterialCardView) view.findViewById(R.id.serverListLayout);
            country = (TextView) view.findViewById(R.id.imageNameSpinner);
            flag = (ImageView) view.findViewById(R.id.imageIconSpinner);
			checker = (ImageView)view.findViewById(R.id.checker_img);
			spInfo = (TextView)view.findViewById(R.id.spinfo);
			cat = (TextView)view.findViewById(R.id.cat);
			cat_bg = (LinearLayout)view.findViewById(R.id.cat_bg);
        }
    }

    public MyAdapter(Context c, boolean isServer, int defpos, ArrayList<HashMap<String, String>> arraylist) {
        data = arraylist;
        cont = c;
        this.isServer = isServer;
        this.defpos = defpos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_row, parent, false);
        return new MyViewHolder(itemView);
    }

	@Deprecated
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        resultp = data.get(holder.getAdapterPosition());
        if (defpos == holder.getAdapterPosition()) {
            //holder.ll.setBackgroundResource(R.drawable.selected_server_bg);
			holder.checker.setImageResource(R.drawable.cnt);
        } else {
			//holder.ll.setBackgroundResource(Color.TRANSPARENT);
            //holder.ll.setBackgroundResource(R.drawable.selected_server_bg);
			holder.checker.setImageResource(Color.TRANSPARENT);
        }
        holder.country.setText(resultp.get("Name"));
		holder.spInfo.setText(resultp.get("INFO"));
		Animation anim = AnimationUtils.loadAnimation(MyApplication.getApp(), R.drawable.animation_enter);
        try {
            if(isServer){
                InputStream inputStream = cont.getAssets().open("flags/" + resultp.get("Flag"));            
                holder.flag.setImageDrawable(Drawable.createFromStream(inputStream, resultp.get("Flag")));
				holder.cat.setText("VIP");
				holder.cat_bg.setBackgroundResource(R.drawable.bg_gradient);
                if (inputStream != null) {          
                    inputStream.close();
                }
            } else {
                String name = resultp.get("Name").toLowerCase();
				//holder.cat.setText("");
				holder.cat_bg.setBackgroundResource(Color.TRANSPARENT);
				if (name.contains("globe")) {
					holder.flag.setImageResource(R.drawable.ic_globe);
				} else if (name.contains("smart")) {
					holder.flag.setImageResource(R.drawable.ic_smart);
				} else if (name.contains("gtm")) {
					holder.flag.setImageResource(R.drawable.ic_gtm);
				} else if (name.contains("tm")) {
					holder.flag.setImageResource(R.drawable.ic_tm);
				} else if (name.contains("tnt")) {
					holder.flag.setImageResource(R.drawable.ic_tnt);
				} else if(name.contains("sun")) {
					holder.flag.setImageResource(R.drawable.ic_sun);
				} else if(name.contains("dito")) {
					holder.flag.setImageResource(R.drawable.ic_dito);
				} else if(name.contains("gomo")) {
					holder.flag.setImageResource(R.drawable.ic_gomo);
				} else if(name.contains("sts")) {
					holder.flag.setImageResource(R.drawable.ic_sts);
				} else if(name.contains("piso")){
					holder.flag.setImageResource(R.drawable.pisovpn);
				} else{
					holder.flag.setImageResource(R.drawable.icon_speed);
				}
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
		holder.ll.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
