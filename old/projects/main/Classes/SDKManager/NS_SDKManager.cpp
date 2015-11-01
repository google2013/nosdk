//
//  NS_SDKManager.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/6/21.
//
//

#include "NS_SDKManager.h"
#include "HelloWorldScene.h"
#include "cocos-ext.h"

#define LOCAL_SERVER "192.168.0.1"

NS_SDKManager::NS_SDKManager()
{
    
}

NS_SDKManager::~NS_SDKManager()
{
    
}

void NS_SDKManager::goNativeLogin( const char* str )
{
    char buff[1024];
    
    //向服务器验证平台信息
    //...
    sprintf( buff , "%s%s" , LOCAL_SERVER , str );
//    CCLOG( str );
    
    
    bool isLoginSuccess = true;
    //登录成功，切换界面
    if( isLoginSuccess )
    {
        //处理服务器返回信息.
        onLoginSuccess();
        
        cocos2d::CCDirector *pDirector = cocos2d::CCDirector::sharedDirector();
        cocos2d::CCScene *pScene = HelloWorld::scene();
        // run
        pDirector->replaceScene(pScene);
    }
}

void NS_SDKManager::getPayInfo(int type , char* outStr )
{
    char buff[1024];
    
    //读取客户端数据，将type换为充值信息，包括首冲双倍之类的
    float amount = type * 10;
    
    
    Json::Value json_info;      // 临时对象，供如下代码使用
    json_info["mount"] = Json::Value(amount);
    
    //生成订单
    sprintf(buff , "%fA%ld" , amount , time(NULL) );
    json_info["order"] = Json::Value( buff );

    //生成订单验证信息
    json_info["payinfo"] = Json::Value( buff );
    
    sprintf( outStr , "%s" , json_info.toStyledString().c_str() );
//    outStr = json_info.toStyledString().c_str();
}

void NS_SDKManager::onLoginSuccess()
{
    CCLOG( "onLoginSuccess" );
}

void NS_SDKManager::onPaySuccess()
{
    
}
