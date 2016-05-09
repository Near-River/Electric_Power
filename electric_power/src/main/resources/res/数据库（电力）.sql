
CREATE TABLE Elec_Antenna(
	AntennaID VARCHAR(50) NOT NULL,
	JctID VARCHAR(50)  NULL,
	AntennaName VARCHAR(50)   NULL,
	TxZengyi VARCHAR(50)  NULL,
	SPBSWidth VARCHAR(50)  NULL,
	JiHuaFangShi VARCHAR(50)  NULL,
	TxChengshi VARCHAR(50)  NULL,
	WorkHZ VARCHAR(50)  NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
) 



CREATE TABLE Elec_Device(
	DevID VARCHAR(50)  NOT NULL,
	DevPlanID VARCHAR(50)   NULL,
	JctID VARCHAR(50)   NULL,
	DevName VARCHAR(50)   NULL,
	DevType VARCHAR(10)   NULL,
	Trademark VARCHAR(50)  NULL,
	SpecType VARCHAR(50)  NULL,
	ProduceHome VARCHAR(50)  NULL,
	ProduceArea VARCHAR(50)  NULL,
	Useness VARCHAR(50)  NULL,
	Quality VARCHAR(10)  NULL,
	UseUnit VARCHAR(50)  NULL,
	DevExpense NUMERIC(20, 2) NULL,
	AdjustPeriod VARCHAR(50)  NULL,
	OverhaulPeriod VARCHAR(50)  NULL,
	Configure VARCHAR(100)  NULL,
	DevState VARCHAR(10)  NULL,
	RunDescribe VARCHAR(500)  NULL,
	COMMENT VARCHAR(500)  NULL,
	UseDate DATETIME NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	QUnit VARCHAR(10)  NULL,
	APUnit VARCHAR(10)  NULL,
	OPUnit VARCHAR(10)  NULL,
	APState VARCHAR(10)  NULL,
	OPState VARCHAR(10)  NULL
) 

CREATE TABLE Elec_Device_Plan(
	DevPlanID VARCHAR(50)  NOT NULL,
	JctID VARCHAR(50)   NULL,
	DevName VARCHAR(100)   NULL,
	DevType VARCHAR(10)   NULL,
	Trademark VARCHAR(50)  NULL,
	SpecType VARCHAR(50)  NULL,
	ProduceHome VARCHAR(50)  NULL,
	ProduceArea VARCHAR(50)  NULL,
	Useness VARCHAR(50)  NULL,
	Quality VARCHAR(10)  NULL,
	UseUnit VARCHAR(50)  NULL,
	DevExpense NUMERIC(20, 2) NULL,
	PlanDate DATETIME NULL,
	AdjustPeriod VARCHAR(50)  NULL,
	OverhaulPeriod VARCHAR(50)  NULL,
	Configure VARCHAR(50)  NULL,
	COMMENT VARCHAR(500)  NULL,
	PurchaseState VARCHAR(10)  NULL,
	IsDelete VARCHAR(10)  NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	QUnit VARCHAR(10)  NULL,
	APUnit VARCHAR(10)  NULL,
	OPUnit VARCHAR(10)  NULL
) 

CREATE TABLE Elec_Engineering(
	EngineID VARCHAR(50)  NOT NULL,
	ProjID VARCHAR(50)   NULL,
	EngineName VARCHAR(100)   NULL,
	EngineState VARCHAR(10)   NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
) 

CREATE TABLE Elec_Engineering_Plan(
	EnginePlanID VARCHAR(50)  NOT NULL,
	ProjPlanID VARCHAR(50)   NULL,
	EngineName VARCHAR(100)   NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
) 
CREATE TABLE Elec_Engineering_Plan_History(
	SepId VARCHAR(50)  NOT NULL,
	EnginePlanID VARCHAR(50)   NULL,
	ProjPlanID VARCHAR(50)   NULL,
	EngineName VARCHAR(100)   NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	DeclareYear VARCHAR(50)  NULL
) 


CREATE TABLE Elec_FileUpload(
    SeqID INT NOT NULL primary key,   #主键ID
	ProjID VARCHAR(50)  NULL,         #工程ID/所属单位
	BelongTo VARCHAR(50)  NULL,       #所属模块/图纸类别
	FileName VARCHAR(50)  NULL,       #文件名
	FileURL VARCHAR(1000)  NULL,      #文件路径
	ProgressTime VARCHAR(20)  NULL,   #上传时间
	COMMENT VARCHAR(500)  NULL        #文件描述
	#IsDelete VARCHAR(10)  NULL,       #是否删除
	#CreateEmpID VARCHAR(50)  NULL,    #创建人
	#CreateDate DATETIME NULL          #创建时间
)

CREATE TABLE Elec_JctBuild(
	BuildID VARCHAR(50)  NOT NULL,
	JctID VARCHAR(50)   NULL,
	BuildName VARCHAR(50)   NULL,
	BuildType VARCHAR(10)   NULL,
	BuildStartDate DATETIME NULL,
	DxDate DATETIME NULL,
	UseDate DATETIME NULL,
	BuildLayer VARCHAR(50)  NULL,
	BuildArea VARCHAR(50)  NULL,
	ExtendBuildDate DATETIME NULL,
	ExtendBuildArea VARCHAR(50)  NULL,
	BuildExpense NUMERIC(20, 2) NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
 )
CREATE TABLE Elec_MaintainPlan(
	PlanID VARCHAR(50)  NOT NULL,
	JctID VARCHAR(50)   NULL,
	OccurDate DATETIME  NULL,
	MainContent VARCHAR(500)  NULL,
	COMMENT VARCHAR(500)  NULL
)

CREATE TABLE Elec_Overhaul_Record(
	DevID VARCHAR(50)   NULL,
	IsAdjust VARCHAR(10)   NULL,
	StartDate DATETIME  NULL,
	EndDate DATETIME  NULL,
	IsHaving VARCHAR(10)   NULL,
	Record VARCHAR(500)  NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	SeqID INT NOT NULL
)
CREATE TABLE Elec_Project(
	ProjID VARCHAR(50)  NOT NULL,
	JctID VARCHAR(50)   NULL,
	LayProjID VARCHAR(50)   NULL,
	ProjName VARCHAR(100)   NULL,
	ProjState VARCHAR(10)   NULL,
	ProjType VARCHAR(50)   NULL,
	PlanInvest NUMERIC(20, 2) NULL,
	MainContent VARCHAR(100)  NULL,
	BuildDate DATETIME NULL,
	IsYanShou VARCHAR(10)   NULL,
	Cycle VARCHAR(50)  NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	YanShouDate DATETIME NULL,
	ProjSize VARCHAR(50)  NULL
)

CREATE TABLE Elec_Project_Declare(
	SeqID INT NOT NULL,
	ProjPlanID VARCHAR(50)   NULL,
	DeclareYear VARCHAR(10)   NULL,
	DeclareState VARCHAR(10)   NULL
)
CREATE TABLE Elec_Project_Layout(
	LayProjID VARCHAR(50)  NOT NULL,
	ProjName VARCHAR(100)   NULL,
	SeqID INT  NULL,
	ProjGrade INT  NULL,
	ParentProjID VARCHAR(50)   NULL,
	Property VARCHAR(10)   NULL,
	Size VARCHAR(50)  NULL,
	Adress VARCHAR(50)  NULL,
	StartTime DATETIME NULL,
	EndTime DATETIME NULL,
	PlanStartTime DATETIME NULL,
	PlanInvest NUMERIC(20, 2) NULL,
	MainContent VARCHAR(500)  NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	VarSeqId VARCHAR(50)  NULL
)

CREATE TABLE Elec_Project_Plan(
	ProjPlanID VARCHAR(50)  NOT NULL,
	JctID VARCHAR(50)   NULL,
	LayProjID VARCHAR(50)   NULL,
	ProjName VARCHAR(100)   NULL,
	ProjState VARCHAR(10)   NULL,
	ProjType VARCHAR(10)   NULL,
	DeclareDate DATETIME NULL,
	PlanInvest NUMERIC(20, 2) NULL,
	KeYan VARCHAR(10)   NULL,
	ChuShe VARCHAR(10)   NULL,
	KeYanInvest NUMERIC(20, 2) NULL,
	ChuSheInvest NUMERIC(20, 2) NULL,
	IsDeclare VARCHAR(10)   NULL,
	IsApprove VARCHAR(10)   NULL,
	Gist VARCHAR(100)  NULL,
	MainContent VARCHAR(500)  NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	ProjSize VARCHAR(50)  NULL
)


CREATE TABLE Elec_Station(
	StationID VARCHAR(50)  NOT NULL,
	JctID VARCHAR(50)   NULL,
	StationCode VARCHAR(50)   NULL,
	StationName VARCHAR(50)   NULL,
	JCFrequency VARCHAR(100)  NULL,
	ProduceHome VARCHAR(50)  NULL,
	ContactType VARCHAR(50)  NULL,
	UseStartDate DATETIME NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	StationType VARCHAR(50)   NULL,
	AttributionGround VARCHAR(50)  NULL,
	period VARCHAR(50)  NULL
)
CREATE TABLE Elec_StationBug(
	BugID INT  NOT NULL,
	StationID VARCHAR(50)   NULL,
	JctID VARCHAR(50)   NULL,
	SbMonth VARCHAR(50)   NULL,
	BugType VARCHAR(10)   NULL,
	OccurDate VARCHAR(50)   NULL,
	LauncherName VARCHAR(50)  NULL,
	BugDescribe VARCHAR(500)  NULL,
	ResolveDate VARCHAR(50)  NULL,
	ResolveMethod VARCHAR(500)  NULL,
	BugReason VARCHAR(500)  NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
)
CREATE TABLE Elec_SubEngineering(
	SubEngineID VARCHAR(50)  NOT NULL,
	ProjID VARCHAR(50)   NULL,
	EngineID VARCHAR(50)   NULL,
	SubEngineName VARCHAR(100)   NULL,
	SubEngineState VARCHAR(10)   NULL,
	UnitPrice NUMERIC(20, 2) NULL,
	Quality INT NULL,
	PlanInvest NUMERIC(20, 2) NULL,
	InvestMonth VARCHAR(50)  NULL,
	ActualInvest NUMERIC(20, 2) NULL,
	TiaoGaiInvest NUMERIC(20, 2) NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
)
CREATE TABLE Elec_SubEngineering_Plan(
	SubEngineID VARCHAR(50)  NOT NULL,
	ProjPlanID VARCHAR(50)   NULL,
	EnginePlanID VARCHAR(50)   NULL,
	SubEngineName VARCHAR(100)   NULL,
	UnitPrice NUMERIC(20, 2) NULL,
	Quality INT NULL,
	PlanInvest NUMERIC(20, 2) NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL
)
CREATE TABLE Elec_SubEngineering_Plan_History(
	SsepId VARCHAR(50)  NOT NULL,
	SubEngineID VARCHAR(50)   NULL,
	ProjPlanID VARCHAR(50)   NULL,
	EnginePlanID VARCHAR(50)   NULL,
	SubEngineName VARCHAR(100)   NULL,
	UnitPrice NUMERIC(20, 2) NULL,
	Quality INT NULL,
	PlanInvest NUMERIC(20, 2) NULL,
	COMMENT VARCHAR(500)  NULL,
	IsDelete VARCHAR(10)   NULL,
	CreateEmpID VARCHAR(50)  NULL,
	CreateDate DATETIME NULL,
	LastEmpID VARCHAR(50)  NULL,
	LastDate DATETIME NULL,
	DeclareYear VARCHAR(50)   NULL
)

CREATE TABLE Elec_ExportFields(
	belongTo VARCHAR(10)  NOT NULL primary key,           #所属模块（自然主键），如用户管理为：5-1
	expNameList VARCHAR(5000)  NULL,          #导出字段的中文名
	expFieldName VARCHAR(5000)  NULL,         #导出字段的英文名
	noExpNameList VARCHAR(5000)  NULL,        #未导出字段的中文名
	noExpFieldName VARCHAR(5000)  NULL        #未导出字段的英文名
)
存储运行监控数据 5-3
  stationRun#devRun#createDate
  站点运行情况#设备运行情况#创建日期

存储用户管理数据 5-1
  jctID#userName#logonName#logonPwd#sexID#birthday#address#contactTel#email#mobile#isDuty#onDutyDate#offDutyDate#remark
	所属单位#用户姓名#登录名#密码#性别#出生日期#联系地址#联系电话#电子邮箱#手机#是否在职#入职时间#离职时间#备注


#日志表
CREATE TABLE Elec_Log(
	logID varchar(50) not null primary key, #主键ID
	ipAddress varchar(50),			#IP地址
	opeName varchar(50),				#操作人
	opeTime DATETIME,						#操作时间
	details varchar(500)        #操作明细
)

#测试表
CREATE TABLE Elec_Text(
	textID varchar(50) not null primary key,
	textName varchar(50),
	textDate datetime,
	textRemark varchar(500)
)

-----------------------------------------------------------------工作流表

#申请模板表
CREATE TABLE Elec_Application_Template (
	id INT NOT NULL PRIMARY KEY, #主键ID
	name VARCHAR(500),#名称
	processDefinitionKey VARCHAR(500),#流程定义的key
	path VARCHAR(5000)#上传的模板文件的存储位置
)

#申请模板与申请信息是1对多的关系
#申请信息表
CREATE TABLE Elec_Application (
	applicationID INT NOT NULL PRIMARY KEY, #主键ID
	applicationTemplateID INT, #申请模板表的主键
	applicationUserID VARCHAR(50),#申请人ID
	title VARCHAR(100),#上传的文件标题
	path VARCHAR(5000),#上传的文件的存储路径
	applyTime TIMESTAMP,#申请日期
	status VARCHAR(10), #当前审批状态
	processInstanceID VARCHAR(100),#流程实例ID，用于工作流中查看活动执行位置的流程图
	CONSTRAINT FOREIGN KEY(applicationTemplateID) REFERENCES Elec_Application_Template(id),
	CONSTRAINT FOREIGN KEY(applicationUserID) REFERENCES Elec_User(userID)
)

#申请信息与审批信息的关系是1对多关系
#审批信息表
CREATE TABLE Elec_ApproveInfo (
	approveID INT NOT NULL PRIMARY KEY, #主键ID
	applicationID INT,#申请信息表的主键
	comment VARCHAR(5000),#审批意见
	approval BOOLEAN, #审批结果，是否通过（同意或者不同意）
	approveUserID VARCHAR(50),#审批人ID
	approveTime TIMESTAMP, #审批日期
	CONSTRAINT FOREIGN KEY(applicationID) REFERENCES Elec_Application(applicationID),
	CONSTRAINT FOREIGN KEY(approveUserID) REFERENCES Elec_User(userID)
)

-----------------------------------------------------------------工作流表


