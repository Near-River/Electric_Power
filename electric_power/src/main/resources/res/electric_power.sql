# 导出设置数据初始化
INSERT INTO elec_exportfields(belongTo,expNameList,expFieldName) VALUES('5-3','站点运行情况#设备运行情况#创建日期','stationRun#devRun#createTime');
INSERT INTO elec_exportfields (belongTo, expNameList, expFieldName) VALUES ('5-1',
                                                                            '所属单位#用户姓名#登录名#密码#性别#出生日期#联系地址#联系电话#电子邮箱#手机#是否在职#入职时间#离职时间#职位#备注',
                                                                            'jctID#userName#loginName#password#sexID#birthday#address#contactTel#email#mobile#isDuty#onDutyDate#offDutyDate#postID#remark')

# 插入数据字典
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 1,'性别',1,'男' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 2,'性别',2,'女');
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 3,'是否在职',1,'是' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 4,'是否在职',2,'否' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 5,'所属单位',1,'北京' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 6,'所属单位',2,'上海' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 7,'所属单位',3,'深圳' );


insert elec_systemdd(id,keyword,ddCode,ddValue) values( 14,'审核状态',1,'审核中' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 15,'审核状态',2,'审核不通过' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 16,'审核状态',3,'审核通过' );

insert elec_systemdd(id,keyword,ddCode,ddValue) values( 17,'图纸类别',1,'国内图书' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 18,'图纸类别',2,'国外图书' );

insert elec_systemdd(id,keyword,ddCode,ddValue) values( 19,'职位',1,'总经理' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 20,'职位',2,'部门经理' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 21,'职位',3,'员工' );

insert elec_systemdd(id,keyword,ddCode,ddValue) values( 51,'北京',1,'北京昌平电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 52,'北京',2,'北京海淀电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 53,'北京',3,'北京西城电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 54,'上海',1,'上海浦东电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 55,'上海',2,'上海闸北电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 56,'上海',3,'上海徐汇电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 57,'深圳',1,'深圳福田电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 58,'深圳',2,'深圳龙岗电力公司' );
insert elec_systemdd(id,keyword,ddCode,ddValue) values( 59,'深圳',3,'深圳南山电力公司' );

# 系统角色
INSERT INTO  elec_role(roleID,roleName) values( '1','系统管理员');
INSERT INTO  elec_role(roleID,roleName) values( '2','高级管理员');
INSERT INTO  elec_role(roleID,roleName) values( '3','中级管理员');
INSERT INTO  elec_role(roleID,roleName) values( '4','业务用户' );
INSERT INTO  elec_role(roleID,roleName) values( '5','一般用户' );
INSERT INTO  elec_role(roleID,roleName) values( '6','普通用户' );

# 系统权限

#左侧菜单
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'aa','0','技术设施维护管理','','images/MenuIcon/jishusheshiweihuguanli.gif','',TRUE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ab','aa','仪器设备管理','','images/MenuIcon/yiqishebeiguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ac','aa','设备校准检修','','images/MenuIcon/shebeijiaozhunjianxiu.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ad','aa','设备购置计划','','images/MenuIcon/shebeigouzhijihua.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ae','0','技术资料图纸管理','','images/MenuIcon/jishuziliaotuzhiguanli.gif','',TRUE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'af','ae','资料图纸管理','','images/MenuIcon/ziliaotuzhiguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ag','0','站点设备运行管理','','images/MenuIcon/zhuandianshebeiyunxingguanli.gif','',TRUE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ah','ag','站点基本信息','','images/MenuIcon/zhandianjibenxinxi.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ai','ag','运行情况','','images/MenuIcon/yunxingqingkuang.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'aj','ag','维护情况','','images/MenuIcon/weihuqingkuang.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ak','0','监测台建筑管理','','images/MenuIcon/jiancetaijianzhuguanli.gif','',TRUE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'al','ak','监测台建筑管理','','images/MenuIcon/jiancetaijianzhu.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'am','0','系统管理','','images/MenuIcon/xitongguanli.gif','',TRUE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'an','am','用户管理','system/userManage_index.action','images/MenuIcon/yonghuguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ao','am','角色管理','system/roleManage_index.action','images/MenuIcon/jueseguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ap','am','运行监控','system/commonMsg_home.action','images/MenuIcon/daibanshiyi.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'aq','am','数据字典维护','system/systemDD_index.action','images/MenuIcon/shujuzidianguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ar','0','审批流转','','images/MenuIcon/shenpiliuzhuanguanli.gif','',TRUE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'as','ar','审批流程管理','workflow/processDefinition_home.action','images/MenuIcon/shenpiliuchengguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'at','ar','申请模板管理','workflow/applicationTemplate_home.action','images/MenuIcon/shenqingmobanguanli.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'au','ar','起草申请','workflow/applicationFlow_home.action','images/MenuIcon/qicaoshenqing.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'av','ar','待我审批','workflow/applicationFlow_myTask.action','images/MenuIcon/daiwoshenpi.gif','mainFrame',FALSE,TRUE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'aw','ar','我的申请查询','workflow/applicationFlow_myApplication.action','images/MenuIcon/wodeshenqingchaxun.gif','mainFrame',FALSE,TRUE );

#系统登录
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ba','0','系统登录','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bb','ba','首页显示','system/menuManage_home.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bc','ba','标题','system/menuManage_title.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bd','ba','菜单','system/menuManage_left.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'be','ba','加载左侧树型结构','system/menuManage_showMenu.action','','',FALSE,FALSE );

INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bf','ba','改变框架','system/menuManage_change.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bg','ba','加载主显示页面','system/menuManage_loading.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bh','ba','站点运行','system/menuManage_alermStation.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bi','ba','设备运行','system/menuManage_alermDevice.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'bj','ba','重新登录','system/menuManage_logout.action','','',FALSE,FALSE );

#运行监控
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ca','0','运行监控','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'cb','ca','保存','system/commonMsg_save.action','','',FALSE,FALSE );
# INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'cc','ca','ajax进度条','/system/commonMsg_progressBar.action','','',FALSE,FALSE );

#导出设置
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'da','0','导出设置','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'db','da','导出设置设置','system/exportExcel_setExcelFields.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'dc','da','导出设置保存','system/exportExcel_saveExportExcel.action','','',FALSE,FALSE );

#数据字典
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ea','0','数据字典','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'eb','ea','编辑','system/systemDD_edit.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ec','ea','保存','system/systemDD_save.action','','',FALSE,FALSE );

#用户管理
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fa','0','用户管理','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fb','fa','新增','system/userManage_add.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fc','fa','保存','system/userManage_merge.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fd','fa','编辑','system/userManage_edit.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fe','fa','删除','system/userManage_delete.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ff','fa','验证登录名','system/userManage_checkUser.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fg','fa','导出excel','system/userManage_exportExcel.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fh','fa','excel导入页面','system/userManage_importPage.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fi','fa','excel导入','system/userManage_importData.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fj','fa','人员统计(单位)','system/userManage_chartUser.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fk','fa','人员统计(性别)','system/userManage_chartUserFCF.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'fl','fa','联动查询单位名称','system/userManage_findJctUnit.action','','',FALSE,FALSE );

#角色管理
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ga','0','角色管理','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'gb','ga','编辑','system/roleManage_edit.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'gc','ga','保存','system/roleManage_save.action','','',FALSE,FALSE );

#申请流程管理
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ha','0','申请流程管理','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'hb','ha','部署流程定义','workflow/processDefinition_add.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'hc','ha','保存','workflow/processDefinition_save.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'hd','ha','查看流程图','workflow/processDefinition_downloadProcessImage.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'he','ha','删除流程定义','workflow/processDefinition_delete.action','','',FALSE,FALSE );


#申请模板管理
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ia','0','申请模板管理','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ib','ia','新增','workflow/applicationTemplate_add.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ic','ia','新增保存','workflow/applicationTemplate_save.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'id','ia','编辑','workflow/applicationTemplate_edit.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ie','ia','编辑保存','workflow/applicationTemplate_update.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'if','ia','删除','workflow/applicationTemplate_delete.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ig','ia','下载','workflow/applicationTemplate_download.action','','',FALSE,FALSE );


#起草申请、待我审批、我的申请查询
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ja','0','申请流程管理','','','',TRUE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jb','ja','提交申请页面','workflow/applicationFlow_submitApplication.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jc','ja','提交申请','workflow/applicationFlow_saveApplication.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jd','ja','我的申请查询首页','workflow/applicationFlow_myApplication.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'je','ja','待我审批首页','workflow/applicationFlow_myTask.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jf','ja','跳转审批处理页面','workflow/applicationFlow_flowApprove.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jg','ja','下载','workflow/applicationFlow_download.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jh','ja','审核','workflow/applicationFlow_approve.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'ji','ja','审核历史','workflow/applicationFlow_approvedHistory.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jj','ja','查看流程图','workflow/applicationFlow_approvedHistoryPic.action','','',FALSE,FALSE );
INSERT elec_popedom(MID,pid,NAME,url,icon,target,isParent,ismenu) VALUES( 'jk','ja','查看流程执行位置图片','workflow/applicationFlow_processImage.action','','',FALSE,FALSE );
