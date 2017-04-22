#ifndef __JAVAHELPER_H__
#define __JAVAHELPER_H__

#include <jni.h>
#include <iostream>
#include <string>

using namespace std;

class JavaHelper
{
public:
    JavaHelper();
    ~JavaHelper();
    
    static JavaHelper* getInstance();
    void destroyInstance();
    
    void setJNIEnv(JNIEnv* env);
    JNIEnv* getJNIEnv();
    
    void setPackageName(const char* name);

    void callStringFunction1(const char* functionName, const char* data);	//void xxx(string)
    const char* callStringFunction2(const char* functionName);	            //string xxx()
    void callVoidFunction(const char* functionName);						//void xxx()
    bool callBoolFunction(const char* functionName);						//bool xxx();
    int callIntFunction(const char* functionName);							//int xxx();

    string jstring2string(jstring jstr);
private:
    JNIEnv* jniEnv;
    string packageName;
};

#endif