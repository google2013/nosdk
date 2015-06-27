//
//  JavaHelper.cpp
//  SimpleGame
//
//  Created by nottvlike on 15/6/23.
//
//

#include "JavaHelper.h"

static JavaHelper* instance = NULL;
JavaHelper::JavaHelper()
:m_pJNIEnv(NULL)
{
    
}

JavaHelper::~JavaHelper()
{
    
}

JavaHelper* JavaHelper::getInstance()
{
    if( instance == NULL )
    {
        instance = new JavaHelper();
    }
    return instance;
}

void JavaHelper::destroyInstance()
{
    if( instance != NULL )
    {
        delete instance;
        instance = NULL;
    }
}

void JavaHelper::setJNIEnv( JNIEnv* env )
{
    m_pJNIEnv = env;
}

JNIEnv* JavaHelper::getJNIEnv()
{
    return m_pJNIEnv;
}