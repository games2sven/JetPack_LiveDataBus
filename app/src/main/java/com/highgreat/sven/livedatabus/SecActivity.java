package com.highgreat.sven.livedatabus;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class SecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //消费者订阅消息
        LiveDataBus.getInstance().with("huawei",Huawei.class).observe(this,
                new Observer<Huawei>() {
                    @Override
                    public void onChanged(Huawei huawei) {
                        if(huawei!=null){
                            Toast.makeText(SecActivity.this, huawei.getType(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 发布者
     * @param view
     */
    public void sendMessage(View view){
        Huawei huawei=new Huawei("META-20");
        //厂家发布消息
        LiveDataBus.getInstance().with("huawei",Huawei.class).postValue(huawei);
    }

}
