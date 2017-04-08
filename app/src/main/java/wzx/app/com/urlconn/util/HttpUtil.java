package wzx.app.com.urlconn.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by DELL on 2017/4/8.
 */
//与服务器交互的工具类
public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback  callback){

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);

    }
}
