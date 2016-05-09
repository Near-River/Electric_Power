<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>资料图纸管理</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
    <script language="javascript">
        /*
         function getUrl(ssdw, filename) {
         var strUrl = "";
         strUrl = "${pageContext.request.contextPath }/UploadFile/Paper/" + ssdw + "/";
         strUrl = strUrl + filename;
         openWindow(strUrl, 800, 450);
         }
         function onloadForm2() {
         var str = 'informationAndPaper.do?pageNO=1&pageSize=10';
         Pub.submitActionWithForm('Form2', str, 'Form1');
         }
         */
        function returnMethod() {
            if (event.keyCode == 13) return false;
        }
        function returnConfirm(name) {
            return confirm("你确定要删除 " + name + "吗？");
        }
    </script>
</head>

<body>
<s:form name="Form1" namespace="/dataChart" action="fileManage_luceneIndex.action" id="Form1"
        cssStyle="margin:0;">
    <table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
        <tr>
            <td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
                <span style="font-family: 宋体; font-size: x-small; "><strong>资料图纸管理</strong></span>
            </td>
        </tr>
        <TR height=10>
            <td></td>
        </TR>
        <tr>
            <td>
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                            所属单位：
                        </td>
                        <td class="ta_01">
                            <s:select list="%{#request.jctList}" name="proId" id="proId"
                                      listKey="ddCode" listValue="ddValue"
                                      headerKey="0" headerValue="全部"
                                      cssStyle="width:160px">
                            </s:select>
                        </td>
                        <td width="100" class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                            图纸类别：
                        </td>
                        <td class="ta_01">
                            <span style="font-family: 宋体; color: red; ">
                                <s:select list="%{#request.dataChart}" name="belongTo" id="belongTo"
                                          listKey="ddCode" listValue="ddValue"
                                          headerKey="0" headerValue="全部"
                                          cssStyle="width:160px">
                                </s:select>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                            按文件名称和描述搜素：
                        </td>
                        <td class="ta_01">
                            <s:textfield name="queryString" id="queryString" size="21"
                                         onkeydown="returnMethod()"/>
                        </td>
                        <td width="100" colspan="2" class="ta_01" align="center" bgcolor="#f5fafe" height="22">
                            &nbsp;
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</s:form>
<br>
<s:form name="Form2" id="Form2" cssStyle="margin:0px;">
    <table cellspacing="0" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
        <TR>
            <TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=25><img
                    src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
            <TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=100>
                资料图纸列表
            </TD>
            <td class="ta_01" align="right" bgcolor="#ffffff">
                注意：由于资源数据比较多，请指定条件查询结果-------------
                <input type="button" name="BT_Search" value="查询"
                       style="font-size:12px; color:black; height:20px;width:50px"
                       id="BT_Search" onclick="document.Form1.submit();">
                <input id="BT_Add" type="button" value="添加" name="BT_Add"
                       style="font-size:12px; color:black; height:20px;width:50px"
                       onclick="openWindow('${pageContext.request.contextPath }/dataChart/fileManage_add.action',900,600);">
            </td>
        </TR>
    </table>
    <table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
        <tr>
            <td class="ta_01" align="center" bgcolor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
                       style="WIDTH:100%; WORD-BREAK:break-all; border: 1px solid gray;BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
                    <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
                        <td align="center" width="6%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">序号
                        </td>
                        <td align="center" width="10%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位
                        </td>
                        <td align="center" width="10%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">图纸类别
                        </td>
                        <td align="center" width="48%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">图纸名称
                        </td>
                        <td align="center" width="10%" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">描述
                        </td>
                        <td width="6%" align="center" height=22
                            background="${pageContext.request.contextPath }/images/tablehead.jpg">删除
                        </td>
                    </tr>
                    <s:if test="#request.uploadList!=null && #request.uploadList.size>0">
                        <s:iterator value="#request.uploadList" var="upload" status="st">
                            <tr onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td align="center" width="6%"><s:property value="#st.index+1"/></td>
                                <td align="center" width="10%"><s:property value="#upload.projId"/></td>
                                <td align="center" width="10%"><s:property value="#upload.belongTo"/></td>
                                <td align="center" width="48%">
                                    <a href="${pageContext.request.contextPath}/dataChart/fileManage_download.action?seqId=${upload.seqId}">
                                            ${upload.fileName}</a></td>
                                <td align="center" width="20%">
                                    <s:property value="#upload.comment" escapeHtml="false"/></td>
                                <td align="center" style="HEIGHT: 21px">
                                    <a href="${pageContext.request.contextPath }/dataChart/fileManage_delete.action?seqId=${upload.seqId}"
                                       onclick="return returnConfirm('<s:property value="#upload.fileName"/>')">
                                        <img src="${pageContext.request.contextPath }/images/delete.gif" width="16"
                                             height="16" border="0" style="CURSOR:hand"></a>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                </table>
            </td>
        </tr>
    </table>
</s:form>
</body>
</html>
