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
        
        CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
        CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
        
        CCMenuItemImage *pCloseItem = CCMenuItemImage::create(
                                                              "CloseNormal.png",
                                                              "CloseSelected.png",
                                                              this,
                                                              menu_selector(PayBackScene::menuPayCallback));
        CC_BREAK_IF(! pCloseItem);
        
        // Place the menu item bottom-right conner.
        
        pCloseItem->setPosition(ccp(origin.x + visibleSize.width / 2 - pCloseItem->getContentSize().width/2,
                                    origin.y + visibleSize.height / 2 - pCloseItem->getContentSize().height/2));
        
        // Create a menu with the "close" menu item, it's an auto release object.
        CCMenu* pMenu = CCMenu::create(pCloseItem, NULL);
        pMenu->setPosition(CCPointZero);
        CC_BREAK_IF(! pMenu);
        
        // Add the menu to PayBackScene layer as a child layer.
        this->addChild(pMenu, 1);
        
        /////////////////////////////
        // 2. add your codes below...
        //        CCSprite *player = CCSprite::create("Player.png", CCRectMake(0, 0, 27, 40) );
        //
        //        player->setPosition( ccp(origin.x + player->getContentSize().width/2,
        //                                 origin.y + visibleSize.height/2) );
        //        this->addChild(player);
        //
        //        this->schedule( schedule_selector(PayBackScene::gameLogic), 1.0 );
        //
        //        this->setTouchEnabled(true);
        //
        //        _targets = new CCArray;
        //        _projectiles = new CCArray;
        //
        //        // use updateGame instead of update, otherwise it will conflit with SelectorProtocol::update
        //        // see http://www.cocos2d-x.org/boards/6/topics/1478
        //        this->schedule( schedule_selector(PayBackScene::updateGame) );
        //
        //        CocosDenshion::SimpleAudioEngine::sharedEngine()->playBackgroundMusic("background-music-aac.wav", true);
        
        bRet = true;
    } while (0);
    
    return bRet;
}

void PayBackScene::menuPayCallback(CCObject* pSender)
{
//    char buf[1024];
    NS_SDKFactory::getInstance()->getSDKPlatform()->setPay( 1 );
}
