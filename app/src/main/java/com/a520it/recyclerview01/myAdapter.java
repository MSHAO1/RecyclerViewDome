package com.a520it.recyclerview01;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.a520it.recyclerview01.R.id.tv;

public class myAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mData;
    IRecyclerItemChangeListerent mListerent;
    private static final int TYPE_ONE = 0;   //item的显示样式
    private static final int TYPE_TWO = 1;   //item的显示样式

    public myAdapter(ArrayList<String> data) {
        mData = data;
    }

    //确定数据个数
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
        public int getItemViewType(int position) {
            //可能根据数据的字段来指定对应的type
            if(position%2==0){
                return TYPE_ONE;
            }else{
                return TYPE_TWO;
            }
    }



    //确定布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //当有多个Item样式时先进行判断
        if (viewType == TYPE_ONE){
            //加载布局
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            //创建ViewHolder对象
            MyViewHolder myHolederView = new MyViewHolder(inflate);
            //查找控件
            myHolederView.tv = (TextView) inflate.findViewById(tv);
            return myHolederView;
        }
        //显示不同的布局
        if (viewType == TYPE_TWO){
            //创建不同的ViewHolder
           return null;
        }
        return  null;
    }


    //设置值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //有可能会有多种Item的显示样式,所有在设置值之前要进行判断
        if (getItemViewType(position) == TYPE_ONE){
            //设置值
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.tv.setText(mData.get(position));
        }
        if (getItemViewType(position) == position){
            //强转成不同的ViewHolder进行赋值
        }

        //设置Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件回调到Activity
                if (mListerent != null){
                    mListerent.onitemLister(v,position);
                }
            }
        });

    }
    //设置点击事件借口回调
    public  void  setListerent(IRecyclerItemChangeListerent listerent){
        mListerent = listerent;
    }

    //创建一个添加数据的方法
    public void addItem(int position,String item){
        mData.add(position,item);
        notifyItemInserted(position);   //带动画的刷新数据
    }

    //创建一个删除的方法
    public void deleteItem(int position){
        mData.remove(position);
        notifyItemRemoved(position);    //带动画的刷新数据
    }



    //设置一个回调的接口
    interface  IRecyclerItemChangeListerent {
         void onitemLister(View v,int postion);
    }






}
