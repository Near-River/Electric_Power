<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD><TITLE>国家电力监测中心设备资源管理系统</TITLE>
    <LINK href="${pageContext.request.contextPath }/css/MainPage.css" type="text/css" rel="stylesheet">
    <LINK href="${pageContext.request.contextPath }/css/buttonstyle.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/buttonstyle.css">
    <script>
        var i = 6;
        var t;
        function showTimer() {
            if (i == 0) {
                //如果秒数为0的话,清除t,防止一直调用函数,对于反应慢的机器可能实现不了跳转到的效果，所以要清除掉
                parent.location.href = "${pageContext.request.contextPath }/system/menuManage_logout.action";
                window.clearInterval(t);
            } else {
                i = i - 1;
                // 秒数减少并插入 timer 层中
                document.getElementById("timer").innerHTML = i + "秒";
            }
        }
        // 每隔一秒钟调用一次函数 showTimer()
        t = window.setInterval(showTimer, 1000);
    </script>
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
                                    <b>非法操作！系统将在5秒钟内跳转到登录页面</b>
                                </s:if>
                                <s:else>
                                    ${requestScope.errorMsg}
                                </s:else>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="39" height="34"></td>
                        <td style="word-break:break-all" align="center">
                            <span style="font-family: 黑体; font-size: small; color: red; ">
                                <div id="timer" style="color:#999;font-size:20pt;text-align:center"></div>
                            </span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</Form>
</BODY>