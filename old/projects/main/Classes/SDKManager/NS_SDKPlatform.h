//
//  NS_SDKPlatform.h
//  SimpleGame
//
//  Created by nottvlike on 15/6/21.
//
//
#pragma once

#include "cocos2d.h"

class NS_SDKPlatform : public cocos2d::CCObject
{
public:
    NS_SDKPlatform();
    ~NS_SDKPlatform();
    
    void goLogin();
    void goLogout();
    void setPay( const char* info );
    void logout();
    
    void showCenter();
    void showBBS();
    void showFloatWindow();
    void hideFloatWindow();
    
    bool isGuest();
    bool isLogined();
    
    bool isNormalSDK();
    bool isHasLoginAccess();
    
    const char* getProjectID();
    
private:
    void callStringFunc( const char* json , char* funcName );
    void callStringFunc2( char* funcName , const char* result);
    void callVoidFunc(  char* funcName );
    void callVoidFunc2(  char* funcName );
    bool callBoolFunc(  char* funcName );
    int callIntFunc( char* funcName );
};