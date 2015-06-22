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
    void setPay( int type );
    void logout();
    
    void showCenter();
    void showBBS();
    
    bool isGuest();
    bool isLogined();
    
    bool isNormalSDK();
    bool isHasLoginAccess();
    
    const char* getProjectID();
    
private:
    void callStringFunc( char* json );
    void callVoidFunc( char* json );
    void callBoolFunc( char* json );
    void callIntFunc( char* json );
};