<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD>
    <TITLE>国家电力监测中心首页</TITLE>
    <LINK href="${pageContext.request.contextPath}/css/Font.css" type="text/css" rel="stylesheet">
    <STYLE>
        BODY {
            SCROLLBAR-ARROW-COLOR: #ffffff;
            SCROLLBAR-BASE-COLOR: #dee3f7
        }
    </STYLE>
</HEAD>

<frameset border=0 frameSpacing=0 rows=82,* frameBorder=0 id="mainparent">
    <frame name=topFrame src="${pageContext.request.contextPath }/system/menuManage_title.action" noResize scrolling=no>
    <frameset id="main" border="0" frameSpacing="0" frameBorder="0" cols="200,1%,*">
        <frame name="leftFrame" src="${pageContext.request.contextPath }/system/menuManage_left.action" noResize>
        <frame name="changeButton" src="${pageContext.request.contextPath }/system/menuManage_change.action"
               frameBorder=0
               marginHeight=0 marginWidth=0 scrolling=no noresize>
        <frame name="mainFrame" src="${pageContext.request.contextPath }/system/menuManage_loading.action">
    </frameset>
</frameset>

</HTML>
