package test.lenovo.com.testlogin;

import android.os.Handler;
import android.text.TextUtils;


public class XLogin {

    public static XLogin getInstance(){
        return XLoginHelper.INSTANCE;
    }
    private ILoginListener listener;
    private static class XLoginHelper{
        private static final XLogin INSTANCE = new XLogin();
    }


    public void doLogin(String name,String pwd,ILoginListener listener){
        this.listener = listener;
        realLogin(name,pwd);
    }

    private void realLogin(String name, String pwd){
        if (name.equals("apple") && pwd.equals("123456")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onSuccess();
                }
            },1500);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onError();
                }
            },1500);
        }
    }

}
