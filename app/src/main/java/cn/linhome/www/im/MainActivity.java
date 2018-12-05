package cn.linhome.www.im;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.linhome.lib.im.FIMManager;
import cn.linhome.lib.im.callback.FIMMsgCallback;
import cn.linhome.lib.im.msg.FIMMsg;

public class MainActivity extends AppCompatActivity implements FIMMsgCallback
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FIMManager.getInstance().setDebug(true);
        FIMManager.getInstance().addMsgCallback(this);
    }

    @Override
    public boolean ignoreMsg(FIMMsg fimMsg)
    {
        return false;
    }

    @Override
    public void onReceiveMsg(FIMMsg fimMsg)
    {

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        FIMManager.getInstance().removeMsgCallback(this);
    }
}
