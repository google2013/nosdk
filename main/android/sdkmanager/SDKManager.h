#ifndef __SDKMANAGER_H__
#define __SDKMANAGER_H__

#include <iostream>
#include <string>

using namespace std;

enum LoginResultType
{
	Success = 0,	//登陆成功
	Failed = 1,		//登陆失败
	Exit = 2		//取消登陆
};

//统计信息
enum ExtraDataType
{
	EnterGame = 1,
	CreateRole = 2,
	LevelUp = 4,
	QuitGame = 8
};

class SDKManager
{
public:
	SDKManager();
	~SDKManager();

	static SDKManager* getInstance();

	void init(string param);
	
	void login(string param);
	void switchAccount(string param);

	void logout(string param);
	
	void pay(string param);
	
	void exit(string param);

	void sendExtraData(string data);

	const char* getChannel();

	void onLogout(string result);
	void onLoginResult(string result);
	void onPayResult(string result);

private:
	string packageName;
	string channelLabel;
};

#endif