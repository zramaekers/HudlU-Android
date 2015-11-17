package ramaekers.zach.hudlu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zach.ramaekers on 11/16/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    String[] myData;
    OnAdapterInteractionListener myListener;
    public MyAdapter(Context context, String[] data) {
        super();
        myData = data;
        myListener = (OnAdapterInteractionListener) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_cardview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.myTextView.setText(myData[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myListener.onItemClicked(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.length;
    }

    public interface OnAdapterInteractionListener {
        void onItemClicked(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.item_my_text);
        }
    }
}
