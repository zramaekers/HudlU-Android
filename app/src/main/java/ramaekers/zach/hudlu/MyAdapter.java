package ramaekers.zach.hudlu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import ramaekers.zach.hudlu.models.MashableNewsItem;

/**
 * Created by zach.ramaekers on 11/16/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    List<MashableNewsItem> myData;
    OnAdapterInteractionListener myListener;
    RequestQueue mRequestQueue;
    public MyAdapter(Context context, List<MashableNewsItem> data) {
        super();
        myData = data;
        myListener = (OnAdapterInteractionListener) context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_cardview_layout_class3, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MashableNewsItem currentData = myData.get(position);
        holder.myTitle.setText(currentData.title);
        holder.myAuthor.setText(currentData.author);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onItemClicked(v, position);
            }
        });
        ImageRequest request = new ImageRequest(currentData.image,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.myImage.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ALPHA_8,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FetchingImage", error.toString());
                    }
                });
        mRequestQueue.add(request);
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public interface OnAdapterInteractionListener {
        void onItemClicked(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myTitle;
        TextView myAuthor;
        ImageView myImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            myTitle = (TextView) itemView.findViewById(R.id.item_title);
            myAuthor = (TextView) itemView.findViewById(R.id.item_author);
            myImage = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
