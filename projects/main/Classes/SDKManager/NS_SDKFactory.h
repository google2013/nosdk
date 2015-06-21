//
//  NS_SDKFactory.h
//  SimpleGame
//
//  Created by nottvlike on 15/6/21.
//
//
#pragma once

#include "cocos2d.h"
#include "NS_SDKManager.h"

class NS_SDKFactory
{
public:
    NS_SDKFactory();
    ~NS_SDKFactory();
    
    static NS_SDKFactory* getInstance();
    NS_SDKManager* getSDKPlatform();
    void destroyInstance();
private:
    NS_SDKManager* m_pSDKPlatform;
};