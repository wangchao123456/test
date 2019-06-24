package test.lenovo.com.modle;

import android.app.Activity;

import java.io.Serializable;

import test.lenovo.com.bean.User;

public interface Modle {
    void sucess();
    void faile();
    void error(String error);
    void activity(Activity activity);
}
