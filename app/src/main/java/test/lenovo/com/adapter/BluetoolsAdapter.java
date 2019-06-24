package test.lenovo.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.lenovo.com.bean.BlueToolsEntity;
import test.lenovo.com.mtest.R;

public class BluetoolsAdapter extends RecyclerView.Adapter<BluetoolsAdapter.MyViewHolder> {
    private Context context;
    private List<BlueToolsEntity> dataList;
    private View contentView;
    private OnClickListener onClickListener;
    public BluetoolsAdapter(Context context, List<BlueToolsEntity> dataList){
        Log.e("aaaaa", "=========blue_name====BluetoolsAdapter====" + dataList.size());
        this.context = context;
        this.dataList = dataList;
    }
    @Override
    public BluetoolsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        contentView = LayoutInflater.from(context).inflate(R.layout.bluetools_itme, parent, false);
        MyViewHolder holder = new MyViewHolder(contentView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BluetoolsAdapter.MyViewHolder holder, int position) {
        Log.e("aaaaa", "=========blue_name====onBindViewHolder====" + dataList.get(position).getName());
          holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
          holder.tv.setText(dataList.get(position).getName());
          if(onClickListener != null)
          {
              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickListener.onItemClickListener(holder.itemView, holder.getLayoutPosition());
                  }
              });
              holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                  @Override
                  public boolean onLongClick(View v) {
                      onClickListener.onItemLongClickListener(holder.itemView, holder.getLayoutPosition());
                      return true;
                  }
              });
          }
    }

    @Override
    public int getItemCount() {
        Log.e("aaaaa", "=========blue_name====getItemCount====" + dataList.size());
        return dataList.size();
    }
    //添加这个方法防止错乱
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv_title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }

   public interface OnClickListener{
        void onItemClickListener(View view, int position);
        void onItemLongClickListener(View view, int position);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

}
