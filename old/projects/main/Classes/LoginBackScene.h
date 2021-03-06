//
//  LoginBackScene.h
//  SimpleGame
//
//  Created by nottvlike on 15/6/22.
//
//
#pragma once

#include "cocos2d.h"
#include "SimpleAudioEngine.h"

class LoginBackScene : public cocos2d::CCLayerColor
{
public:
    LoginBackScene();
    ~LoginBackScene();
    
    // Here's a difference. Method 'init' in cocos2d-x returns bool,
    // instead of returning 'id' in cocos2d-iphone
    virtual bool init();
    
    // there's no 'id' in cpp, so we recommand to return the exactly class pointer
    static cocos2d::CCScene* scene();
    
    // a selector callback
    virtual void menuLoginCallback(cocos2d::CCObject* pSender);
    
    // implement the "static node()" method manually
    CREATE_FUNC(LoginBackScene);
    
    
    
};