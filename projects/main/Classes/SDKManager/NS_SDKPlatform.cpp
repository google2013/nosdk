//
//  NS_SDKPlatform.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/7/13.
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
    
}

void NS_SDKPlatform::goLogout()
{
    logout();
}

void NS_SDKPlatform::setPay( const char* info )
{
    
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
    return false;
}

bool NS_SDKPlatform::isLogined()
{
    return false;
}

bool NS_SDKPlatform::isNormalSDK()
{
    return false;
}
bool NS_SDKPlatform::isHasLoginAccess()
{
    return false;
}

const char* NS_SDKPlatform::getProjectID()
{
    return "";
}

void NS_SDKPlatform::callStringFunc( const char* json , char* funcName )
{

}


void NS_SDKPlatform::callStringFunc2( char* funcName , const char* result)
{

}

void NS_SDKPlatform::callVoidFunc( char* funcName )
{

}

void NS_SDKPlatform::callVoidFunc2( char* funcName )
{
}

bool NS_SDKPlatform::callBoolFunc( char* funcName )
{
    return false;
}

int NS_SDKPlatform::callIntFunc( char* funcName )
{

    return 0;
}