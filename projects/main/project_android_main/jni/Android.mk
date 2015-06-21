LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS += -DPLATFORM_XX=1
LOCAL_CFLAGS += -DNAME_SGZ15=1
LOCAL_MODULE := game_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp


LOCAL_SRC_FILES := hellocpp/main.cpp \
libiconv.a \
../../Classes/AppDelegate.cpp\
../../Classes/Common/aescrypt/TK_Aescrypt.cpp\
../../Classes/Common/ByteOrder.cpp\
../../Classes/Common/CCControlButtonWithFX/CCControlButtonWithFX.cpp\
../../Classes/Common/CCControlButtonWithFX/CCControlButtonWithFXLoader.cpp\
../../Classes/Common/CCLabelFX/CCLabelFX.cpp\
../../Classes/Common/CodeHelper/CodeGenerator.cpp\
../../Classes/Common/JavaCppHelper.cpp\
../../Classes/Common/LogSystem.cpp\
../../Classes/Common/OCandCppHelperAndroid.cpp\
../../Classes/Common/ParamListParser.cpp\
../../Classes/Common/Queue.cpp\
../../Classes/Common/RbfParser.cpp\
../../Classes/Common/RichBox.cpp\
../../Classes/Common/SerBuffer.cpp\
../../Classes/Common/SocketClient.cpp\
../../Classes/Common/SoundDelegate.cpp\
../../Classes/Common/StringConvert.cpp\
../../Classes/Common/TK_DirtyWordsCheck.cpp\
../../Classes/Common/TK_TableCell.cpp\
../../Classes/Common/TK_XmlReader.cpp\
../../Classes/Common/TK_TextMgr.cpp\
../../Classes/Common/VersionControl.cpp\
../../Classes/jsoncpp/src/lib_json/json_reader.cpp\
../../Classes/jsoncpp/src/lib_json/json_value.cpp\
../../Classes/jsoncpp/src/lib_json/json_writer.cpp\
../../Classes/WeChatSdk/TK_WeChatManager.cpp\
../../Classes/SinaWeibo/TK_LayerWeiboBand.cpp\
../../Classes/SinaWeibo/TK_LayerWeiboShare.cpp\
../../Classes/SinaWeibo/WeiboManagerAndroid.cpp\
../../Classes/iap/PaymentViewandroid.cpp\
../../Classes/Common/md5.c\
../../Classes/Common/Util.cpp\
../../Classes/Game/TK_AchievementManager.cpp \
../../Classes/Game/TK_ActionData.cpp \
../../Classes/Game/TK_ActionManager.cpp \
../../Classes/Game/TK_ActivityData.cpp \
../../Classes/Game/TK_ActivityTemplate.cpp \
../../Classes/Game/TK_Audio.cpp \
../../Classes/Game/TK_AudioAction.cpp \
../../Classes/Game/TK_CCBUIMemAutoBinding.cpp \
../../Classes/Game/TK_CallGeneralCard.cpp \
../../Classes/Game/TK_CallGeneralData.cpp \
../../Classes/Game/TK_CallGeneralMainView.cpp \
../../Classes/Game/TK_ChapterData.cpp \
../../Classes/Game/TK_ChartsData.cpp \
../../Classes/Game/TK_ChatData.cpp \
../../Classes/Game/TK_CircleLayerTest.cpp \
../../Classes/Game/TK_CirleCell.cpp \
../../Classes/Game/TK_DataFriend.cpp \
../../Classes/Game/TK_DataPVP.cpp \
../../Classes/Game/TK_DataShowPic.cpp \
../../Classes/Game/TK_DataTeamCompare.cpp \
../../Classes/Game/TK_DateManager.cpp \
../../Classes/Game/TK_DescriptionCommon.cpp \
../../Classes/Game/TK_DescriptionCommonTable.cpp \
../../Classes/Game/TK_DescriptionCommonUnit.cpp \
../../Classes/Game/TK_DiviningData.cpp \
../../Classes/Game/TK_DragonTreasureData.cpp \
../../Classes/Game/TK_EmperorData.cpp \
../../Classes/Game/TK_FactorySystemData.cpp \
../../Classes/Game/TK_FireCrackerManager.cpp \
../../Classes/Game/TK_GameInfo.cpp \
../../Classes/Game/TK_GameWndManager.cpp \
../../Classes/Game/TK_GiftMoneyActivityUint.cpp \
../../Classes/Game/TK_GiftMoneyManager.cpp \
../../Classes/Game/TK_GodData.cpp \
../../Classes/Game/TK_GoodsIcon.cpp \
../../Classes/Game/TK_GuiKingStoreHouseItems.cpp \
../../Classes/Game/TK_GuildData.cpp \
../../Classes/Game/TK_ItemCard.cpp \
../../Classes/Game/TK_ItemData.cpp \
../../Classes/Game/TK_KingData.cpp \
../../Classes/Game/TK_KingTreasuresEventManager.cpp \
../../Classes/Game/TK_LayeAdventureCollectReward.cpp \
../../Classes/Game/TK_LayerAccoutManager.cpp \
../../Classes/Game/TK_LayerAchievement.cpp \
../../Classes/Game/TK_LayerActivityEntry.cpp \
../../Classes/Game/TK_LayerActivityList.cpp \
../../Classes/Game/TK_LayerAdvance.cpp \
../../Classes/Game/TK_LayerAdventure.cpp \
../../Classes/Game/TK_LayerAdventureReward.cpp \
../../Classes/Game/TK_LayerAidGerBatBox.cpp \
../../Classes/Game/TK_LayerAidGerBox.cpp \
../../Classes/Game/TK_LayerAllEvent.cpp \
../../Classes/Game/TK_LayerAutoMatic.cpp \
../../Classes/Game/TK_LayerBlessTenTimes.cpp \
../../Classes/Game/TK_LayerBoxOpenChoose.cpp \
../../Classes/Game/TK_LayerChallengeGodMain.cpp \
../../Classes/Game/TK_LayerChallengeGodSweep.cpp \
../../Classes/Game/TK_LayerChangban.cpp \
../../Classes/Game/TK_LayerChangbanReward.cpp \
../../Classes/Game/TK_LayerCharts.cpp \
../../Classes/Game/TK_LayerChatBoard.cpp \
../../Classes/Game/TK_LayerChatList.cpp \
../../Classes/Game/TK_LayerChatMain.cpp \
../../Classes/Game/TK_LayerCirCleChange.cpp \
../../Classes/Game/TK_LayerCoalitionScience.cpp \
../../Classes/Game/TK_LayerCoalitionScienceUp.cpp \
../../Classes/Game/TK_LayerCommonHelp.cpp \
../../Classes/Game/TK_LayerComposeScrollView.cpp \
../../Classes/Game/TK_LayerComposeShow.cpp \
../../Classes/Game/TK_LayerCreatGuild.cpp \
../../Classes/Game/TK_LayerDailyGuildItem.cpp \
../../Classes/Game/TK_LayerDailyGuildUnit.cpp \
../../Classes/Game/TK_LayerDailyPVPUnit.cpp \
../../Classes/Game/TK_LayerDailyReward.cpp \
../../Classes/Game/TK_LayerDailyRewardUnit.cpp \
../../Classes/Game/TK_LayerDailyRewardUnitWill.cpp \
../../Classes/Game/TK_LayerDailyRewardWill.cpp \
../../Classes/Game/TK_LayerDiving.cpp \
../../Classes/Game/TK_LayerDivining.cpp \
../../Classes/Game/TK_LayerDiviningHelp.cpp \
../../Classes/Game/TK_LayerDragonReward.cpp \
../../Classes/Game/TK_LayerDragonTreasureHelp.cpp \
../../Classes/Game/TK_LayerDungeonNavigationList.cpp \
../../Classes/Game/TK_LayerEmperor.cpp \
../../Classes/Game/TK_LayerEmperorBet.cpp \
../../Classes/Game/TK_LayerEmperorGuess.cpp \
../../Classes/Game/TK_LayerEmperorHelp.cpp \
../../Classes/Game/TK_LayerEmperorReplay.cpp \
../../Classes/Game/TK_LayerEqCompare.cpp \
../../Classes/Game/TK_LayerEqOther.cpp \
../../Classes/Game/TK_LayerEquipment.cpp \
../../Classes/Game/TK_LayerEvenet.cpp \
../../Classes/Game/TK_LayerEventBtn.cpp \
../../Classes/Game/TK_LayerEventChangeCoin.cpp \
../../Classes/Game/TK_LayerEventDetail.cpp \
../../Classes/Game/TK_LayerEventDiscription.cpp \
../../Classes/Game/TK_LayerEventEmperor.cpp \
../../Classes/Game/TK_LayerEventKingTreasure.cpp \
../../Classes/Game/TK_LayerEventMonthCard.cpp \
../../Classes/Game/TK_LayerEventRecoveryEnergy.cpp \
../../Classes/Game/TK_LayerEventTreasure.cpp \
../../Classes/Game/TK_LayerEventUnit.cpp \
../../Classes/Game/TK_LayerFactorySystem.cpp \
../../Classes/Game/TK_LayerFactoryTip.cpp \
../../Classes/Game/TK_LayerFireCrackerRank.cpp \
../../Classes/Game/TK_LayerFireCrackerRankList.cpp \
../../Classes/Game/TK_LayerFireCrackerReward.cpp \
../../Classes/Game/TK_LayerFireCrackerRuleDetail.cpp \
../../Classes/Game/TK_LayerFirstLogin.cpp \
../../Classes/Game/TK_LayerFriend.cpp \
../../Classes/Game/TK_LayerFriendAdd.cpp \
../../Classes/Game/TK_LayerGagList.cpp \
../../Classes/Game/TK_LayerGerAidHelp.cpp \
../../Classes/Game/TK_LayerGerBuyConfirm.cpp \
../../Classes/Game/TK_LayerGerCommand.cpp \
../../Classes/Game/TK_LayerGerDetail.cpp \
../../Classes/Game/TK_LayerGerEnhance.cpp \
../../Classes/Game/TK_LayerGerEnhanceSpecial.cpp \
../../Classes/Game/TK_LayerGerEquipment.cpp \
../../Classes/Game/TK_LayerGerLetters.cpp \
../../Classes/Game/TK_LayerGerSell.cpp \
../../Classes/Game/TK_LayerGerTeam.cpp \
../../Classes/Game/TK_LayerGerUnion.cpp \
../../Classes/Game/TK_LayerGerUpdate.cpp \
../../Classes/Game/TK_LayerGodList.cpp \
../../Classes/Game/TK_LayerGodReady.cpp \
../../Classes/Game/TK_LayerGuidRankList.cpp \
../../Classes/Game/TK_LayerGuild.cpp \
../../Classes/Game/TK_LayerGuildAgreeList.cpp \
../../Classes/Game/TK_LayerGuildContribution.cpp \
../../Classes/Game/TK_LayerGuildDailyReward.cpp \
../../Classes/Game/TK_LayerGuildDeclaration.cpp \
../../Classes/Game/TK_LayerGuildLog.cpp \
../../Classes/Game/TK_LayerGuildMain.cpp \
../../Classes/Game/TK_LayerGuildMainMenu.cpp \
../../Classes/Game/TK_LayerGuildManager.cpp \
../../Classes/Game/TK_LayerGuildMember.cpp \
../../Classes/Game/TK_LayerGuildMemberManager.cpp \
../../Classes/Game/TK_LayerGuildNumberItem.cpp \
../../Classes/Game/TK_LayerHard.cpp \
../../Classes/Game/TK_LayerHuaRong.cpp \
../../Classes/Game/TK_LayerHuarongQuick.cpp \
../../Classes/Game/TK_LayerHuarongQuickReward.cpp \
../../Classes/Game/TK_LayerHuarongReward.cpp \
../../Classes/Game/TK_LayerInvite.cpp \
../../Classes/Game/TK_LayerInviteHelp.cpp \
../../Classes/Game/TK_LayerInviteUnite.cpp \
../../Classes/Game/TK_LayerItemDetail.cpp \
../../Classes/Game/TK_LayerItemEnhance.cpp \
../../Classes/Game/TK_LayerItemForge.cpp \
../../Classes/Game/TK_LayerItemUnionUnit.cpp \
../../Classes/Game/TK_LayerItemUpdate.cpp \
../../Classes/Game/TK_LayerKing.cpp \
../../Classes/Game/TK_LayerKingAddBuffer.cpp \
../../Classes/Game/TK_LayerKingBoxInfo.cpp \
../../Classes/Game/TK_LayerKingHelp.cpp \
../../Classes/Game/TK_LayerKingHouseRankList.cpp \
../../Classes/Game/TK_LayerKingRank.cpp \
../../Classes/Game/TK_LayerKingRankUnit.cpp \
../../Classes/Game/TK_LayerKingReport.cpp \
../../Classes/Game/TK_LayerKingStoreHouseRank.cpp \
../../Classes/Game/TK_LayerLevelMatch.cpp \
../../Classes/Game/TK_LayerLevelReward.cpp \
../../Classes/Game/TK_LayerLevelRewardUnit.cpp \
../../Classes/Game/TK_LayerLimitTimeGod.cpp \
../../Classes/Game/TK_LayerLimitTimeGodHelp.cpp \
../../Classes/Game/TK_LayerLimitTimeGodReward.cpp \
../../Classes/Game/TK_LayerListEquip.cpp \
../../Classes/Game/TK_LayerListEquipChange.cpp \
../../Classes/Game/TK_LayerListForgeTreasure.cpp \
../../Classes/Game/TK_LayerListItemEnhance.cpp \
../../Classes/Game/TK_LayerListItemSale.cpp \
../../Classes/Game/TK_LayerListOther.cpp \
../../Classes/Game/TK_LayerListTreasure.cpp \
../../Classes/Game/TK_LayerListTreasureEnhance.cpp \
../../Classes/Game/TK_LayerMain.cpp \
../../Classes/Game/TK_LayerMainCampaign.cpp \
../../Classes/Game/TK_LayerMap.cpp \
../../Classes/Game/TK_LayerMineBuff.cpp \
../../Classes/Game/TK_LayerMineFriendList.cpp \
../../Classes/Game/TK_LayerMineHelp.cpp \
../../Classes/Game/TK_LayerMinePartnerInvite.cpp \
../../Classes/Game/TK_LayerMineReport.cpp \
../../Classes/Game/TK_LayerMineSelf.cpp \
../../Classes/Game/TK_LayerMining.cpp \
../../Classes/Game/TK_LayerMultiBoxShow.cpp \
../../Classes/Game/TK_LayerNewFuncTip.cpp \
../../Classes/Game/TK_LayerNodeDownInfoAdvance.cpp \
../../Classes/Game/TK_LayerNodeDownInfoHard.cpp \
../../Classes/Game/TK_LayerNormal.cpp \
../../Classes/Game/TK_LayerOldUserReturn.cpp \
../../Classes/Game/TK_LayerOneKeyRob.cpp \
../../Classes/Game/TK_LayerOnesFight.cpp \
../../Classes/Game/TK_LayerOnesHelp.cpp \
../../Classes/Game/TK_LayerOnesJonin.cpp \
../../Classes/Game/TK_LayerOnesRankList.cpp \
../../Classes/Game/TK_LayerOnesReplay.cpp \
../../Classes/Game/TK_LayerOnesReport.cpp \
../../Classes/Game/TK_LayerOnesSupport.cpp \
../../Classes/Game/TK_LayerOnlineReward.cpp \
../../Classes/Game/TK_LayerOnlineRewardConfirm.cpp \
../../Classes/Game/TK_LayerPVP.cpp \
../../Classes/Game/TK_LayerPVPRanking.cpp \
../../Classes/Game/TK_LayerPowerMatch.cpp \
../../Classes/Game/TK_LayerPveCommand.cpp \
../../Classes/Game/TK_LayerPvpCommand.cpp \
../../Classes/Game/TK_LayerReportTop8.cpp \
../../Classes/Game/TK_LayerResultHuLao.cpp \
../../Classes/Game/TK_LayerResultHuaRong.cpp \
../../Classes/Game/TK_LayerResultHuaRong2.cpp \
../../Classes/Game/TK_LayerResultMining.cpp \
../../Classes/Game/TK_LayerResultPVPRanking.cpp \
../../Classes/Game/TK_LayerResultPve.cpp \
../../Classes/Game/TK_LayerResultRepLay.cpp \
../../Classes/Game/TK_LayerResultUnionWar.cpp \
../../Classes/Game/TK_LayerRewardMore.cpp \
../../Classes/Game/TK_LayerRewardOne.cpp \
../../Classes/Game/TK_LayerRewardTen.cpp \
../../Classes/Game/TK_LayerRoleDetail.cpp \
../../Classes/Game/TK_LayerRoleInfoHeader.cpp \
../../Classes/Game/TK_LayerRoleLevelUp.cpp \
../../Classes/Game/TK_LayerRpUnit.cpp \
../../Classes/Game/TK_LayerServerMission.cpp \
../../Classes/Game/TK_LayerSettingMain.cpp \
../../Classes/Game/TK_LayerSevenConsume.cpp \
../../Classes/Game/TK_LayerShopBox.cpp \
../../Classes/Game/TK_LayerShowPic.cpp \
../../Classes/Game/TK_LayerStarGer.cpp \
../../Classes/Game/TK_LayerStarGerHelp.cpp \
../../Classes/Game/TK_LayerSweep.cpp \
../../Classes/Game/TK_LayerTask.cpp \
../../Classes/Game/TK_LayerTitleReward.cpp \
../../Classes/Game/TK_LayerTitleRewardUnit.cpp \
../../Classes/Game/TK_LayerTreasureComposeSynthesis.cpp \
../../Classes/Game/TK_LayerTreasureEnhance.cpp \
../../Classes/Game/TK_LayerTreasureForge.cpp \
../../Classes/Game/TK_LayerTutorial.cpp \
../../Classes/Game/TK_LayerTutorialDialog.cpp \
../../Classes/Game/TK_LayerUnionBoss.cpp \
../../Classes/Game/TK_LayerUnionBossBattle.cpp \
../../Classes/Game/TK_LayerUnionBossDrop.cpp \
../../Classes/Game/TK_LayerUnionBossLeaderBoard.cpp \
../../Classes/Game/TK_LayerUnionBossLvUp.cpp \
../../Classes/Game/TK_LayerUnionBossSetTime.cpp \
../../Classes/Game/TK_LayerUnionFightDetail.cpp \
../../Classes/Game/TK_LayerUnionSecretShop.cpp \
../../Classes/Game/TK_LayerUnionStorage.cpp \
../../Classes/Game/TK_LayerUnionStorageReqestList.cpp \
../../Classes/Game/TK_LayerUnionWar.cpp \
../../Classes/Game/TK_LayerUnionWarDetail.cpp \
../../Classes/Game/TK_LayerUnionWarHelp.cpp \
../../Classes/Game/TK_LayerUnionWarMain.cpp \
../../Classes/Game/TK_LayerUnionWarReport.cpp \
../../Classes/Game/TK_LayerVersionCheck.cpp \
../../Classes/Game/TK_LayerVipInfo.cpp \
../../Classes/Game/TK_LayerWarningHeader.cpp \
../../Classes/Game/TK_LayerWebChatInvite.cpp \
../../Classes/Game/TK_LayerWindow.cpp \
../../Classes/Game/TK_LayerWindowEx.cpp \
../../Classes/Game/TK_LayerWindowTransition.cpp \
../../Classes/Game/TK_LayerWorldGuess.cpp \
../../Classes/Game/TK_LayerWorldWarBuy.cpp \
../../Classes/Game/TK_LayerWorldWarComboRank.cpp \
../../Classes/Game/TK_LayerWorldWarGuessBuy.cpp \
../../Classes/Game/TK_LayerWorldWarHelp.cpp \
../../Classes/Game/TK_LayerWorldWarMain.cpp \
../../Classes/Game/TK_LayerWorldWarRank.cpp \
../../Classes/Game/TK_LayerWorldWarRegister.cpp \
../../Classes/Game/TK_LayerWorldWarReport.cpp \
../../Classes/Game/TK_LevelMatchManager.cpp \
../../Classes/Game/TK_LimitTimeGodData.cpp \
../../Classes/Game/TK_LoginNotice.cpp \
../../Classes/Game/TK_MailData.cpp \
../../Classes/Game/TK_MailEdit.cpp \
../../Classes/Game/TK_MailEnter.cpp \
../../Classes/Game/TK_MailList.cpp \
../../Classes/Game/TK_MailMainView.cpp \
../../Classes/Game/TK_MailReportSubItem.cpp \
../../Classes/Game/TK_MailSelectMore.cpp \
../../Classes/Game/TK_MailSubItem.cpp \
../../Classes/Game/TK_MailsOperator.cpp \
../../Classes/Game/TK_MainLayerFireCracker.cpp \
../../Classes/Game/TK_MainLayerGiftMoney.cpp \
../../Classes/Game/TK_Message.cpp \
../../Classes/Game/TK_MessageList.cpp \
../../Classes/Game/TK_MiningData.cpp \
../../Classes/Game/TK_MoneyEevntData.cpp \
../../Classes/Game/TK_NetStructFight.cpp \
../../Classes/Game/TK_NodeAchievementUnit.cpp \
../../Classes/Game/TK_NodeActivityDescribe.cpp \
../../Classes/Game/TK_NodeActivityEntry.cpp \
../../Classes/Game/TK_NodeAdventureDetail.cpp \
../../Classes/Game/TK_NodeBlessOnce.cpp \
../../Classes/Game/TK_NodeChallengeGodSweepUint.cpp \
../../Classes/Game/TK_NodeChartsUnit.cpp \
../../Classes/Game/TK_NodeChatInformation.cpp \
../../Classes/Game/TK_NodeDamage.cpp \
../../Classes/Game/TK_NodeDescription.cpp \
../../Classes/Game/TK_NodeDivideUnit.cpp \
../../Classes/Game/TK_NodeDowninfo.cpp \
../../Classes/Game/TK_NodeDragonReward.cpp \
../../Classes/Game/TK_NodeDungeonCoodinate.cpp \
../../Classes/Game/TK_NodeDungeonNavigation.cpp \
../../Classes/Game/TK_NodeDungeonUnit.cpp \
../../Classes/Game/TK_NodeEmperorGuess.cpp \
../../Classes/Game/TK_NodeEmperorHead.cpp \
../../Classes/Game/TK_NodeEmperorRpUnit.cpp \
../../Classes/Game/TK_NodeFactorySystem.cpp \
../../Classes/Game/TK_NodeFastCollect.cpp \
../../Classes/Game/TK_NodeGagUnit.cpp \
../../Classes/Game/TK_NodeGeneralGerHeadUint.cpp \
../../Classes/Game/TK_NodeGer.cpp \
../../Classes/Game/TK_NodeGerAid.cpp \
../../Classes/Game/TK_NodeGerCard.cpp \
../../Classes/Game/TK_NodeGerList.cpp \
../../Classes/Game/TK_NodeGerShop.cpp \
../../Classes/Game/TK_NodeGerSkillDetail.cpp \
../../Classes/Game/TK_NodeGerUnit.cpp \
../../Classes/Game/TK_NodeGodUint.cpp \
../../Classes/Game/TK_NodeGuildAgreeNumber.cpp \
../../Classes/Game/TK_NodeGuildAllZoneRankUnit.cpp \
../../Classes/Game/TK_NodeGuildAskNumber.cpp \
../../Classes/Game/TK_NodeGuildGetActivity.cpp \
../../Classes/Game/TK_NodeGuildLog.cpp \
../../Classes/Game/TK_NodeHuLao.cpp \
../../Classes/Game/TK_NodeHuLaoPreReport.cpp \
../../Classes/Game/TK_NodeHuLaoReport.cpp \
../../Classes/Game/TK_NodeHuaRong.cpp \
../../Classes/Game/TK_NodeHuarongCard.cpp \
../../Classes/Game/TK_NodeHulaoDamage.cpp \
../../Classes/Game/TK_NodeItem.cpp \
../../Classes/Game/TK_NodeItemForShop.cpp \
../../Classes/Game/TK_NodeItemShop.cpp \
../../Classes/Game/TK_NodeItemUnit.cpp \
../../Classes/Game/TK_NodeKingHouseRankUint.cpp \
../../Classes/Game/TK_NodeLevelMatch.cpp \
../../Classes/Game/TK_NodeLimitTimeGodReward.cpp \
../../Classes/Game/TK_NodeLuckyPlayer.cpp \
../../Classes/Game/TK_NodeMineFriendInvite.cpp \
../../Classes/Game/TK_NodeMineInviteTip.cpp \
../../Classes/Game/TK_NodeMineReport.cpp \
../../Classes/Game/TK_NodeNanMan.cpp \
../../Classes/Game/TK_NodeNotice.cpp \
../../Classes/Game/TK_NodeOnesBet.cpp \
../../Classes/Game/TK_NodeOnesEight.cpp \
../../Classes/Game/TK_NodeOnesRpUnit.cpp \
../../Classes/Game/TK_NodeOnesRplayUnit.cpp \
../../Classes/Game/TK_NodeOnesSupport.cpp \
../../Classes/Game/TK_NodeOnlineReward.cpp \
../../Classes/Game/TK_NodePVPInfo.cpp \
../../Classes/Game/TK_NodePowerMatch.cpp \
../../Classes/Game/TK_NodeQuickSearch.cpp \
../../Classes/Game/TK_NodeReplayGerUnite.cpp \
../../Classes/Game/TK_NodeReportBox.cpp \
../../Classes/Game/TK_NodeRewardUnit.cpp \
../../Classes/Game/TK_NodeReword.cpp \
../../Classes/Game/TK_NodeServerInfo.cpp \
../../Classes/Game/TK_NodeServerMissionUnit.cpp \
../../Classes/Game/TK_NodeSevenConsume.cpp \
../../Classes/Game/TK_NodeShopBoxUnit.cpp \
../../Classes/Game/TK_NodeShow.cpp \
../../Classes/Game/TK_NodeShowPic.cpp \
../../Classes/Game/TK_NodeSweepUnit.cpp \
../../Classes/Game/TK_NodeTaskUnit.cpp \
../../Classes/Game/TK_NodeTreasureRobUnit.cpp \
../../Classes/Game/TK_NodeTreasureUint.cpp \
../../Classes/Game/TK_NodeUnionBossDrop.cpp \
../../Classes/Game/TK_NodeUnionFightDetailUnite.cpp \
../../Classes/Game/TK_NodeUnionFightUnite.cpp \
../../Classes/Game/TK_NodeUnionReportUnite.cpp \
../../Classes/Game/TK_NodeUnionStorage.cpp \
../../Classes/Game/TK_NodeUnionStorageRequest.cpp \
../../Classes/Game/TK_NodeUnit91.cpp \
../../Classes/Game/TK_NodeUnitCode.cpp \
../../Classes/Game/TK_NodeUnitFAQ.cpp \
../../Classes/Game/TK_NodeUnitInvite.cpp \
../../Classes/Game/TK_NodeUnitLock.cpp \
../../Classes/Game/TK_NodeUnitLogout.cpp \
../../Classes/Game/TK_NodeUnitMusic.cpp \
../../Classes/Game/TK_NodeUnitOnesShow.cpp \
../../Classes/Game/TK_NodeUnitPhoneNumber.cpp \
../../Classes/Game/TK_NodeUnitRegist.cpp \
../../Classes/Game/TK_NodeUnitWeibo.cpp \
../../Classes/Game/TK_NodeUpdateGerList.cpp \
../../Classes/Game/TK_NodeWorldWarComboRank.cpp \
../../Classes/Game/TK_NodeWorldWarFighter.cpp \
../../Classes/Game/TK_NodeWorldWarRank.cpp \
../../Classes/Game/TK_NodeWorldWarReport.cpp \
../../Classes/Game/TK_NotificationData.cpp \
../../Classes/Game/TK_OldUserReturnData.cpp \
../../Classes/Game/TK_OnesData.cpp \
../../Classes/Game/TK_OnlineRewardData.cpp \
../../Classes/Game/TK_PowerMatchManager.cpp \
../../Classes/Game/TK_PublicNoticeData.cpp \
../../Classes/Game/TK_PvpTable.cpp \
../../Classes/Game/TK_RebateData.cpp \
../../Classes/Game/TK_RewardData.cpp \
../../Classes/Game/TK_RoleData.cpp \
../../Classes/Game/TK_SDKFactory.cpp \
../../Classes/Game/TK_SDKManager.cpp \
../../Classes/Game/TK_SDKPlatformAndroid.cpp \
../../Classes/Game/TK_SceneGame.cpp \
../../Classes/Game/TK_SceneInitGer.cpp \
../../Classes/Game/TK_SceneLoading.cpp \
../../Classes/Game/TK_SceneLogin.cpp \
../../Classes/Game/TK_SceneLoginBackground.cpp \
../../Classes/Game/TK_SceneMain.cpp \
../../Classes/Game/TK_SceneReconnect.cpp \
../../Classes/Game/TK_SceneRegist.cpp \
../../Classes/Game/TK_SceneRoleCreate.cpp \
../../Classes/Game/TK_SceneServerList.cpp \
../../Classes/Game/TK_SceneStart.cpp \
../../Classes/Game/TK_SceneStory.cpp \
../../Classes/Game/TK_SceneTeamCompare.cpp \
../../Classes/Game/TK_ServerList.cpp \
../../Classes/Game/TK_ServerMissionManager.cpp \
../../Classes/Game/TK_SevenConsumeManager.cpp \
../../Classes/Game/TK_ShopBase.cpp \
../../Classes/Game/TK_ShopData.cpp \
../../Classes/Game/TK_ShopIngots.cpp \
../../Classes/Game/TK_ShopMainView.cpp \
../../Classes/Game/TK_ShopPay.cpp \
../../Classes/Game/TK_ShopPoints.cpp \
../../Classes/Game/TK_ShopSubItem.cpp \
../../Classes/Game/TK_SimpleAudioProcesser.cpp \
../../Classes/Game/TK_SkillTemplete.cpp \
../../Classes/Game/TK_StarGerData.cpp \
../../Classes/Game/TK_TaskManager.cpp \
../../Classes/Game/TK_TeamData.cpp \
../../Classes/Game/TK_TreasureComposeCompletePanel.cpp \
../../Classes/Game/TK_TreasureComposeMark.cpp \
../../Classes/Game/TK_TreasureComposeMatrial.cpp \
../../Classes/Game/TK_TreasureComposePanel.cpp \
../../Classes/Game/TK_TreasureComposeView.cpp \
../../Classes/Game/TK_TreasureDelegate.cpp \
../../Classes/Game/TK_TreasureFightProtectedItemUse.cpp \
../../Classes/Game/TK_TreasureFightProtectedItemUsePanel.cpp \
../../Classes/Game/TK_TreasureRobEnemyPanel.cpp \
../../Classes/Game/TK_TreasureRobEnemySubItem.cpp \
../../Classes/Game/TK_TreasureRobResult.cpp \
../../Classes/Game/TK_TreasureRobTable.cpp \
../../Classes/Game/TK_TreasureScrambleManager.cpp \
../../Classes/Game/TK_TutorialManager.cpp \
../../Classes/Game/TK_UnionBossData.cpp \
../../Classes/Game/TK_UnionScienceData.cpp \
../../Classes/Game/TK_UnionShopData.cpp \
../../Classes/Game/TK_UnionStorageData.cpp \
../../Classes/Game/TK_UnionWarData.cpp \
../../Classes/Game/TK_UnitOldUserReturn.cpp \
../../Classes/Game/TK_WorldWarData.cpp \
../../Classes/Game/TK_gerData.cpp \

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
$(LOCAL_PATH)/../../Classes\
$(LOCAL_PATH)/../../Classes\
$(LOCAL_PATH)/../../Classes/Common\
$(LOCAL_PATH)/../../Classes/Common/aescrypt\
$(LOCAL_PATH)/../../Classes/Common/CCControlButtonWithFX\
$(LOCAL_PATH)/../../Classes/Common/CCLabelFX\
$(LOCAL_PATH)/../../Classes/Common/CodeHelper\
$(LOCAL_PATH)/../../Classes/Game\
$(LOCAL_PATH)/../../Classes/iap\
$(LOCAL_PATH)/../../Classes/jsoncpp\
$(LOCAL_PATH)/../../Classes/jsoncpp/include\
$(LOCAL_PATH)/../../Classes/jsoncpp/include/json\
$(LOCAL_PATH)/../../Classes/jsoncpp/src\
$(LOCAL_PATH)/../../Classes/jsoncpp/src/lib_json\
$(LOCAL_PATH)/../../Classes/SinaWeibo\
$(LOCAL_PATH)/../../Classes/WeChatSdk\
${NDKROOT}/sources/cxx-stl/stlport/stlport
LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static cocosdenshion_static cocos_extension_static
include $(BUILD_SHARED_LIBRARY)
$(call import-module,CocosDenshion/android) \
$(call import-module,cocos2dx) \
$(call import-module,extensions) \
$(call import-module,cocos2dx/platform/third_party/android/prebuilt/libcurl) \
$(call import-module,cocos2dx/platform/third_party/android/prebuilt/libxml2)
