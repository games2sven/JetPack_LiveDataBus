package com.highgreat.sven.livedatabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //消费者订阅消息
        LiveDataBus.getInstance().with("huawei",Huawei.class)
                .observe(this, new Observer<Huawei>() {
                    @Override
                    public void onChanged(Huawei huawei) {
                        if(huawei!=null){
                            Toast.makeText(MainActivity.this, huawei.getType(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 这里就是一个发布者
     */
    public void sendMessage(View view){
        Huawei huawei = new Huawei("MATE-20");
        //厂家发布消息
        LiveDataBus.getInstance().with("huawei",Huawei.class).postValue(huawei);
    }

    public void startSecActivity(View view) {
        Intent intent=new Intent(this,SecActivity.class);
        startActivity(intent);
    }

}
