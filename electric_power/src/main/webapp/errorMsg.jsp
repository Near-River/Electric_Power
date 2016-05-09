<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD><TITLE>国家电力监测中心设备资源管理系统</TITLE>
    <LINK href="${pageContext.request.contextPath }/css/MainPage.css" type="text/css" rel="stylesheet">
    <LINK href="${pageContext.request.contextPath }/css/buttonstyle.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/buttonstyle.css">
</HEAD>
<BODY bgcolor="#FFFFFF" onload="showTimer()" background="${pageContext.request.contextPath }/images/back1.jpg">
<Form name="form1" method="POST">
    <table border="0" width="100%" id="table1" height="100%" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">
                <table border="0" width="60%" id="table2" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="49" height="45">
                            <img border="0"
                                 src="${pageContext.request.contextPath }/images/build.gif"
                                 width="185px" height="180px"></td>
                        <td style="word-break:break-all" align="center">
                            <span style="font-family: 黑体; font-size: medium; color: red; ">
                                <s:if test="#request.errorMsg == null">
                                    <b>系统出现故障！请联系管理员！</b>
                                </s:if>
                                <s:else>
                                    ${requestScope.errorMsg}
                                </s:else>
                            </span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</Form>
</BODY>