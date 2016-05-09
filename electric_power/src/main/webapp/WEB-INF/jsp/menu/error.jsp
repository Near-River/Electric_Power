<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD><TITLE>国家电力监测中心</TITLE>
    <LINK href="${pageContext.request.contextPath }/css/MainPage.css" type="text/css" rel="stylesheet">
    <LINK href="${pageContext.request.contextPath }/css/buttonstyle.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/buttonstyle.css">
    <SCRIPT type="text/javascript">
        function submitrequest(action) {
            eval("document.location='" + action + "'");
        }
    </SCRIPT>
</HEAD>
<BODY bgcolor="#FFFFFF" onunload="submitrequest('logout.do')">
<Form name=form1 method=POST>
    <table border="0" width="100%" id="table1" height="504" cellspacing="0" cellpadding="0">
        <tr>
            <td align=center>
                <table border="0" width="60%" id="table2" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="39" height="34">
                            <img border="0" src="${pageContext.request.contextPath }/images/zhuyi.jpg" width="39" height="34"></td>
                        <td style="word-break:break-all">
                            <span style="font-family: 黑体; font-size: medium; ">
                                很遗憾，您的页面已经过期！请您重新登录!
                            </span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</Form>
</BODY>