package test.lenovo.com.myviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import test.lenovo.com.mtest.R;

/*
* 自定义控件的demo
* 自定义输入框
* 1、input_icon -----输入框前面的图标
* 2、input_hint -----输入框提示内容
* 3、is_password -----输入内容是否需要以密文的形式去展示
* 在res/values/创建attrs 文件 自定义属性
* */
public class MyInputView extends FrameLayout {
    private int inputIcon;
    private String inputHint;
    private boolean isPassword;
    private View inPutView;
    private ImageView img;
    private EditText et;
    public MyInputView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public MyInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MyInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    //创建一个view 的初始化方法 名字随便起
    private void initView(Context context, AttributeSet attrs){
        if(attrs == null)
            return;
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        //参数1 自定义的属性 参数2 默认的值
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.mipmap.ic_launcher);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password, false);
        //使用完之后手动是放避免内存泄漏
        typedArray.recycle();
        //需要创建一个部件文件把所需要的view引入进来 绑定上
        inPutView = LayoutInflater.from(context).inflate(R.layout.input_view, null, false);
        img = (ImageView)inPutView.findViewById(R.id.icon);
        et = (EditText) inPutView.findViewById(R.id.et_input);
        //布局关联属性
        img.setImageResource(inputIcon);
        et.setHint(inputHint);
        et.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);
        addView(inPutView);
    }
    //获取返回输入内容
    public String getInputStr(){
        return (!TextUtils.isEmpty(et.getText()) ? et.getText().toString().trim() : "");
    }

    public void setOnTextChangeListener(String str){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
