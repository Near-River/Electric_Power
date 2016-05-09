<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>编辑用户</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/DatePicker/WdatePicker.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
    <Script language="javascript">
        function check_null() {
            var theForm = document.Form1;
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
            //如果是否在职选择是，则入职时间必须要填写，如果是否在职选择否，则离职日期必须要填写
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
            else {
                if (theForm.offDutyDate.value == "") {
                    alert("该用户属于离职人员，请填写离职时间");
                    theForm.offDutyDate.focus();
                    return false;
                }
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
        }
        /**如果选择离职时间，则【是否在职】默认选择"否"，如果没有选择离职时间，则【是否在职】默认选择"是"*/
        function checkIsDuty(o) {
            var offDutyDate = o.value;
            var isDutySelect = document.getElementById("isDuty");
            if (offDutyDate != "") {
                isDutySelect.options[1].selected = true; //否
            }
            else {
                isDutySelect.options[0].selected = true; //是
            }
        }
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
    </script>
</head>
<body>

<s:if test="%{#request.viewflag == 'true'}">
    <script type="text/javascript">
        $(function () {
            $("#Form1 input").attr('disabled', 'true');
            $("#Form1 select").attr('disabled', 'true');
            $("#Form1 textarea").attr('disabled', 'true');
            $("#Form1 input[type=button]").attr('disabled', '');
            // $("#Form1 input").attr('readonly', 'readonly');

        });
    </script>
</s:if>

<form name="Form1" id="Form1" method="post">
    <br>
    <table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3"
           border="0">
        <tr>
            <td class="ta_01" align="center" colSpan="4"
                background="${pageContext.request.contextPath }/images/b-info.gif">
                <span style="font-family: 宋体; font-size: x-small; ">
                    <strong>
                        <s:if test="%{#request.viewflag == 'true'}">
                            查看用户明细
                        </s:if>
                        <s:else>
                            编辑用户
                        </s:else>
                    </strong>
                </span>
            </td>
        </tr>
        <s:hidden name="userID" value="%{model.userID}"/>
        <s:hidden name="oldPwd" value="%{model.password}"/>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="loginName" id="loginName" maxlength="25" size="20" onblur="checkUser(this);"
                             readonly="true"/>
                <span style="color: #FF0000; ">*</span></td>
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
                <s:select list="#request.jetUnitList" name="jctUnitID" id="jctUnitID"
                          listKey="ddCode" listValue="ddValue"
                          cssStyle="width:155px"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:password name="password" id="password" maxlength="25" size="20" showPassword="true"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:password name="passwordconfirm" id="passwordconfirm" maxlength="25" size="20"
                            value="%{model.password}" showPassword="true"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:date name="%{model.birthday}" var="birthDay" format="yyyy-MM-dd"/>
                <s:textfield name="birthday" id="birthday" maxlength="50" size="20" onClick="WdatePicker()"
                             value="%{birthDay}"/>
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
            <s:date name="%{model.onDutyDate}" var="onDuty" format="yyyy-MM-dd"/>
            <s:date name="%{model.offDutyDate}" var="offDuty" format="yyyy-MM-dd"/>
            <td align="center" bgColor="#f5fafe" class="ta_01">入职日期：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="onDutyDate" id="onDutyDate" maxlength="50" size="20" onClick="WdatePicker()"
                             value="%{onDuty}"/>
                <span style="color: #FF0000; ">*</span>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">离职日期：</td>
            <td class="ta_01" bgColor="#ffffff">
                <s:textfield name="offDutyDate" id="offDutyDate" maxlength="50" size="20" onClick="WdatePicker()"
                             value="%{offDuty}" onblur="checkIsDuty(this)"/>
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
                <s:if test="%{#request.viewflag != 'true'}">
                    <input type="button" id="BT_Submit" name="BT_Submit" value="保存"
                           style="font-size:12px; color:black; height=22;width=55" onClick="check_null()">
                    <span style="font-family: 宋体; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                </s:if>
                <input style="font-size:12px; color:black; height=22;width=55" type="button" value="关闭" name="Reset1"
                       onClick="window.close()">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
