//
//  PayBackScene.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/6/22.
//
//


#include "PayBackScene.h"
#include "GameOverScene.h"
#include "SimpleAudioEngine.h"
#include "NS_SDKFactory.h"
#include "HelloWorldScene.h"
#include "cocos-ext.h"

using namespace cocos2d;
using namespace cocos2d::extension;

PayBackScene::~PayBackScene()
{
    
    // cpp don't need to call super dealloc
    // virtual destructor will do this
}

PayBackScene::PayBackScene()
{
}

CCScene* PayBackScene::scene()
{
    CCScene * scene = NULL;
    do
    {
        // 'scene' is an autorelease object
        scene = CCScene::create();
        CC_BREAK_IF(! scene);
        
        // 'layer' is an autorelease object
        PayBackScene *layer = PayBackScene::create();
        CC_BREAK_IF(! layer);
        
        // add layer as a child to scene
        scene->addChild(layer);
    } while (0);
    
    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool PayBackScene::init()
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
        float num = 5;
        int margin = 1;
        CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
        CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
        char buf[1024];
        for( int i = 0 ; i < num ; i++)
        {
            /* 正常状态下的按钮图片 */
            CCScale9Sprite* btnNormal = CCScale9Sprite::create("control_normal.png");
            
            /* 点击状态下的按钮图片 */
            CCScale9Sprite* btnDown = CCScale9Sprite::create("control_selected.png");
            
            sprintf( buf , "充值%d元" , ((int)num - 1 - i) * 10 + 1 );
            /* 按钮标题 */
            CCLabelTTF *title = CCLabelTTF::create( buf , "Marker Felt", 30);
            
            /* 按钮的大小会根据标题自动调整 */
            CCControlButton* controlBtn = CCControlButton::create(title, btnNormal);
            
            /* 设置按钮按下时的图片 */
            controlBtn->setBackgroundSpriteForState(btnDown, CCControlStateSelected);
            controlBtn->setPosition(ccp(origin.x + visibleSize.width / 2,
                                        origin.y + visibleSize.height / 2 + btnNormal->getContentSize().height * margin * num / 2 - btnNormal->getContentSize().height * margin * ( num - i - 1 ) ));
            controlBtn->setTag( ((int)num - 1 - i) * 10 + 1 );
            controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(PayBackScene::menuPayCallback), CCControlEventTouchDown );
            
            this->addChild(controlBtn);
            
        }
        
        /* 正常状态下的按钮图片 */
        CCScale9Sprite* btnNormal = CCScale9Sprite::create("control_normal.png");
        
        /* 点击状态下的按钮图片 */
        CCScale9Sprite* btnDown = CCScale9Sprite::create("control_selected.png");
        
        /* 按钮标题 */
        CCLabelTTF *title = CCLabelTTF::create( "返回" , "Marker Felt", 30);
        
        /* 按钮的大小会根据标题自动调整 */
        CCControlButton* controlBtn = CCControlButton::create(title, btnNormal);
        
        /* 设置按钮按下时的图片 */
        controlBtn->setBackgroundSpriteForState(btnDown, CCControlStateSelected);
        controlBtn->setPosition(ccp(origin.x + visibleSize.width / 2,
                                    origin.y + visibleSize.height / 2 + btnNormal->getContentSize().height * margin * num / 2 - btnNormal->getContentSize().height * margin * ( num ) ));
//        controlBtn->setTag( ((int)num - 1 - i) * 10 + 1 );
        controlBtn->addTargetWithActionForControlEvents( this , cccontrol_selector(PayBackScene::menuBackCallback), CCControlEventTouchDown );
        
        this->addChild(controlBtn);
        
        bRet = true;
    } while (0);
    
    return bRet;
}

void PayBackScene::menuPayCallback(cocos2d::CCObject* pSender, cocos2d::extension::CCControlEvent event)
{
//    char buf[1024];
    CCControlButton* btn = (CCControlButton*)pSender;
    if( btn )
    {
        NS_SDKFactory::getInstance()->getSDKPlatform()->setPay( btn->getTag() );
    }
}

void PayBackScene::menuBackCallback(cocos2d::CCObject* pSender)
{
    //    char buf[1024];
    CCDirector *pDirector = CCDirector::sharedDirector();
    CCScene *pScene = HelloWorld::scene();
    // run
    pDirector->replaceScene(pScene);
}
