#include "AppDelegate.h"
#include "cocos2d.h"
#include "CCEventType.h"
#include "platform/android/jni/JniHelper.h"
#include "JavaCppHelper.h"
#include "WeiboManager.h"
//#include "TK_MIManager.h"
#include "TK_SDKFactory.h"
#include <jni.h>
#include <android/log.h>


#define  LOG_TAG    "main"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

using namespace cocos2d;

extern "C"
{

jint JNI_OnLoad(JavaVM *vm, void *reserved)
{
    JniHelper::setJavaVM(vm);

    return JNI_VERSION_1_4;
}

void Java_org_cocos2dx_lib_Cocos2dxRenderer_nativeInit(JNIEnv*  env, jobject thiz, jint w, jint h)
{
    CCUserDefault::sharedUserDefault()->setStringForKey("PACKEAGE","com/crimoon/common/");
    JavaCppHelper::getInstance()->setEnv(env);
    if (!CCDirector::sharedDirector()->getOpenGLView())
    {
        CCEGLView *view = CCEGLView::sharedOpenGLView();
        view->setFrameSize(w, h);
        
        AppDelegate *pAppDelegate = new AppDelegate();
        CCApplication::sharedApplication()->run();
   
    }
    else
    {
        ccDrawInit();
        ccGLInvalidateStateCache();
        
        CCShaderCache::sharedShaderCache()->reloadDefaultShaders();
        CCTextureCache::reloadAllTextures();
        CCNotificationCenter::sharedNotificationCenter()->postNotification(EVENT_COME_TO_FOREGROUND, NULL);
        CCDirector::sharedDirector()->setGLDefaultValues(); 
    }
}

void Java_com_crimoon_common_WeiboManager_loginSucess(JNIEnv* env, jobject thiz){

     CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
     scene->runAction(CCCallFunc::create(WeiboManager::getInstance(),callfunc_selector(WeiboManager::onLoginSuccess)));
     //WeiboManager::getInstance()->onLoginSuccess();
}
void Java_com_crimoon_common_WeiboManager_loginFail(JNIEnv* env, jobject thiz){

   CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
   scene->runAction(CCCallFunc::create(WeiboManager::getInstance(),callfunc_selector(WeiboManager::onLoginFail)));
   // WeiboManager::getInstance()->onLoginFail();
}

void Java_com_crimoon_common_WeiboManager_onLogOutSuccess(JNIEnv* env, jobject thiz){

    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    scene->runAction(CCCallFunc::create(WeiboManager::getInstance(),callfunc_selector(WeiboManager::onLogOutSuccess)));

}
void Java_com_crimoon_common_WeiboManager_shareSucess(JNIEnv* env, jobject thiz){

    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    scene->runAction(CCCallFunc::create(WeiboManager::getInstance(),callfunc_selector(WeiboManager::onShareSuccess)));
    //WeiboManager::getInstance()->onShareSuccess();

}
void Java_com_crimoon_common_WeiboManager_shareFail(JNIEnv* env, jobject thiz){

    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    scene->runAction(CCCallFunc::create(WeiboManager::getInstance(),callfunc_selector(WeiboManager::onShareFail)));
   // WeiboManager::getInstance()->onShareFail();
}
void Java_com_crimoon_common_WeiboManager_shareFailRepeate(JNIEnv* env, jobject thiz){

     CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
     scene->runAction(CCCallFunc::create(WeiboManager::getInstance(),callfunc_selector(WeiboManager::onShareFail2)));
}
//void Java_com_crimoon_common_CommonSdkManager_login(JNIEnv* env, jobject thiz,jstring userid,jstring tocken){
//
//	 CCArray *array = CCArray::create();
//
//     char *struserid = JavaCppHelper::getInstance()->jstringTostring(env,userid);
//     CCString *cstruserid = CCString::create(struserid);
//     array->addObject(cstruserid);
//
//     char *strtocken = JavaCppHelper::getInstance()->jstringTostring(env,tocken);
//     CCString* cstrtocken = CCString::create(strtocken);
//     array->addObject(cstrtocken);
//
//     CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
//     CCAction *action = CCCallFuncO::create(TK_MIManager::getInstance(),callfuncO_selector(TK_MIManager::login),array);
//     scene->runAction(action);
//
//     CC_SAFE_DELETE_ARRAY(struserid);
//     CC_SAFE_DELETE_ARRAY(strtocken);
//
//}
void Java_com_crimoon_common_CommonSdkManager_login(JNIEnv* env, jobject thiz,jstring str){

    char *strid = JavaCppHelper::getInstance()->jstringTostring(env,str);
    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    CCAction *action = CCCallFuncO::create(TK_SDKFactory::getInstance()->getSDKPlatform(),callfuncO_selector(TK_SDKManager::login),CCString::create(strid));
    scene->runAction(action);
    CC_SAFE_DELETE_ARRAY(strid);
}
void Java_com_crimoon_common_CommonSdkManager_payFail(JNIEnv* env, jobject thiz){

     CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
//     CCAction *action = CCCallFunc::create(TK_MIManager::getInstance(),callfunc_selector(TK_MIManager::onBuyFail));
     CCAction *action = CCCallFunc::create(TK_SDKFactory::getInstance()->getSDKPlatform(),callfunc_selector(TK_SDKManager::onBuyFail));
     scene->runAction(action);
}
void Java_com_crimoon_common_CommonSdkManager_payCancel(JNIEnv* env, jobject thiz){

     CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
//     CCAction *action = CCCallFunc::create(TK_MIManager::getInstance(),callfunc_selector(TK_MIManager::onBuyCancle));
     CCAction *action = CCCallFunc::create(TK_SDKFactory::getInstance()->getSDKPlatform(),callfunc_selector(TK_SDKManager::onBuyCancle));
     scene->runAction(action);
 }
void Java_com_crimoon_common_CommonSdkManager_logout(JNIEnv* env, jobject thiz){

    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    CCAction *action = CCCallFunc::create(TK_SDKFactory::getInstance()->getSDKPlatform(),callfunc_selector(TK_SDKManager::goLogout));
    scene->runAction(action);
 }
 void Java_com_crimoon_common_WeiboManager_enterBackGround(JNIEnv* env, jobject thiz){
   
      CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
      if(scene){

//    	  TK_MIManager::getInstance()->EnterBackGroud();
    	  TK_SDKFactory::getInstance()->getSDKPlatform()->EnterBackGroud();
        
     }

 
 }
 void Java_com_crimoon_common_WeiboManager_enterFront(JNIEnv* env, jobject thiz){

     CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
     if(scene){
    
//           TK_MIManager::getInstance()->EnterFront();
           TK_SDKFactory::getInstance()->getSDKPlatform()->EnterFront();

     }

 }
 jstring Java_com_crimoon_common_WeiboManager_sinaAppKey(JNIEnv* env, jobject thiz){

	 return  JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getSinaAppKey());

 }
 jstring Java_com_crimoon_common_WeiboManager_sinaAppSeceret(JNIEnv* env, jobject thiz){

 	return   JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getSinaAppSecret());
  }
 jstring Java_com_crimoon_common_WeiboManager_sinaURL(JNIEnv* env, jobject thiz){

 	return  JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getSinaUri());
  }
 jstring Java_com_crimoon_common_WeiboManager_sinaName(JNIEnv* env, jobject thiz){

 	return   JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getSinaName());
  }

 jstring Java_com_crimoon_common_WeiboManager_tcAppKey(JNIEnv* env, jobject thiz){

	return   JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getTcAppKey());
 }
 jstring Java_com_crimoon_common_WeiboManager_tcAppSeceret(JNIEnv* env, jobject thiz){

 	return   JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getTcAppSecret());
  }
 jstring Java_com_crimoon_common_WeiboManager_tcURL(JNIEnv* env, jobject thiz){

 	return   JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getTcUri());
  }
 jstring Java_com_crimoon_common_WeiboManager_tcName(JNIEnv* env, jobject thiz){

 	return   JavaCppHelper::getInstance()->stoJstring(env,WeiboManager::getInstance()->getTcName());
  }
 jstring Java_com_crimoon_common_WeChatSDkManager_getAppIdNative(JNIEnv* env, jobject thiz){

	return   JavaCppHelper::getInstance()->stoJstring(env,TK_WeChatManager::getInstance()->AppID());
 }
  jstring Java_net_crimoon_sgz15_mi_wxapi_WXEntryActivity_getAppIdNative(JNIEnv* env, jobject thiz){

 	return   JavaCppHelper::getInstance()->stoJstring(env,TK_WeChatManager::getInstance()->AppID());
  }
  void Java_com_crimoon_common_ProjectTK_weiXinSuccess(JNIEnv* env, jobject thiz){

  	  CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
  	  scene->runAction(CCCallFunc::create(TK_WeChatManager::getInstance(),callfunc_selector(TK_WeChatManager::onSendSuccess)));
  }
  void Java_com_crimoon_common_ProjectTK_weiXinFail(JNIEnv* env, jobject thiz){

  	  CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
  	  scene->runAction(CCCallFunc::create(TK_WeChatManager::getInstance(),callfunc_selector(TK_WeChatManager::onFail)));
  }

   //视频播放完毕
    void Java_com_crimoon_common_ProjectTK_palyover(JNIEnv* env, jobject thiz)
    {
           CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
           scene->runAction(CCCallFunc::create(JavaCppHelper::getInstance(),callfunc_selector(JavaCppHelper::palyOver)));

    }
}
