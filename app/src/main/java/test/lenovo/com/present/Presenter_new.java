package test.lenovo.com.present;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import test.lenovo.com.bean.User;
import test.lenovo.com.modle.Modle;
import test.lenovo.com.modle.impl.ModleImpl;
import test.lenovo.com.mtest.R;
import test.lenovo.com.view.MyView;

public class Presenter_new {

    private MyView view;
    private Modle modle;
    public void login(EditText account, EditText pwd, Activity activity){
        User user = new User();
        if(view != null){
            if(TextUtils.isEmpty(account.getText()) || TextUtils.isEmpty(pwd.getText())){
                view.empty("用户账户密码不能为null");
                return;
            }
            user.setAccount(account.getText().toString());
            user.setPwd(pwd.getText().toString());
            modle = new ModleImpl(user, activity);
            if(user.getAccount().equals("123456") &&
                    user.getPwd().equals("123456")){
                modle.sucess();
            }else{
                modle.faile();
            }
        }else{
            user.setError("view  null");
            modle = new ModleImpl(user, activity);
            Log.e("aaaaa", "========view  null========");
            modle.error("view  null");
        }
    }

    public void login(View contantView, Activity activity){
        User user = new User();
        if(view != null){
            EditText etAccount = (EditText) contantView.findViewById(R.id.et_account);
            EditText etPwd = (EditText) contantView.findViewById(R.id.et_account);
            if(TextUtils.isEmpty(etAccount.getText()) || TextUtils.isEmpty(etPwd.getText())){
                view.empty("用户账户密码不能为null");
                return;
            }
            user.setAccount(etAccount.getText().toString());
            user.setPwd(etPwd.getText().toString());
            modle = new ModleImpl(user, activity);
            if(user.getAccount().equals("123456") &&
                    user.getPwd().equals("123456")){
                modle.sucess();
            }else{
                modle.faile();
            }
        }else{
            user.setError("view  null");
            modle = new ModleImpl(user, activity);
            Log.e("aaaaa", "========view  null========");
            modle.error("view  null");
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
