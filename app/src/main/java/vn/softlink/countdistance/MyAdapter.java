package vn.softlink.countdistance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * *****************************************************************
 *
 * @Project: LocationNativeCore
 * @Created: Bao NQ 2018/10/15
 * @Description: ...
 * All Right Reserved.
 * *******************************************************************
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Acceleration> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public MyViewHolder(View v) {
            super(v);
            mTextView1 = v.findViewById(R.id.ax_record_txt);
            mTextView2 = v.findViewById(R.id.ay_record_txt);
            mTextView3 = v.findViewById(R.id.az_record_txt);
            mTextView4 = v.findViewById(R.id.time_txt);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Acceleration> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView1.setText(mDataset.get(position).getAx());
        holder.mTextView2.setText(mDataset.get(position).getAy());
        holder.mTextView3.setText(mDataset.get(position).getAz());
        holder.mTextView4.setText(mDataset.get(position).getTime());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
