<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
    <tr>
        <td>
            <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;">
                <legend align="left"><a href="#" onclick="displaypermission()"><span id="permissionflag">权限分配&nbsp;&nbsp;关闭</span></a></legend>
                <table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0"
                       id="dataPopedom">
                    <tr>
                        <td class="ta_01" colspan=2 align="left" width="100%">
                            全选/全不选<input type="checkbox" name="selectOperAll" onclick="checkAllOper(this)">
                            <br>
                            <table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all"
                                   bordercolor="gray" border="1" style="WORD-BREAK:break-all; border: 1px solid gray;BORDER-COLLAPSE:collapse;
                                   BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">

                                <s:if test="%{#request.popedomList!=null && #request.popedomList.size>0}">
                                    <s:set value="1" var="flag" scope="request"/>
                                    <s:iterator value="%{#request.popedomList}" var="popedom">
                                        <!-- 父节点 -->
                                        <s:if test="%{parent == true}">
                                            <s:if test="#request.flag==2">
                                                </td></tr>
                                                <s:set value="1" var="flag" scope="request"/>
                                            </s:if>
                                            <tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                                <td class="ta_01" align="left" width="18%" height="22" background="${pageContext.request.contextPath }/images/tablehead.jpg">
                                                    <input type="checkbox" name="selectoper" id="${popedom.mid}_${popedom.mid}"
                                                            <s:if test="%{hasPrivilege == true}">
                                                                checked
                                                            </s:if>
                                                           value="${popedom.mid}_${popedom.pid}" onClick='goSelect(this.id)'>
                                                        ${popedom.name}：
                                        </s:if>
                                        <!-- 子节点 -->
                                        <s:else>
                                            <s:if test="#request.flag==1">
                                                </td>
                                                <td class="ta_01" align="left" width="82%" height="22">
                                                <s:set value="2" var="flag" scope="request"/>
                                            </s:if>
                                                <div>
                                                    <input type="checkbox" name="selectoper" id="${popedom.pid}_${popedom.mid}"
                                                            <s:if test="%{hasPrivilege == true}">
                                                                   checked
                                                            </s:if>
                                                           value="${popedom.mid}_${popedom.pid}" onClick='goSelect(this.id)'>
                                                        ${popedom.name}
                                                </div>
                                        </s:else>
                                    </s:iterator>
                                </s:if>

                            </table>
                        </td>
                    </tr>
                    <input type="hidden" name="roleID">
                </table>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td height=10></td>
    </tr>
    <tr>
        <td>
            <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;">
                <legend align="left"><a href="#" onclick="displayuser()"><span id="userflag">用户分配&nbsp;&nbsp;关闭</span></a></legend>
                <table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all" bordercolor="gray"
                       border="1" id="dataUser"
                       style="WORD-BREAK:break-all; border: 1px solid gray;BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
                    <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
                        <td class="ta_01" align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
                            选中<input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)"></td>
                        <td class="ta_01" align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
                            登录名
                        </td>
                        <td class="ta_01" align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
                            用户姓名
                        </td>
                    </tr>
                    <s:if test="%{#request.userList!=null && #request.userList.size>0}">
                        <s:iterator value="#request.userList" var="user">
                            <tr onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="HEIGHT: 22px" class="ta_01" align="center" width="20%">
                                    <input type="checkbox" name="selectuser" value="${user.userID}" <s:if test="#user.belongTo == true">checked</s:if> />
                                </td>
                                <td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
                                    ${user.loginName}
                                </td>
                                <td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
                                    ${user.userName}
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                </table>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td class="ta_01" align="center" colspan=3 width="100%" height=20>
            <input type="button" name="saverole" onclick="saveRole()"
                   style="font-size:12px; color:black; height:20px;width:50px" value="保存">
        </td>
    </tr>
</table>
