package wzx.app.com.urlconn.util;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import wzx.app.com.urlconn.interfacer.HttpCallBackListener;

/**
 * Created by 王钟鑫 on 2017/4/8.
 */
//与服务器交互的工具类
public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback  callback){

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);

    }
    public static void sendRequestWithHttpURL(final String address,final HttpCallBackListener listenter){

        //开启线程获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {


                HttpURLConnection connection = null;
                InputStream inputStream = null;
                //使用回调函数
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setInstanceFollowRedirects(false);
                   // Log.d("code",address+connection.getResponseCode()+""+connection.getHeaderField("Location"));
                   if(connection.getResponseCode() == 200){

                        inputStream = connection.getInputStream();
                        if(listenter != null){

                            listenter.onFinish(inputStream);
                        }
                   }
                }catch (Exception e){

                    Log.d("sendRequestWithHttpURL",""+e.getMessage());
                    if(listenter != null){

                        listenter.onError(e);
                    }

                }finally {

                    if(connection != null){

                        connection.disconnect();

                    }
                }

            }
        }).start();

    }
}
