<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="f" uri="/WEB-INF/RoleTagLib.tld"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
    <tr>
        <td>
            <table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="dictTbl"
                   style="WIDTH:100%; WORD-BREAK:break-all; border: 1px solid gray;BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
                <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
                    <td class="ta_01" align="center" width="20%" height=22
                        background="${pageContext.request.contextPath }/images/tablehead.jpg">编号
                    </td>
                    <td class="ta_01" align="center" width="60%" height=22
                        background="${pageContext.request.contextPath }/images/tablehead.jpg">名称
                    </td>
                    <td class="ta_01" align="center" width="20%" height=22
                        background="${pageContext.request.contextPath }/images/tablehead.jpg">删除
                    </td>
                </tr>

                <s:if test="%{#request.systemDDList != null && #request.systemDDList.size>0}">
                    <s:iterator value="%{#request.systemDDList}" var="systemDD">
                        <tr>
                            <td class="ta_01" align="center" width="20%">${systemDD.ddCode}</td>
                            <td class="ta_01" align="center" width="60%">
                                <input id="itemname" name="itemname" type="text" value="${systemDD.ddValue}" size="45" maxlength="25"></td>
                            <td class="ta_01" align="center" width="20%">
                                <a href="#" onclick="delTableRow(${systemDD.ddCode})">
                                    <img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16"
                                         border="0" style="CURSOR:hand"></a>
                            </td>
                        </tr>
                    </s:iterator>
                </s:if>
                <s:else>
                    <tr>
                        <td class="ta_01" align="center" width="20%">1</td>
                        <td class="ta_01" align="center" width="60%">
                            <input name="itemname" type="text" size="45" maxlength="25"></td>
                        <td class="ta_01" align="center" width="20%"></td>
                    </tr>
                </s:else>

            </table>
        </td>
    </tr>
    <tr>
        <td>
        </td>
    </tr>
    <TR height=10>
        <td colspan=3></td>
    </TR>
    <tr>
        <td align="center" colspan=3>
            <f:if pattern="ec">
                <input type="button" name="saveitem" value="保存" style="font-size:12px; color:black; height:20px;width:50px"
                       onClick="returnMethod()">
            </f:if>
        </td>
    </tr>
    <input type="hidden" name="keywordname">
    <input type="hidden" name="typeflag">
</table>
