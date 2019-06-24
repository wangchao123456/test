package test.lenovo.com.present;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import test.lenovo.com.view.MyView;

public class Presenter {

    private MyView view;

    public void login(EditText account, EditText pwd){
        if(view != null){
            if(TextUtils.isEmpty(account.getText()) || TextUtils.isEmpty(pwd.getText())){
                view.empty("用户账户密码不能为null");
                return;
            }
            if(account.getText().toString().equals("123123") &&
                    account.getText().toString().equals("123123")){
                view.success("login success");
            }else{
                view.error("Login fail");
            }
        }else{
            Log.e("aaaaa", "========view  null========");
        }
    }

    /** *
     * 绑定
     * @param loginView
     * */
    public void attach( MyView loginView) {
        this.view = loginView;
    }


}
