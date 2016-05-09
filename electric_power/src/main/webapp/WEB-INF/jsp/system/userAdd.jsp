<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>添加用户</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
    <Script language="javascript">
        function check_null() {
            var theForm = document.Form1;
            if (Trim(theForm.loginName.value) == "") {
                alert("登录名不能为空");
                theForm.loginName.focus();
                return false;
            }
            if (Trim(theForm.userName.value) == "") {
                alert("用户姓名不能为空");
                theForm.userName.focus();
                return false;
            }
            if (theForm.sexID.value == "") {
                alert("请选择性别");
                theForm.sexID.focus();
                return false;
            }
            if (theForm.jctID.value == "") {
                alert("请选择所属单位");
                theForm.jctID.focus();
                return false;
            }
            if (Trim(theForm.onDutyDate.value) == "") {
                alert("入职时间不能为空");
                theForm.onDutyDate.focus();
                return false;
            }
            if (theForm.postID.value == "") {
                alert("请选择职位");
                theForm.postID.focus();
                return false;
            }
            if (theForm.password.value != theForm.passwordconfirm.value) {
                alert("两次输入密码不一致，请重新输入");
                return;
            }
            if (checkNull(theForm.contactTel)) {
                if (!checkPhone(theForm.contactTel.value)) {
                    alert("请输入正确的电话号码");
                    theForm.contactTel.focus();
                    return false;
                }
            }
            if (checkNull(theForm.mobile)) {
                if (!checkMobilPhone(theForm.mobile.value)) {
                    alert("请输入正确的手机号码");
                    theForm.mobile.focus();
                    return false;
                }
            }
            if (checkNull(theForm.email)) {
                if (!checkEmail(theForm.email.value)) {
                    alert("请输入正确的EMail");
                    theForm.email.focus();
                    return false;
                }
            }
            if (theForm.remark.value.length > 250) {
                alert("备注字符长度不能超过250");
                theForm.remark.focus();
                return false;
            }
            var isDutySelect = document.getElementById("isDuty");
            //选择[是]
            if (isDutySelect.options[0].selected) {
                if (theForm.onDutyDate.value == "") {
                    alert("该用户属于在职人员，请填写入职时间");
                    theForm.onDutyDate.focus();
                    return false;
                }
            }
            //选择[否]
            if (isDutySelect.options[1].selected) {
                alert("选择离职，不允许新增用户操作！");
                theForm.isDuty.focus();
                return false;
            }
            document.Form1.action = "${pageContext.request.contextPath }/system/userManage_merge.action";
            document.Form1.submit();
        }
        function checkTextAreaLen() {
            var remark = new Bs_LimitedTextarea('remark', 250);
            remark.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
            remark.draw();
        }
        window.onload = function () {
            checkTextAreaLen();
        };

        //ajax的二级联动，使用选择的所属单位，查询该所属单位下对应的单位名称列表
        function findJctUnit(o) {
            var jct = $(o).find("option:selected").text();
            $.post("${pageContext.request.contextPath }/system/userManage_findJctUnit.action", {"jctID": jct}, function (data) {
                //先删除单位名称的下拉菜单，但是请选择要留下
                $('#jctUnitID option').remove();
                $('#jctUnitID').append('<option>请选择</option>');
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var ddCode = data[i].ddCode;
                        var ddValue = data[i].ddValue;
                        //添加到单位名称的下拉菜单中
                        var opt = $("<option></option>");
                        opt.attr("value", ddCode);
                        opt.text(ddValue);
                        $('#jctUnitID').append(opt);
                    }
                }
            });
        }

        /**校验登录名是否出现重复*/
        function checkUser(o) {
            //alert(o.value);  //dom的写法
            //alert($(o).val());  //jquery的写法
            var loginName = $(o).val();
            $.post("${pageContext.request.contextPath }/system/userManage_checkUser.action", {"loginName": loginName}, function (data) {
                if (data == 1) {
                    $("#check").html('<span style="color: red; ">登录名不能为空</span>');
                    o.focus();
                    $("#BT_Submit").attr("disabled", "none");
                }
                else if (data == 2) {
                    $("#check").html('<span style="color: red; ">登录名已经存在</span>');
                    o.focus();
                    $("#BT_Submit").attr("disabled", "none");
                }
                else {
                    $("#check").html('<span style="color: green; ">登录名可以使用</span>');
                    $("#BT_Submit").attr("disabled", "");
                }
            });
        }
    </script>
</head>
<body>
<form name="Form1" method="post">
    <br>
    <table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3"
           border="0">
        <tr>
            <td class="ta_01" align="center" colSpan="4"
                background="${pageContext.request.contextPath }/images/b-info.gif">
                <span style="font-family: 宋体; font-size: x-small; "><strong>添加用户</strong></span>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：
                <span style="color: #FF0000; ">*</span></td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="loginName" id="loginName" maxlength="25" size="20" onblur="checkUser(this);"/>
                <div id="check"></div>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<span style="color: #FF0000; ">*</span>
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="userName" maxlength="25" id="userName" size="20"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">性别：<span style="color: #FF0000; ">*</span></td>
            <td class="ta_01" bgColor="#ffffff">
                <s:select list="#request.sexList" name="sexID" id="sexID"
                          listKey="ddCode" listValue="ddValue"
                          headerKey="" headerValue=""
                          cssStyle="width:155px"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">职位：<span style="color: #FF0000; ">*</span></td>
            <td class="ta_01" bgColor="#ffffff">
                <s:select list="#request.postList" name="postID" id="postID"
                          listKey="ddCode" listValue="ddValue"
                          headerKey="" headerValue=""
                          cssStyle="width:155px"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">所属单位：<span style="color: #FF0000; ">*</span></td>
            <td class="ta_01" bgColor="#ffffff">
                <s:select list="#request.jctList" name="jctID" id="jctID"
                          listKey="ddCode" listValue="ddValue"
                          headerKey="" headerValue=""
                          cssStyle="width:155px"
                          onchange="findJctUnit(this)"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">单位名称：<span style="color: #FF0000; ">*</span></td>
            <td class="ta_01" bgColor="#ffffff">
                <select id="jctUnitID" name="jctUnitID" style="width:155px"></select>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:password name="password" id="password" maxlength="25" size="20"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:password name="passwordconfirm" id="passwordconfirm" maxlength="25" size="20"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="birthday" id="birthday" maxlength="50" size="20" onClick="WdatePicker()"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="address" maxlength="50" size="20"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="contactTel" maxlength="25" size="20"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">手机：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="mobile" maxlength="25" size="20"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="email" maxlength="50" size="20"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:select list="#request.isDutyList" name="isDuty" id="isDuty"
                          listKey="ddCode" listValue="ddValue"
                          value="1" cssStyle="width:155px"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">入职日期：<span style="color: #FF0000; ">*</span></td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="onDutyDate" id="onDutyDate" maxlength="50" size="20" onClick="WdatePicker()"/>
            </td>
            <td align="center" bgColor="#ffffff" class="ta_01"></td>
            <td class="ta_01" bgColor="#ffffff">
            </td>
        </tr>
        <TR>
            <TD class="ta_01" align="center" bgColor="#f5fafe">备注：</TD>
            <TD class="ta_01" bgColor="#ffffff" colSpan="3">
                <s:textarea name="remark" cssStyle="WIDTH:95%" rows="4" cols="52"/>
            </TD>
        </TR>
        <TR>
            <td align="center" colSpan="4" class="sep1"></td>
        </TR>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                <input type="button" id="BT_Submit" name="BT_Submit" value="保存"
                       style="font-size:12px; color:black; height:22px;width:55px" onClick="check_null()">
                <span style="font-family: 宋体; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input style="font-size:12px; color:black; height:22px;width:55px" type="button" value="关闭" name="Reset1"
                       onClick="window.close()">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
