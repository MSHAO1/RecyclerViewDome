package com.a520it.recyclerview01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mContentMain;
    private RecyclerView mRv;
    private ArrayList<String> mStrings;
    private LinearLayoutManager mLinearLayoutManager;
    private LinearLayoutManager mLinearLayoutManager1;
    private GridLayoutManager mGridLayoutManager;
    private GridLayoutManager mGridLayoutManager1;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private myAdapter mMyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initData();


    }

    private void initView() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        mContentMain = (LinearLayout) findViewById(R.id.content_main);
        mRv = (RecyclerView) findViewById(R.id.rv);
    }

    //初始化数据
    private void initData() {

        mStrings = new ArrayList<>();
        for (char i = 'A'; i <='Z'; i++) {
            mStrings.add(i+"");
        }

        //创建Adapter对象
        mMyAdapter = new myAdapter(mStrings);
        //必须要设置layout样式才能使用
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRv.setLayoutManager(mLinearLayoutManager);
        //可以给item设置动画   使用的是第三方动画库
       mRv.setItemAnimator(new FadeInRightAnimator());
        //手动设置分割线    使用的第三方
        mRv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.RED)
                .size(2)
                .margin(0,2)
                .build());
        //设置
        mRv.setAdapter(mMyAdapter);

        //设置Item的点击事件
        mMyAdapter.setListerent(new myAdapter.IRecyclerItemChangeListerent() {
            @Override
            public void onitemLister(View v, int postion) {
                Toast.makeText(MainActivity.this, postion + "", Toast.LENGTH_SHORT).show();
            }
        });



    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击切换不同的显示
        int id = item.getItemId();
        if (id == R.id.action_lv) {
            mLinearLayoutManager1 = new LinearLayoutManager(getApplicationContext()); //显示为listView
            mRv.setLayoutManager(mLinearLayoutManager1);
            return true;
        }else if (id == R.id.action_gv) {
            mGridLayoutManager = new GridLayoutManager(getApplicationContext(),3); //显示为Grid
            mRv.setLayoutManager(mGridLayoutManager);
            return true;
        }else if (id == R.id.action_hor_gv) {
            //显示为横向的Grid  true为是否逆转
            mGridLayoutManager1 = new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.HORIZONTAL, false);
            mRv.setLayoutManager(mGridLayoutManager1);
            return true;
        }else if (id == R.id.action_pubu) {
            //显示为横向瀑布流
            mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            mRv.setLayoutManager(mStaggeredGridLayoutManager);
            return true;
        }else if (id == R.id.action_add) {
            mMyAdapter.addItem(2,"嘿嘿嘿");
            return true;
        }else if (id == R.id.action_delete) {
            mMyAdapter.deleteItem(2);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
