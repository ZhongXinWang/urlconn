package wzx.app.com.urlconn.interfacer;

import java.io.InputStream;

/**
 * Created by 王钟鑫 on 2017/4/10.
 */

public interface HttpCallBackListener {

    //访问成功
    void onFinish(InputStream inputStream);
    //访问失败
    void onError(Exception e);
}
