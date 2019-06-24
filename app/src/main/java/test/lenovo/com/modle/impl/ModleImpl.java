package test.lenovo.com.modle.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.Serializable;

import test.lenovo.com.bean.User;
import test.lenovo.com.modle.Modle;

public class ModleImpl implements Modle {
    private Activity activity;
    private User user;
    public ModleImpl(User user, Activity activity){
        this.user = user;
        this.activity = activity;
    }
    @Override
    public void sucess() {
        Toast.makeText(activity, "Login Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void faile() {
        Toast.makeText(activity, "Account & Pwd is Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void error(String error) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void activity(Activity activity) {

    }
}
