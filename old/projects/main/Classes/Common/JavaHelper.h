//
//  JavaHelper.h
//  SimpleGame
//
//  Created by nottvlike on 15/6/23.
//
//
#include <jni.h>
#include "cocos2d.h"

class JavaHelper
{
public:
    JavaHelper();
    ~JavaHelper();
    
    static JavaHelper* getInstance();
    void destroyInstance();
    
    void setJNIEnv( JNIEnv* env );
    JNIEnv* getJNIEnv();
    
private:
    JNIEnv* m_pJNIEnv;
};