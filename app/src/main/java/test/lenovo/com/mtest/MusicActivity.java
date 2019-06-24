package test.lenovo.com.mtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;

import test.lenovo.com.myviews.MyInputView;

public class MusicActivity extends BaseActivity implements View.OnClickListener {
    private MyInputView inputView;
    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity);
        initView();
    }

    private void initView(){
        inputView = (MyInputView) fd(R.id.inputview);
        btn = (Button)fd(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(inputView.getInputStr())){
            if( RegexUtils.isMobileExact(inputView.getInputStr())){
                startActivity(new Intent(MusicActivity.this, MainActivity.class));
                Toast.makeText(this, "Phone is right", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Phone is error", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "click=====null", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("aaaaa", "==============生命周期=======onStart=======");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("aaaaa", "==============生命周期=======onResume=======");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("aaaaa", "==============生命周期=======onPause=======");
    }
}
