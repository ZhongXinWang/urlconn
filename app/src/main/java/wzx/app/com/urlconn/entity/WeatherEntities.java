package wzx.app.com.urlconn.entity;

/**
 * Created by 王钟鑫 on 2017/4/10.
 */

public class WeatherEntities {
    private String mAddress;
    private String mName;
    public WeatherEntities(String mAddress, String mName) {
        this.mAddress = mAddress;
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
    public String toString(){

        return getmName();
    }
}
