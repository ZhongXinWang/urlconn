package wzx.app.com.urlconn;

import android.content.Context;
import android.content.Intent;

/**
 * Created by DELL on 2017/4/8.
 */

public class ControlIntent {

    private Context mContext;
    private Intent mIntent;
    private String mName;
    public ControlIntent(Context mContext, Intent mIntent,String name) {
        this.mContext = mContext;
        this.mIntent = mIntent;
        this.mName = name;
    }
    public Context getContext() {
        return mContext;
    }

    public Intent getIntent() {
        return mIntent;
    }
    public String getName(){

        return mName;
    }
    //启动activity
    public void startAction(){

        getContext().startActivity(getIntent());
    }

    public String toString(){

        return getName();
    }

}
