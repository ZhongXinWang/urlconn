package wzx.app.com.urlconn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import wzx.app.com.urlconn.util.HttpUtil;

/*使用okhttp从获取百度界面*/
public class OkHttpGetWebSourceActivity extends AppCompatActivity {

    private EditText mtext;
    private TextView mTextView;
    ProgressDialog progressDialog= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_get_web_source);

        mtext = (EditText) findViewById(R.id.test_url);
        mTextView = (TextView) findViewById(R.id.get_content);
        mTextView.setText("");
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始加载数据进度条
                ShowPregress();
                //开启线程，获取数据

                startConnect();


            }


        });
    }
    //连接网址，获取资源
    private void startConnect(){
        //开启线程
                String text_url = mtext.getText().toString().trim();
                String str = "";
                if(text_url.equals("") || text_url == null){

                    text_url = "http://www.baidu.com";
                }
                    /*
                //使用get的方式获取
                OkHttpClient okHttpClient = new OkHttpClient();
                //设置一个请求
                Request.Builder builder =  new Request.Builder();
                builder.url(text_url);
                Request request = builder.build();
                //发送请求
                //同步请求
                    Response response = okHttpClient.newCall(request).execute();
                    if(response != null){

                        showUI(response.body().string());
                    }*/
                    //调用工具类
                    HttpUtil.sendOkHttpRequest(text_url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            OkHttpGetWebSourceActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(OkHttpGetWebSourceActivity.this,"获取失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call,  Response response) throws IOException {
                            final String str = response.body().string();
                            //回到主线程，更新数据
                            OkHttpGetWebSourceActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                mTextView.setText(str);
                                hideProgress();
                                }
                            });
                        }
                    });
    }
    private void ShowPregress(){

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("加载数据");
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    private void hideProgress(){

        progressDialog.dismiss();
    }
}
