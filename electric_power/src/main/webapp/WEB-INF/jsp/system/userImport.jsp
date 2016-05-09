<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>导入文件</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <SCRIPT language="javascript">
    </SCRIPT>
</head>
<body>
<form action="${pageContext.request.contextPath }/system/userManage_importExcel.action" method="post"
      enctype="multipart/form-data">
    <br>
    <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
            <td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"
                colspan="4">
                <font face="宋体" size="2"><strong>Excel文件数据导入</strong></font>
            </td>
        </tr>
        <tr>
            <td width="1%" height=30></td>
            <td width="20%"></td>
            <td width="78%"></td>
            <td width="1%"></td>
        </tr>
        <tr>
            <td width="1%"></td>
            <td width="15%" align="center">请选择文件:</td>
            <td width="83%" align="left">
                <input type="file" name="excel" style="width:365px"/>
            </td>
            <td width="1%"></td>
        </tr>
        <tr height=50>
            <td colspan=4></td>
        </tr>
        <tr height=2>
            <td colspan=4 background="${pageContext.request.contextPath }/images/b-info.gif"></td>
        </tr>
        <tr height=10>
            <td colspan=4></td>
        </tr>
        <tr>
            <td align="center" colspan=4>
                <input type="submit" name="import" value="导入"
                       style="width: 60px; font-size:12px; color:black; height:22px"/>
                <input type="button" name="Reset1" id="save" value="关闭"
                       onClick="opener.location.reload();window.close();"
                       style="width: 60px; font-size:12px; color:black; height:22px">
            </td>
        </tr>
    </table>
</form>
<s:if test="#request.errorList!=null && #request.errorList.size>0">
    <!-- 显示错误信息 -->
    <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
            <td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"
                colspan="4">
                <span style="font-family: 宋体; font-size: x-small; "><strong>导入失败！错误信息！</strong></span>
            </td>
        </tr>
        <s:iterator value="#request.errorList" var="error">
            <tr>
                <td width="1%"></td>
                <td width="15%" align="center">错误信息:</td>
                <td width="83%" align="left">
                    <span style="color: red; ">${error}</span>
                </td>
                <td width="1%"></td>
            </tr>
        </s:iterator>
    </table>
</s:if>

</body>
</html>
