package cn.uc.gamesdk.unity3d;

import com.sdk.common.ExitResultType;
import com.sdk.common.LoginResultType;
import com.sdk.common.LogoutResultType;
import com.sdk.common.PayResultType;
import com.sdk.common.SDKManager;

import org.json.JSONObject;

import cn.uc.gamesdk.even.SDKEventKey;
import cn.uc.gamesdk.even.SDKEventReceiver;
import cn.uc.gamesdk.even.Subscribe;
import cn.uc.gamesdk.open.OrderInfo;

/**
 * Created by junhong.kjh@alibaba.com on 2016/12/20.
 */

public class SdkEventReceiverImpl extends SDKEventReceiver {
    @Subscribe(event = SDKEventKey.ON_INIT_SUCC)
    private void onInitSucc() {

    }

    @Subscribe(event = SDKEventKey.ON_INIT_FAILED)
    private void onInitFailed(String data) {

    }

    @Subscribe(event = SDKEventKey.ON_LOGIN_SUCC)
    private void onLoginSucc(String sid) {
    	SDKManager.getInstance().onLoginResult(LoginResultType.Success, sid);
    }

    @Subscribe(event = SDKEventKey.ON_LOGIN_FAILED)
    private void onLoginFailed(String desc) {
    	SDKManager.getInstance().onLoginResult(LoginResultType.Failed, desc);
    }

    @Subscribe(event = SDKEventKey.ON_LOGOUT_SUCC)
    private void onLogoutSucc() {
    	SDKManager.getInstance().onLogoutResult(LogoutResultType.Success, "");
    }

    @Subscribe(event = SDKEventKey.ON_LOGOUT_FAILED)
    private void onLogoutFailed() {
    	SDKManager.getInstance().onLogoutResult(LogoutResultType.Failed, "");
    }

    @Subscribe(event = SDKEventKey.ON_EXIT_SUCC)
    private void onExitSucc(String desc) {
    	SDKManager.getInstance().onExitResult(ExitResultType.Success, desc);
    }

    @Subscribe(event = SDKEventKey.ON_EXIT_CANCELED)
    private void onExitCanceled(String desc) {
    	SDKManager.getInstance().onExitResult(ExitResultType.Failed, desc);
    }

    @Subscribe(event = SDKEventKey.ON_CREATE_ORDER_SUCC)
    private void onCreateOrderSucc(OrderInfo orderInfo) {
    	SDKManager.getInstance().onPayResult(PayResultType.Success, orderInfo);
    }

    @Subscribe(event = SDKEventKey.ON_PAY_USER_EXIT)
    private void onPayUserExit(OrderInfo orderInfo) {
    	SDKManager.getInstance().onPayResult(PayResultType.Exit, orderInfo);
    }

    @Subscribe(event = SDKEventKey.ON_EXECUTE_SUCC)
    private void onExecuteSucc(String msg) {
    }

    @Subscribe(event = SDKEventKey.ON_EXECUTE_FAILED)
    private void onExecuteFailed(String msg) {
    }

    @Subscribe(event = 110001)
    private void onShowPageResult(String business, String result) {
    }
}

