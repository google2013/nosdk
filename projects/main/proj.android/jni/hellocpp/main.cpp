#include "AppDelegate.h"
#include "cocos2d.h"
#include "CCEventType.h"
#include "platform/android/jni/JniHelper.h"
#include <jni.h>
#include <android/log.h>
#include "NS_SDKFactory.h"
#include "NS_SDKManager.h"

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
    if (!CCDirector::sharedDirector()->getOpenGLView())
    {
        CCEGLView *view = CCEGLView::sharedOpenGLView();
        view->setFrameSize(w, h);

        AppDelegate *pAppDelegate = new AppDelegate();
        CCApplication::sharedApplication()->run();
    }
    else
    {
        ccGLInvalidateStateCache();
        CCShaderCache::sharedShaderCache()->reloadDefaultShaders();
        ccDrawInit();
        CCTextureCache::reloadAllTextures();
        CCNotificationCenter::sharedNotificationCenter()->postNotification(EVENT_COME_TO_FOREGROUND, NULL);
        CCDirector::sharedDirector()->setGLDefaultValues(); 
    }
}
void Java_org_nosdk_main_CommonSDKManager_login(JNIEnv* env, jobject thiz,jstring prompt){
	char buf[1024];
	const char *str = env->GetStringUTFChars(env, prompt, 0);
//	char *strid = JavaCppHelper::getInstance()->jstringTostring(env,str);
    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    CCAction *action = CCCallFuncO::create(NS_SDKFactory::getInstance()->getSDKPlatform(),callfuncO_selector(NS_SDKManager::goNativeLogin),CCString::create(strid));
    scene->runAction(action);
	env->ReleaseStringUTFChars(env, prompt, str);
//    CC_SAFE_DELETE_ARRAY(strid);
}

void Java_org_nosdk_main_CommonSDKManager_logout(JNIEnv* env, jobject thiz){

    CCScene *scene = CCDirector::sharedDirector()->getRunningScene();
    CCAction *action = CCCallFunc::create(NS_SDKFactory::getInstance()->getSDKPlatform(),callfunc_selector(NS_SDKManager::goLogout));
    scene->runAction(action);
 }
}
