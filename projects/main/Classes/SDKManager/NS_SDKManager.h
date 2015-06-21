//
//  NS_SDKManager.h
//  SimpleGame
//
//  Created by nottvlike on 15/6/21.
//
//
#pragma once

#include "cocos2d.h"
#include "NS_SDKPlatform.h"

class NS_SDKManager : public NS_SDKPlatform
{
public:
    NS_SDKManager();
    ~NS_SDKManager();
    
    void goNativeLogin( const char* str );
    void getLoginInfo(const char* str);
    void getPayInfo(const char* str);
    
    void onLoginSuccess();
    void onPaySuccess();
private:
};