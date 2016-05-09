<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="filesTb2" align="center"
       style="WIDTH:700px; WORD-BREAK:break-all; border: 1px solid gray;BORDER-COLLAPSE:collapse;
       BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
    <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
        <td class="ta_01" align="center" width="20%"
            background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
            编号
        </td>
        <td class="ta_01" align="center" width="80%"
            background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
            已上传文件
        </td>
    </tr>
    <s:if test="#request.uploadList!=null && #request.uploadList.size>0">
        <s:iterator value="#request.uploadList" var="upload" status="status">
            <tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
                <td class="ta_01" align="center" width="20%">
                    ${status.index+1}
                </td>
                <td class="ta_01" align="center" width="80%">
                    <a href="${pageContext.request.contextPath}/dataChart/fileManage_download.action?seqId=${upload.seqId}">${upload.fileName}</a>
                </td>
            </tr>
        </s:iterator>
    </s:if>
</table>
