package wzx.app.com.urlconn;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import wzx.app.com.urlconn.entity.WeatherEntities;
import wzx.app.com.urlconn.util.HttpUtil;

public class HttpRequestImageActivity extends BaseActivity {

    private Spinner mSpinner;
    private List<WeatherEntities> mList;
    private ArrayAdapter<WeatherEntities> mAdapter;
    private TextView mName;
    private ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_http_request_image);

        mSpinner = (Spinner) findViewById(R.id.select_weather_id);
        mImage = (ImageView) findViewById(R.id.weather_image);
        mName = (TextView) findViewById(R.id.weather_name);
        initData();
        mAdapter = new ArrayAdapter<WeatherEntities>(this,R.layout.spinner_item,mList);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String url = mAdapter.getItem(position).getmAddress();
                final String name = mAdapter.getItem(position).getmName();
                ShowPregress();
                HttpUtil.sendOkHttpRequest(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Toast.makeText(HttpRequestImageActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                        hideProgress();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {


                        final byte[] bytes = response.body().bytes();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(bytes != null){

                                    mImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
                                    mName.setText(name);
                                    hideProgress();
                                }

                            }
                        });

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initData(){

        mList = new ArrayList<>();
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b0.gif","晴"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b1.gif","阴"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b2.gif","多云"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b3.gif","中雨"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b4.gif","雷阵雨"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b5.gif","雷阵雨加雪"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b6.gif","雨加雪"));
        mList.add(new WeatherEntities("http://m.weather.com.cn/img/b7.gif","小雨"));

    }
}
