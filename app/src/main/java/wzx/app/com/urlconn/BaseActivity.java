package wzx.app.com.urlconn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 王钟鑫 on 2017/4/10.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected   void ShowPregress(){

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("加载数据");
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    protected void hideProgress(){

        progressDialog.dismiss();
    }
}
