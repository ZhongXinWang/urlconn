package wzx.app.com.urlconn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import wzx.app.com.urlconn.interfacer.HttpCallBackListener;
import wzx.app.com.urlconn.util.HttpUtil;

/*使用okhttp从获取百度界面*/
public class HttpConnectionGetWebSourceActivity extends AppCompatActivity {

    private EditText mtext;
    private TextView mTextView;
    private ProgressDialog progressDialog = null;

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
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(msg.obj.toString());
            hideProgress();
        }
    };
    //连接网址，获取资源
    private void startConnect() {
       String text_url = mtext.getText().toString().trim();
        String str = "";
        if (text_url.equals("") || text_url == null) {

            text_url = "http://www.baidu.com";
        }
       HttpUtil.sendRequestWithHttpURL(text_url, new HttpCallBackListener() {
            @Override
            public void onFinish(InputStream inputStream) {
                final StringBuilder response = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                try {
                    while ((str = reader.readLine()) != null) {

                        response.append(str);
                    }
                    if (response != null) {

                        Message message = new Message();
                        message.obj = response;
                        handler.sendMessage(message);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (reader != null) {

                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
            @Override
            public void onError(Exception e) {

              hideProgress();
              Toast.makeText(HttpConnectionGetWebSourceActivity.this,"请求失败",Toast.LENGTH_SHORT).show();

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
