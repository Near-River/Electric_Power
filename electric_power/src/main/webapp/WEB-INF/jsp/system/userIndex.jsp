<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<HTML>
<HEAD>
    <title>用户管理</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/DatePicker/WdatePicker.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/page.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
    <script language="javascript">
        function deleteAll() {
            var selectuser = document.getElementsByName("userIDs");
            var flag = false;
            for (var i = 0; i < selectuser.length; i++) {
                if (selectuser[i].checked) {
                    flag = true;
                }
            }
            if (!flag) {
                alert("没有选择执行操作的用户！不能执行该操作");
                return false;
            } else {
                if (!window.confirm("你确定执行批量删除吗？")) {
                    return false;
                } else {
                    document.Form2.action = "${pageContext.request.contextPath }/system/userManage_delete.action";
                    document.Form2.submit();
                }
            }
        }
        //用户:全部选中/全部不选中
        function checkAllUser(user) {
            var selectuser = document.getElementsByName("userIDs");
            for (var i = 0; i < selectuser.length; i++) {
                selectuser[i].checked = user.checked;
            }
        }

        //导出Exce数据字段
        function exportExcel() {
            var userName = document.getElementById("userName").value;
            userName = encodeURI(userName, 'UTF-8');
            var jctID = document.getElementById("jctID").value;
            var onDutyDate = document.getElementById("onDutyDate").value;
            var offDutyDate = document.getElementById("offDutyDate").value;
            openWindow('${pageContext.request.contextPath }/system/userManage_exportExcel.action?userName=' + userName + '&jctID=' + jctID + '&onDutyDate=' + onDutyDate + '&offDutyDate=' + offDutyDate, '700', '400')
        }
    </script>
</HEAD>
<body>
<form id="Form1" name="Form1" action="${pageContext.request.contextPath }/system/userManage_index.action" method="post"
      style="margin:0px;">
    <input type="hidden" value="1" name="initPage"/>
    <table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
        <tr height=10>
            <td></td>
        </tr>
        <tr>
            <td class="ta_01" colspan="4" align="center"
                background="${pageContext.request.contextPath }/images/b-info.gif">
                <span style="font-family: 宋体; font-size: x-small; "><strong>用户信息管理</strong></span>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                姓名：
            </td>
            <td class="ta_01">
                <s:textfield name="userName" size="21" id="userName"/>
            </td>
            <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                所属单位：
            </td>
            <td class="ta_01">
                <s:select name="jctID" id="jctID" list="#request.systemDDList" cssStyle="width:155px"
                          listKey="ddCode" listValue="ddValue"
                          headerKey="" headerValue="请选择"/>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                入职时间：
            </td>
            <td class="ta_01" colspan="3">
                <s:date name="%{model.onDutyDate}" var="begin"/>
                <s:date name="%{model.offDutyDate}" var="end"/>
                <s:textfield name="onDutyDate" id="onDutyDate" maxlength="50" size="20"
                             onclick="WdatePicker()" value="%{begin}"/>
                ~
                <s:textfield name="offDutyDate" id="offDutyDate" maxlength="50" size="20"
                             onclick="WdatePicker()" value="%{end}"/>
            </td>
        </tr>

    </table>
</form>
<form id="Form2" name="Form2" action="${pageContext.request.contextPath }/system/userManage_index.action" method="post">
    <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <TR height=10>
            <td></td>
        </TR>
        <tr>
            <td>
                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
                    <TR>
                        <TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img
                                src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
                        <TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">
                            用户列表
                        </TD>
                    </TR>
                </TABLE>
            </td>
            <td class="ta_01" align="right">
                <input style="font-size:12px; color:black; height:20px;width:80px" type="button" value="查询"
                       name="BT_find" onclick="document.forms[0].submit()">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" id="BT_Add" type="button"
                       value="添加用户" name="BT_Add"
                       onclick="openWindow('${pageContext.request.contextPath }/system/userManage_add.action','700','400')">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" id="BT_Delete" type="button"
                       value="批量删除" name="BT_Delete" onclick="return deleteAll()">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" id="BT_Export" type="button"
                       value="导出设置" name="BT_Export"
                       onclick="openWindow('${pageContext.request.contextPath }/system/exportExcel_setExcelFields.action?belongTo=5-1','900','500')">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" type="button" value="导入"
                       onclick="openWindow('${pageContext.request.contextPath }/system/userManage_importPage.action','900','500')">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" type="button"
                       value="导出" name="BT_Export" onclick="exportExcel()">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" type="button"
                       value="人员统计(单位)" name="BT_Export" onclick="openWindow('${pageContext.request.contextPath }/system/userManage_chart.action','900','700')">&nbsp;&nbsp;
                <input style="font-size:12px; color:black; height:20px;width:80px" type="button"
                       value="人员统计(性别)" name="BT_Export" onclick="openWindow('${pageContext.request.contextPath }/system/userManage_chartFCF.action','900','550')">&nbsp;&nbsp;
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">
                <table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%;
                       WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
                    <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">

                        <td align="center" width="5%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">
                            <input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)">
                        </td>
                        <td align="center" width="15%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">登录名
                        </td>
                        <td align="center" width="15%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">用户姓名
                        </td>
                        <td align="center" width="7%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">性别
                        </td>
                        <td align="center" width="15%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">联系电话
                        </td>
                        <td align="center" width="15%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">入职时间
                        </td>
                        <td align="center" width="8%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">职位
                        </td>
                        <td width="10%" align="center" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑
                        </td>
                        <td width="10%" align="center" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">查看
                        </td>
                    </tr>

                    <s:if test="%{#request.userList!=null && #request.userList.size>0}">
                        <s:iterator value="#request.userList" var="user">
                            <tr onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="HEIGHT:22px" align="center" width="5%">
                                    <input type="checkbox" name="userIDs" id="userIDs" value="${user.userID}">
                                </td>
                                <td style="HEIGHT:22px" align="center" width="15%">
                                        ${user.loginName}
                                </td>
                                <td style="HEIGHT:22px" align="center" width="15%">
                                        ${user.userName}
                                </td>
                                <td style="HEIGHT:22px" align="center" width="7%">
                                        ${user.sexID}
                                </td>
                                <td style="HEIGHT:22px" align="center" width="15%">
                                        ${user.contactTel}
                                </td>
                                <td style="HEIGHT:22px" align="center" width="15%">
                                    <s:date name="%{onDutyDate}" var="onDutyDate1"/>
                                    <s:property value="%{onDutyDate1}"/>
                                </td>
                                <td style="HEIGHT:22px" align="center" width="8%">
                                        ${user.postID}
                                </td>
                                <td align="center" style="HEIGHT: 22px" align="center" width="10%">
                                    <a href="#"
                                       onclick="openWindow('${pageContext.request.contextPath }/system/userManage_edit.action?userID=${user.userID}','700','400');">
                                        <img src="${pageContext.request.contextPath }/images/edit.gif" border="0"
                                             style="CURSOR:hand"></a>
                                </td>
                                <td align="center" style="HEIGHT: 22px" align="center" width="10%">
                                    <a href="#"
                                       onclick="openWindow('${pageContext.request.contextPath }/system/userManage_edit.action?userID=${user.userID}&viewflag=true','700','400');">
                                        <img src="${pageContext.request.contextPath }/images/button_view.gif" width="20"
                                             height="18" border="0" style="CURSOR:hand"></a>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                </table>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>
