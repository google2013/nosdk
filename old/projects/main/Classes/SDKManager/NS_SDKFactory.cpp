//
//  NS_SDKFactory.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/6/21.
//
//
#include "NS_SDKFactory.h"

static NS_SDKFactory* instance = NULL;

NS_SDKFactory::NS_SDKFactory()
:m_pSDKPlatform(NULL)
{
    m_pSDKPlatform = new NS_SDKManager();
}

NS_SDKFactory::~NS_SDKFactory()
{
    if( m_pSDKPlatform != NULL )
    {
        delete m_pSDKPlatform;
        m_pSDKPlatform = NULL;
    }
}

NS_SDKFactory* NS_SDKFactory::getInstance()
{
    if( instance == NULL)
    {
        instance = new NS_SDKFactory();
    }
    return instance;
}

void NS_SDKFactory::destroyInstance()
{
    if( instance != NULL)
    {
        delete instance;
        instance = NULL;
    }
}

NS_SDKManager* NS_SDKFactory::getSDKPlatform()
{
    if( m_pSDKPlatform == NULL )
    {
        m_pSDKPlatform = new NS_SDKManager();
    }
    return m_pSDKPlatform;
}
