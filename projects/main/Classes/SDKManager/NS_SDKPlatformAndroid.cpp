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

using namespace cocos2d;

NS_SDKPlatform::NS_SDKPlatform()
{
    
}

NS_SDKPlatform::~NS_SDKPlatform()
{
    
}

void NS_SDKPlatform::goLogin()
{
    
}

void NS_SDKPlatform::goLogout()
{
    
}

void NS_SDKPlatform::setPay( int type )
{
    
}

void NS_SDKPlatform::logout()
{
    
}

void NS_SDKPlatform::showCenter()
{
    
}

void NS_SDKPlatform::showBBS()
{
    
}

void NS_SDKPlatform::showFloatWindow()
{
    
}

void NS_SDKPlatform::hideFloatWindow()
{
    
}

bool NS_SDKPlatform::isGuest()
{
    
}

bool NS_SDKPlatform::isLogined()
{
    
}

bool NS_SDKPlatform::isNormalSDK()
{
    
}
bool NS_SDKPlatform::isHasLoginAccess()
{
    
}

const char* NS_SDKPlatform::getProjectID()
{
    
}

void NS_SDKPlatform::callStringFunc( char* json , char* funcName )
{
    std::string packageName = CCUserDefault::sharedUserDefault()->getStringForKey("PACKEAGE");
    packageName += "CommonSDKManager";
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,                     /*JniMethodInfo的引用*/
                                                 packageName.c_str(),       /*类的路径*/
                                                 "getInstance",             /*函数名*/
//                                                 "()Ljava/lang/Object;"); /*函数类型简写*/
                                                 "()V");
    jobject activityObj;
    if (isHave)
    {
        //CallStaticObjectMethod调用java函数，并把返回值赋值给activityObj
        activityObj = minfo.env->CallStaticObjectMethod(minfo.classID, minfo.methodID);
    }
}

void NS_SDKPlatform::callVoidFunc( char* json , char* funcName )
{
    
}

void NS_SDKPlatform::callBoolFunc( char* json , char* funcName )
{
    
}

void NS_SDKPlatform::callIntFunc( char* json , char* funcName )
{
    
}