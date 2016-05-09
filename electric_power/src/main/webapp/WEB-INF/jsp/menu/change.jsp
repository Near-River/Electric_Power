<%@ page language="java" pageEncoding="UTF-8" %>
<HEAD>
    <META http-equiv=Content-Type content="text/html; charset=UTF-8">
    <META content="Microsoft FrontPage 4.0" name=GENERATOR>
    <META content=C# name=CODE_LANGUAGE>
    <META content=JavaScript name=vs_defaultClientScript>
    <META content=http://schemas.microsoft.com/intellisense/ie5 name=vs_targetSchema>
    <STYLE type=text/css>
        BODY {
            MARGIN: 0px;
            BACKGROUND-COLOR: #ffffff
        }
        BODY, TD, TH {
            COLOR: #000000
        }
        BODY {
            SCROLLBAR-FACE-COLOR: #cccccc;
            SCROLLBAR-HIGHLIGHT-COLOR: #ffffff;
            SCROLLBAR-SHADOW-COLOR: #ffffff;
            SCROLLBAR-3DLIGHT-COLOR: #cccccc;
            SCROLLBAR-ARROW-COLOR: #ffffff;
            SCROLLBAR-TRACK-COLOR: #ffffff;
            SCROLLBAR-DARKSHADOW-COLOR: #cccccc
        }
    </STYLE>
    <script language="javascript">
        function shiftWindow() {
            if (parent.document.getElementById("main").cols == "200,1%,*") {
                parent.document.getElementById("main").cols = '0,1%,99%';
                document.all.image.src = "${pageContext.request.contextPath }/images/you.gif";
            }
            else if (parent.document.getElementById("main").cols == "0,1%,99%") {
                parent.document.getElementById("main").cols = '200,1%,*';
                document.all.image.src = "${pageContext.request.contextPath }/images/zuo.gif";
            }
        }
    </script>
</HEAD>
<BODY MS_POSITIONING="GridLayout">
<table width=1 style="cursor: pointer" height="100%" background="" cellspacing="0" cellpadding="0">
    <tr>
        <td onclick="shiftWindow()" title="全屏/半屏" background="" width="20">
            <p align="center">
                <img id="image" src="${pageContext.request.contextPath }/images/zuo.gif" width="9" height="79">
            </p>
        </td>
    </tr>
</table>
</body>
</html>
