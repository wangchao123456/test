package test.lenovo.com.mtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import test.lenovo.com.adapter.RecycleAdapterDome;
import test.lenovo.com.present.Presenter;
import test.lenovo.com.present.Presenter_new;
import test.lenovo.com.view.MyView;
//ExpandableListView 二级列表
public class MainActivity extends Activity implements MyView, View.OnClickListener {
    private EditText etAccount, etPwd;
    private Button btnLogin;
    //private Presenter presenter;
    private Presenter_new presenter;
    private BluetoothAdapter bluetoothAdapter;
    //声明RecyclerView
    private RecyclerView recyclerView;
    //声明适配器
    private RecycleAdapterDome adapterDome;
    private List<String> list;
    private View  contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(contentView);
//        etAccount = (EditText)contentView.findViewById(R.id.et_account);
//        etPwd = (EditText)contentView.findViewById(R.id.et_pwd);
        btnLogin = (Button) contentView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
//        presenter = new Presenter();
//        presenter.attach(this);
        presenter = new Presenter_new();
        presenter.attach(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//============================================================================================================
        recyclerView = (RecyclerView) findViewById(R.id.rview);
        list = new ArrayList<>();
        for (int i = 0; i<10; i++){
            list.add("这是第"+i+"个测试");
        }
        adapterDome = new RecycleAdapterDome(this, list);
        adapterDome.setOnItemClickLitener(new RecycleAdapterDome.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("aaaaa", "===点击===="+ "这是第"+position+"个测试");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.e("aaaaa", "===长按===="+ "这是第"+position+"个测试");
            }
        });
/*
        与ListView效果对应的可以通过LinearLayoutManager来设置  LinearLayoutManager.VERTICAL 竖直 HORIZONTAL 水平
        与GridView效果对应的可以通过GridLayoutManager来设置
        与瀑布流对应的可以通过StaggeredGridLayoutManager来设置
        */
        GridLayoutManager manager = new GridLayoutManager(this, 3);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterDome);
    }

    @Override
    public void empty(String empty) {
        Log.e("aaaaa", "=========" + empty);
    }

    @Override
    public void success(String succsee) {
        Log.e("aaaaa", "=========" + succsee);
    }

    @Override
    public void error(String error) {
        Log.e("aaaaa", "=========" + error);
    }

    @Override
    public void onClick(View v) {
//        presenter.login(etAccount, etPwd);
//        presenter.login(etAccount, etPwd, MainActivity.this);
        presenter.login(contentView, MainActivity.this);
/*        if(!bluetoothAdapter.isEnabled()){
            Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enable, 1);
//            bluetoothAdapter.enable();


        }

        if(bluetoothAdapter.isEnabled()){
            //获取本机蓝牙名称
            String name = bluetoothAdapter.getName();
            //获取本机蓝牙地址
            String address = bluetoothAdapter.getAddress();
            //获取已配对蓝牙设备
            Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
            for(BluetoothDevice bonddevice:devices){
                Log.e("aaaaa", "======1=======bonded device name ="+bonddevice.getName()+" address"+bonddevice.getAddress());
            }
            if (bluetoothAdapter.getScanMode() !=
                    BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
                Intent discoverableIntent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(
                        BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
                startActivity(discoverableIntent);
            }
            bluetoothAdapter.startDiscovery();
            IntentFilter filter = new IntentFilter();
            //发现设备
            filter.addAction(BluetoothDevice.ACTION_FOUND);
//            //设备连接状态改变
//            filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//            //蓝牙设备状态改变
//            filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBluetoothReceiver, filter);
        }*/
        startActivity(new Intent(MainActivity.this, MusicActivity.class));
    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("aaaaa","======1=======mBluetoothReceiver action =");
            String action = intent.getAction();
            Log.e("aaaaa","======1=======mBluetoothReceiver action ="+action);
            //每扫描到一个设备，系统都会发送此广播。
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                //获取蓝牙设备
                BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(scanDevice == null || scanDevice.getName() == null)
                    return;
                Log.e("aaaaa", "======1=======name="+scanDevice.getName()+"address="+scanDevice.getAddress());
                //蓝牙设备名称
                String name = scanDevice.getName();
//                if(name != null && name.equals(BLUETOOTH_NAME)){
//                    mBluetoothAdapter.cancelDiscovery();
//                    //取消扫描
//                    mProgressDialog.setTitle(getResources().getString(R.string.progress_connecting));                   //连接到设备。
//                    mBlthChatUtil.connect(scanDevice);
//                }
            }else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                Log.e("aaaaa", "======1=======");
            }
        }

    };
}
