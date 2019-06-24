package test.lenovo.com.mtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.receiver.BluetoothReceiver;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import test.lenovo.com.adapter.BluetoolsAdapter;
import test.lenovo.com.bean.BlueToolsEntity;

public class BlueToolsActivity extends Activity {
    private List<String> devices;
    private List<BluetoothDevice> deviceList;
    private final String lockName = "BOLUTEK";
    private String message = "000001";
    private RecyclerView recyclerView;
    private Button btnAdd;
    private List<BlueToolsEntity> dataList;
    private BluetoolsAdapter bluetoolsAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blue_tools_activity);
//        BluetoothClient mClint = new BluetoothClient(this);
//        SearchRequest request = new SearchRequest.Builder().
//                // 先扫BLE设备3次，每次3s
//                searchBluetoothLeDevice(3000, 3).
//                // 再扫经典蓝牙5s,在实际工作中没用到经典蓝牙的扫描
//                searchBluetoothClassicDevice(5000).
//                // 再扫BLE设备2s
//                searchBluetoothLeDevice(2000)
//                .build();
//        mClint.search(request, new SearchResponse() {
//            @Override
//            public void onSearchStarted() {
//
//            }
//
//            @Override
//            public void onDeviceFounded(SearchResult device) {
//                Log.e("aaaaa", "=======SearchResult=======" + device.getAddress() + "==" + device.getName());
//            }
//
//            @Override
//            public void onSearchStopped() {
//
//            }
//
//            @Override
//            public void onSearchCanceled() {
//
//            }
//        });
        recyclerView = (RecyclerView)findViewById(R.id.blutools_rv);
        btnAdd = (Button)findViewById(R.id.btn_lianjie);
        dataList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            BlueToolsEntity blueToolsEntity = new BlueToolsEntity();
            blueToolsEntity.setImg(R.mipmap.ic_launcher);
            blueToolsEntity.setName("blue_tooth" + i);
            dataList.add(blueToolsEntity);
        }
        bluetoolsAdapter = new BluetoolsAdapter(this, dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(1000);
//        animator.setMoveDuration(1000);
        recyclerView.setItemAnimator(animator);
        recyclerView.setAdapter(bluetoolsAdapter);
        bluetoolsAdapter.setOnClickListener(new BluetoolsAdapter.OnClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                dataList.remove(position);
                bluetoolsAdapter.notifyItemRemoved(position);
                Toast.makeText(BlueToolsActivity.this, "Removed" + position, Toast.LENGTH_LONG).show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlueToolsEntity blueToolsEntity = new BlueToolsEntity();
                blueToolsEntity.setImg(R.drawable.ic_launcher);
                blueToolsEntity.setName("张三");
                dataList.add(2, blueToolsEntity);
                bluetoolsAdapter.notifyItemInserted(2);
            }
        });

    }


    private void search(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //判断是否打开
        if(!bluetoothAdapter.isEnabled()){
            //没打开打开 1  带打开申请的提示
            Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enable, 1);
            //没打开打开 2 直接打开不提示
//            bluetoothAdapter.enable();
        }
    }
}
