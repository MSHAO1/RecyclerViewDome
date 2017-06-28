package com.a520it.recyclerview01;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.a520it.recyclerview01.R.id.tv;

/**
 * Created by ASUS on 2017/6/27.
 */

//具体操作
public class MyAdapters extends BaseAdapterss<String> {

    private static final int TYPE_ONE = 0;   //item的显示样式
    private static final int TYPE_TWO = 1;   //item的显示样式

    @Override
    public int getItemViewType(int position) {
        //可能根据数据的字段来指定对应的type
        if(position%2==0){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    @Override
    protected void setItemData(RecyclerView.ViewHolder holder, int position) {
        //有可能会有多种Item的显示样式,所有在设置值之前要进行判断
        if (getItemViewType(position) == TYPE_ONE){
            //设置值
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.tv.setText(mData.get(position));
        }
        if (getItemViewType(position) == position){
            //强转成不同的ViewHolder进行赋值
        }
    }

    @Override
    protected RecyclerView.ViewHolder getLayout(ViewGroup parent, int viewType) {
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


}
