<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet"/>
    <style type="text/css">
        <!--
        body {
            background-color: #FFFFFF;
            margin: 0px;
        }
        body, td, th {
            color: #000000;
        }
        -->
        BODY {
            SCROLLBAR-FACE-COLOR: #cccccc;
            SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF;
            SCROLLBAR-SHADOW-COLOR: #ffffff;
            SCROLLBAR-3DLIGHT-COLOR: #cccccc;
            SCROLLBAR-ARROW-COLOR: #ffffff;
            SCROLLBAR-TRACK-COLOR: #ffffFF;
            SCROLLBAR-DARKSHADOW-COLOR: #cccccc;
        }
    </style>
    <script type="text/javascript">
        function shiftIframe(value) {
            if (value == 'station') {
                if (document.all.station.width == 500) {
                    document.all.station.width = 1100;
                    document.getElementById("devtd").style.display = "none";
                }
                else if (document.all.station.width == 1100) {
                    document.all.station.width = 500;
                    document.getElementById("devtd").style.display = "";
                }
            }
            else if (value == 'device') {
                if (document.all.dev.width == 500) {
                    document.all.dev.width = 1100;
                    document.getElementById("stationtd").style.display = "none";
                }
                else if (document.all.dev.width == 1100) {
                    document.all.dev.width = 500;
                    document.getElementById("stationtd").style.display = "";
                }
            }
        }
        /*添加每隔10分钟 自动刷新页面效果，保证站点和设备运行数据的实时性*/
        window.onload = function () {
            setTimeout('refreshPage()', 1000 * 60 * 10);
        };
        function refreshPage() {
            window.location.reload();
        }
    </script>
    <link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<form method="post" id="Form1">
    <table width="100%" border="0" height="88" border="1"
           background=${pageContext.request.contextPath }/images/back1.jpg>
        <tr>
            <td colspan=3 class="ta_01" align="center"
                background="${pageContext.request.contextPath }/images/b-info.gif"><strong>系统首页</strong></td>
        </tr>
        <tr>
            <td width="50%" height="84" align="left" valign="top" id="stationtd">
                <fieldset id="stationset"
                          style="width: 500px; height: 430px; padding: 1px; background:${pageContext.request.contextPath }/images/back1.JPG">
                    <legend>
                        <span style="color: #0000FF; ">
                            <img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14"
                                 height="14">
                            <a href="javascript:void(0);" onclick="shiftIframe('station')">站点运行情况</a>
                        </span>
                    </legend>
                    <IFRAME src="${pageContext.request.contextPath }/system/menuManage_alermStation.action"
                            name="station" id="station" frameBorder="0" width="500" scrolling="auto" height="400"></IFRAME>
                </fieldset>
            </td>
            <td width="50%" align="left" valign="top" id="devtd">
                <fieldset id="devset"
                          style="width: 500px; height: 430px; padding: 1px; background:${pageContext.request.contextPath }/images/back1.JPG">
                    <legend>
                        <span style="color: #0000FF;">
                            <img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14"
                                 height="14">
                            <a href="javascript:void(0);" onclick="shiftIframe('device')">设备运行情况</a></span>
                    </legend>
                    <IFRAME src="${pageContext.request.contextPath }/system/menuManage_alermDevice.action" name="dev"
                            id="dev" frameBorder="0" width="500" scrolling="auto" height="400"></IFRAME>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td height=2></td>
        </tr>
    </table>
</form>
</body>
</html>
