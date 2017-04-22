/*
 * Copyright (C) 2011 Dmitry Skiba
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "AndroidClasses.h"
#include "SDKManager.h"
#include "JavaHelper.h"

#define LOG_TAG "Sample"

///////////////////////////////////////////////////////////////////// MyCustomView

class MyCustomView;
typedef java::ObjectPointer<MyCustomView> PMyCustomView;

class MyCustomView: public LiveView {
public:
    static bool SetNativeInstance(const char* tag,const jni::LObject&);
    color_t GetFillColor() const;
    void SetFillColor(color_t color);
private:
    MyCustomView(const jni::LObject&);
    virtual void Construct(PContext context,PAttributeSet attrs);
    virtual void OnDraw(PCanvas canvas);
private:
    color_t m_fillColor;
};


bool MyCustomView::SetNativeInstance(const char* tag,const jni::LObject& object) {
    if (strcmp(tag,"MyCustomView")) {
        return false;
    }
    new MyCustomView(object);
    return true;
}

MyCustomView::MyCustomView(const jni::LObject& object):
LiveView(object),
m_fillColor(Color::LTGRAY)
{
}

void MyCustomView::Construct(PContext context,PAttributeSet attrs) {
    Log::Info(LOG_TAG,"Constructed MyCustomView");
}

void MyCustomView::OnDraw(PCanvas canvas) {
    //LiveView::OnDraw(canvas);
    canvas->DrawColor(m_fillColor);
}

color_t MyCustomView::GetFillColor() const {
    return m_fillColor;
}

void MyCustomView::SetFillColor(color_t color) {
    m_fillColor=color;
    Invalidate();
}

///////////////////////////////////////////////////////////////////// MainActivity

class MainActivity: public Activity {
public:
    static bool SetNativeInstance(const char* className,const jni::LObject& object);
    virtual void OnCreate(const jni::LObject& state);
private:
    MainActivity(const jni::LObject& object);
    void init(PView view);
    void login(PView view);
    void logout(PView view);
    void pay(PView view);
    void exit(PView view);
    void getChannel(PView view);
    void sendExtraData(PView view);
};

bool MainActivity::SetNativeInstance(const char* className,const jni::LObject& object) {
    if (strcmp(className,"com.sdk.sample.MainActivity")) {
        return false;
    }
    new MainActivity(object);
    // There is no leak since java class has a reference to
    //  the native instance. Destructor will be called
    //  when finalizer is run on java class.
    return true;
}

MainActivity::MainActivity(const jni::LObject& object):
Activity(object)
{
}

void MainActivity::OnCreate(const jni::LObject& bundle) {
    Activity::OnCreate(bundle);
    SetContentView(R::GetLayout("main"));
    FindViewById(R::GetID("init"))->SetOnClickListener(*this,&MainActivity::init);
    FindViewById(R::GetID("login"))->SetOnClickListener(*this,&MainActivity::login);
    FindViewById(R::GetID("logout"))->SetOnClickListener(*this,&MainActivity::logout);
    FindViewById(R::GetID("pay"))->SetOnClickListener(*this,&MainActivity::pay);
    FindViewById(R::GetID("exit"))->SetOnClickListener(*this,&MainActivity::exit);
    FindViewById(R::GetID("getChannel"))->SetOnClickListener(*this,&MainActivity::getChannel);
    FindViewById(R::GetID("sendExtraData"))->SetOnClickListener(*this,&MainActivity::sendExtraData);
}

void MainActivity::init(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->init("");
}

void MainActivity::login(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->login("");
}

void MainActivity::logout(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->logout("");
}

void MainActivity::pay(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->pay("");
}

void MainActivity::exit(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->exit("");
}

void MainActivity::getChannel(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->getChannel();
}

void MainActivity::sendExtraData(PView) {
    // Since MyCustomView is backed with the same Java class as LiveView
    //  this cast really checks that view is-a LiveView. However, it is
    //  better than nothing and probably best you can get without hussle
    //  of creating Java subclass for each native subclass of LiveView.
    SDKManager::getInstance()->sendExtraData("");
}

///////////////////////////////////////////////////////////////////// SetNativeInstance

void SetNativeInstance(const char* tag,const jni::LObject& object) {
    MainActivity::SetNativeInstance(tag,object) ||
    MyCustomView::SetNativeInstance(tag,object);
}

///////////////////////////////////////////////////////////////////// JNI_OnLoad

extern "C" jint JNI_OnLoad(JavaVM* vm,void* reserved) {
    jni::Initialize(vm);
    Activity::RegisterCallbacks();
    R::Initialize("com.sdk.sample");
    return JNI_VERSION_1_6;
}
