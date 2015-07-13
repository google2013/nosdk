//
//  NS_SDKPlatformAndroid.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/6/21.
//
//

#include <stdio.h>
#include "platform/android/jni/JniHelper.h"
#include "NS_SDKPlatform.h"
#include "Common/JavaHelper.h"
#include "LoginBackScene.h"

using namespace cocos2d;

NS_SDKPlatform::NS_SDKPlatform()
{
    
}

NS_SDKPlatform::~NS_SDKPlatform()
{
    
}

void NS_SDKPlatform::goLogin()
{
    callVoidFunc2( "goCommonLogin" );
}

void NS_SDKPlatform::goLogout()
{
    callVoidFunc( "goLogout" );
    logout();
}

void NS_SDKPlatform::setPay( const char* info )
{
    
//    std::string json = "tttt";
    callStringFunc( info , "setPay");
}

void NS_SDKPlatform::logout()
{
    CCDirector *pDirector = CCDirector::sharedDirector();
    
    //断开客户端连接，跳转到登录界面
    CCScene *pScene = LoginBackScene::scene();
    pDirector->replaceScene(pScene);
}

void NS_SDKPlatform::showCenter()
{
    callVoidFunc( "showCenter" );
}

void NS_SDKPlatform::showBBS()
{
    callVoidFunc( "showBBS" );
}

void NS_SDKPlatform::showFloatWindow()
{
    callVoidFunc( "showFloatWindow" );
}

void NS_SDKPlatform::hideFloatWindow()
{
    callVoidFunc( "hideFloatWindow" );
}

bool NS_SDKPlatform::isGuest()
{
    return callBoolFunc( "isGuest" );
}

bool NS_SDKPlatform::isLogined()
{
    return callBoolFunc( "isLogined" );
}

bool NS_SDKPlatform::isNormalSDK()
{
    return callBoolFunc( "isNormalSDK" );
}
bool NS_SDKPlatform::isHasLoginAccess()
{
    return callBoolFunc( "isHasLoginAccess" );
}

const char* NS_SDKPlatform::getProjectID()
{
    char buff[1024];
    callStringFunc2( "getProjectID", buff);
    CCLOG("getProjectID result is %s" , buff );
}

void NS_SDKPlatform::callStringFunc( const char* json , char* funcName )
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
    packageName += "CommonSDKManager";
    
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
//                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()Lorg/nosdk/main/CommonSDKManager;");
    jobject joCommonSDKManager;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
        joCommonSDKManager = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
        isHave=JniHelper::getMethodInfo(minfo,
                                        packageName.c_str() , "getSDKManager","()Lcom/nosdk/common/SDKManager;");
        if( isHave )
        {
            jobject joSDKManager = minfo.env->CallObjectMethod(joCommonSDKManager, minfo.methodID);
            isHave=JniHelper::getMethodInfo(minfo,
                                            "com/nosdk/common/SDKManager" , funcName ,"(Ljava/lang/String;)V");
            if( isHave )
            {
                CCLOG("CallStringMethod");
                minfo.env->CallObjectMethod(joSDKManager, minfo.methodID , minfo.env->NewStringUTF( json ) );
            }
        }
    }
//    CCLOG("callStringFUnc");
}


void NS_SDKPlatform::callStringFunc2( char* funcName , const char* result)
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
    packageName += "CommonSDKManager";
    
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
                                                 //                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()Lorg/nosdk/main/CommonSDKManager;");
    jobject joCommonSDKManager;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
        joCommonSDKManager = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
        isHave=JniHelper::getMethodInfo(minfo,
                                        packageName.c_str() , "getSDKManager","()Lcom/nosdk/common/SDKManager;");
        if( isHave )
        {
            jobject joSDKManager = minfo.env->CallObjectMethod(joCommonSDKManager, minfo.methodID);
            isHave=JniHelper::getMethodInfo(minfo,
                                            "com/nosdk/common/SDKManager" , funcName ,"()Ljava/lang/String;");
            if( isHave )
            {
                CCLOG("CallStringMethod");
                jstring jResult = (jstring)minfo.env->CallObjectMethod(joSDKManager, minfo.methodID );
                result = minfo.env->GetStringUTFChars((jstring) jResult, NULL);
//                printf("%s\n", str);
                // Clean up
//                env->ReleaseStringUTFChars(jstr, str);
            }
        }
    }
//    CCLOG("callStringFunc2");
}

void NS_SDKPlatform::callVoidFunc( char* funcName )
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
    packageName += "CommonSDKManager";
    
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
                                                 //                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()Lorg/nosdk/main/CommonSDKManager;");
    jobject joCommonSDKManager;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
        joCommonSDKManager = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
        isHave=JniHelper::getMethodInfo(minfo,
                                        packageName.c_str() , "getSDKManager","()Lcom/nosdk/common/SDKManager;");
        if( isHave )
        {
            jobject joSDKManager = minfo.env->CallObjectMethod(joCommonSDKManager, minfo.methodID);
            isHave=JniHelper::getMethodInfo(minfo,
                                            "com/nosdk/common/SDKManager" , funcName ,"()V");
            if( isHave )
            {
                CCLOG("CallVoidMethod");
                minfo.env->CallVoidMethod(joSDKManager, minfo.methodID );
            }
        }
    }
//        CCLOG("callVoidFunc");
}

void NS_SDKPlatform::callVoidFunc2( char* funcName )
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
    packageName += "CommonSDKManager";
    
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
                                                 //                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()Lorg/nosdk/main/CommonSDKManager;");
    jobject joCommonSDKManager;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
        joCommonSDKManager = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
        isHave=JniHelper::getMethodInfo(minfo,
                                        packageName.c_str() , funcName,"()V");
        
        if( isHave )
        {
            CCLOG("CallVoidMethod");
            minfo.env->CallVoidMethod(joCommonSDKManager, minfo.methodID );
        }
        
//        if( isHave )
//        {
//            jobject joSDKManager = minfo.env->CallObjectMethod(joCommonSDKManager, minfo.methodID);
//            isHave=JniHelper::getMethodInfo(minfo,
//                                            "com/nosdk/common/SDKManager" , funcName ,"()V");
//            if( isHave )
//            {
//                CCLOG("CallVoidMethod");
//                minfo.env->CallVoidMethod(joSDKManager, minfo.methodID );
//            }
//        }
    }
    //        CCLOG("callVoidFunc");
}

bool NS_SDKPlatform::callBoolFunc( char* funcName )
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
//    std::string packageName = "org/nosdk/main/";
    packageName += "CommonSDKManager";
    CCLOG("packageName is %s" , packageName.c_str() );
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
                                                 //                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()Lorg/nosdk/main/CommonSDKManager;");
//    JNIEnv* env = JavaHelper::getInstance()->getJNIEnv();

//    jclass strClass = env->FindClass("org/nosdk/main/CommonSDKManager");
//    jmethodID ctorID = env->GetStaticMethodID(strClass, "getInstance", "()Ljava/lang/Object;");
//    if( strClass )
//    {
//        jobject joCommonSDKManager = env->CallStaticObjectMethod( strClass , ctorID );
//            CCLOG("jmethodID is %s" , "fff");
//    }

//
//    CCLOG("packageName is %s" , packageName.c_str() );
    jobject joCommonSDKManager;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
            CCLOG("packageName is %s" , packageName.c_str() );
        joCommonSDKManager = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
        isHave=JniHelper::getMethodInfo(minfo,
                                        packageName.c_str() , "getSDKManager","()Lcom/nosdk/common/SDKManager;");
        if( isHave )
        {
            jobject joSDKManager = minfo.env->CallObjectMethod(joCommonSDKManager, minfo.methodID);
            isHave=JniHelper::getMethodInfo(minfo,
                                            "com/nosdk/common/SDKManager" , funcName ,"()Z");
            if( isHave )
            {
                CCLOG("CallBooleanMethod");
                jboolean result = minfo.env->CallBooleanMethod(joSDKManager, minfo.methodID );
                return result;
            }
        }
    }
    return false;
//    CCLOG("callBoolFunc");
//    return false;
}

int NS_SDKPlatform::callIntFunc( char* funcName )
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
    packageName += "CommonSDKManager";
    
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
                                                 //                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()Lorg/nosdk/main/CommonSDKManager;");
    jobject joCommonSDKManager;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
        joCommonSDKManager = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
        isHave=JniHelper::getMethodInfo(minfo,
                                        packageName.c_str() , "getSDKManager","()Lcom/nosdk/common/SDKManager;");
        if( isHave )
        {
            jobject joSDKManager = minfo.env->CallObjectMethod(joCommonSDKManager, minfo.methodID);
            isHave=JniHelper::getMethodInfo(minfo,
                                            "com/nosdk/common/SDKManager" , funcName ,"()I");
            if( isHave )
            {
                CCLOG("CallIntMethod");
                jint result = minfo.env->CallIntMethod(joSDKManager, minfo.methodID );
                return result;
            }
        }
    }
//    CCLOG("callIntFunc");
    return 0;
}