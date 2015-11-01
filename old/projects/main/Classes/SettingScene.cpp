//
//  SettingScene.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/6/22.
//
//
#include "SettingScene.h"
#include "GameOverScene.h"
#include "SimpleAudioEngine.h"
#include "NS_SDKFactory.h"
#include "HelloWorldScene.h"
#include "cocos-ext.h"

using namespace cocos2d;
using namespace cocos2d::extension;

SettingScene::~SettingScene()
{
    
    // cpp don't need to call super dealloc
    // virtual destructor will do this
}

SettingScene::SettingScene()
{
    
}

CCScene* SettingScene::scene()
{
    CCScene * scene = NULL;
    do
    {
        // 'scene' is an autorelease object
        scene = CCScene::create();
        CC_BREAK_IF(! scene);
        
        // 'layer' is an autorelease object
        SettingScene *layer = SettingScene::create();
        CC_BREAK_IF(! layer);
        
        // add layer as a child to scene
        scene->addChild(layer);
    } while (0);
    
    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool SettingScene::init()
{
    bool bRet = false;
    do
    {
        //////////////////////////////////////////////////////////////////////////
        // super init first
        //////////////////////////////////////////////////////////////////////////
        
        CC_BREAK_IF(! CCLayerColor::initWithColor( ccc4(255,255,255,255) ) );
        
        //////////////////////////////////////////////////////////////////////////
        // add your codes below...
        //////////////////////////////////////////////////////////////////////////
        
        // 1. Add a menu item with "X" image, which is clicked to quit the program.
        
        // Create a "close" menu item with close icon, it's an auto release object.
        float num = 6;
        int margin = 1;
        CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
        CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
        
        

        for( int i = 0 ; i < num ; i++)
        {
            /* 正常状态下的按钮图片 */
            CCScale9Sprite* btnNormal = CCScale9Sprite::create("control_normal.png");
            
            /* 点击状态下的按钮图片 */
            CCScale9Sprite* btnDown = CCScale9Sprite::create("control_selected.png");
            
            std::string titleStr = "";
            if( i == 5 )
            {
                titleStr = "个人中心";
            }
            else if( i == 4 )
            {
                titleStr = "显示浮窗";
            }
            else if( i == 3 )
            {
                titleStr = "隐藏浮窗";
            }
            else if( i == 2 )
            {
                titleStr = "注销";
            }
            else if( i == 1 )
            {
                titleStr = "登出";
            }
            else if( i == 0 )
            {
                titleStr = "返回";
            }
            /* 按钮标题 */
            CCLabelTTF *title = CCLabelTTF::create( titleStr.c_str() , "Marker Felt", 30);
            
            /* 按钮的大小会根据标题自动调整 */
            CCControlButton* controlBtn = CCControlButton::create(title, btnNormal);
            
            /* 设置按钮按下时的图片 */
            controlBtn->setBackgroundSpriteForState(btnDown, CCControlStateSelected);
            controlBtn->setPosition(ccp(origin.x + visibleSize.width / 2,
                                        origin.y + visibleSize.height / 2 + btnNormal->getContentSize().height * margin * num / 2 - btnNormal->getContentSize().height * margin * ( num - i ) ));
            
            if( i == 5 )
            {
//                titleStr = "个人中心";
                controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(SettingScene::menuShowCenterCallback), CCControlEventTouchDown );
            }
            else if( i == 4 )
            {
                //                titleStr = "注销";
                controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(SettingScene::menuShowFloatCallback), CCControlEventTouchDown );
            }
            else if( i == 3 )
            {
                //                titleStr = "注销";
                controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(SettingScene::menuHideFloatCallback), CCControlEventTouchDown );
            }
            else if( i == 2 )
            {
//                titleStr = "注销";
                controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(SettingScene::menuLogoutCallback), CCControlEventTouchDown );
            }
            else if( i == 1 )
            {
//                titleStr = "登出";
                controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(SettingScene::menuExitCallback), CCControlEventTouchDown );
            }
            else if( i == 0 )
            {
                //                titleStr = "登出";
                controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(SettingScene::menuBackCallback), CCControlEventTouchDown );
            }
            
            this->addChild(controlBtn);
            
        }
        bRet = true;
    } while (0);
    
    return bRet;
}

void SettingScene::menuLogoutCallback(cocos2d::CCObject* pSender)
{
    if( NS_SDKFactory::getInstance()->getSDKPlatform()->isLogined() )
    {
        NS_SDKFactory::getInstance()->getSDKPlatform()->goLogout();
    }
}

void SettingScene::menuExitCallback(cocos2d::CCObject* pSender)
{
    // "close" menu item clicked
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WINRT) || (CC_TARGET_PLATFORM == CC_PLATFORM_WP8)
    CCMessageBox("You pressed the close button. Windows Store Apps do not implement a close button.", "Alert");
#else
    CCDirector::sharedDirector()->end();
#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
#endif
}

void SettingScene::menuShowCenterCallback(cocos2d::CCObject* pSender)
{
    NS_SDKFactory::getInstance()->getSDKPlatform()->showCenter();
}

void SettingScene::menuBackCallback(cocos2d::CCObject* pSender)
{
    CCDirector *pDirector = CCDirector::sharedDirector();
    CCScene *pScene = HelloWorld::scene();
    // run
    pDirector->replaceScene(pScene);
}

void SettingScene::menuShowFloatCallback(cocos2d::CCObject* pSender)
{
    NS_SDKFactory::getInstance()->getSDKPlatform()->showFloatWindow();
}

void SettingScene::menuHideFloatCallback(cocos2d::CCObject* pSender)
{
    NS_SDKFactory::getInstance()->getSDKPlatform()->hideFloatWindow();
}
