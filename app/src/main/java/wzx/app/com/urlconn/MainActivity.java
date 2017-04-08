package wzx.app.com.urlconn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ControlIntent> mList;
    private RecyclerView mRecycle_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycle_List = (RecyclerView) findViewById(R.id.recycle_list);
        initData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        mRecycle_List.setAdapter(new MyRecycleViewAdapter());
        mRecycle_List.setLayoutManager(layoutManager);

    }
    private void initData(){

        mList = new ArrayList<>();
        mList.add(new ControlIntent(MainActivity.this,new Intent(MainActivity.this,OkHttpGetWebSourceActivity.class),"使用OkHttp获取整个网页"));
    }
    private class ViewHodler extends RecyclerView.ViewHolder{

        private ControlIntent mControl;
        private TextView textView;
        public ViewHodler(View viewItem){

            super(viewItem);
            textView = (TextView) viewItem.findViewById(R.id.text_name);
            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //跳转界面
                    mControl.startAction();
                }
            });
        }
        public void bindParame(ControlIntent value){

            this.mControl = value;
            textView.setText(mControl.toString());
        }
    }
    private class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHodler>{

        @Override
        public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

           View v =  LayoutInflater.from(MainActivity.this).inflate(R.layout.recycle_view_item,parent,false);
            return new ViewHodler(v);
        }

        @Override
        public void onBindViewHolder(ViewHodler holder, int position) {

            ControlIntent controlIntent = mList.get(position);
            holder.bindParame(controlIntent);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
