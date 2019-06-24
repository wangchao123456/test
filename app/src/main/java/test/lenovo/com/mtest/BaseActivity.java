package test.lenovo.com.mtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

public class BaseActivity extends Activity {
    //简写findviewbyid  返回view 子类型的 数据
    public <T extends View> T fd(@IdRes int id){
        return findViewById(id);
    }
    //boolean ? a : b 三元运算 表示 true 的时候显示a 为false 的时候显示 b
}
