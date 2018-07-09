package cn.linhome.www.im.tim;

import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import cn.linhome.lib.FIMHandler;
import cn.linhome.lib.FIMMsgReceiver;
import cn.linhome.lib.callback.FIMResultCallback;
import cn.linhome.lib.conversation.FIMConversationType;
import cn.linhome.lib.msg.FIMMsg;
import cn.linhome.lib.msg.FIMMsgData;

/**
 * Created by Administrator on 2017/11/23.
 */
public class AppIMHandler extends FIMHandler<TIMMessage>
{

    @Override
    public FIMMsgReceiver<TIMMessage> newMsgReceiver()
    {
        return new AppIMMsgReceiver();
    }

    @Override
    public FIMMsg sendMsg(String peer, FIMMsgData<TIMMessage> msgData, FIMConversationType conversationType, final String callbackId)
    {
        TIMConversation conversation = null;
        switch (conversationType)
        {
            case C2C:
                conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, peer);
                break;
            case Group:
                conversation = TIMManager.getInstance().getConversation(TIMConversationType.Group, peer);
                break;
            default:
                break;
        }

        final TIMMessage msg = msgData.parseToSDKMsg();
        final FIMMsgReceiver<TIMMessage> receiver = newMsgReceiver();
        receiver.parse(msg);
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>()
        {
            @Override
            public void onError(int code, String msg)
            {
                FIMResultCallback callback = removeCallbackById(callbackId);
                if (callback != null)
                {
                    callback.onError(code, msg);
                }
            }

            @Override
            public void onSuccess(TIMMessage timMessage)
            {
                FIMResultCallback callback = removeCallbackById(callbackId);
                if (callback != null)
                {
                    receiver.parse(timMessage);
                    callback.onSuccess(receiver);
                }
            }
        });

        return receiver;
    }

    @Override
    public void joinGroup(String groupId, String callbackId)
    {

    }

    @Override
    public void quitGroup(String groupId, String callbackId)
    {

    }
}
