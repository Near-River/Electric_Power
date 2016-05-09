<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户按照所属单位分报表统计</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script language="JavaScript" src="${pageContext.request.contextPath }/script/FusionCharts.js"></script>
</head>
<body>
<center>
    <fieldset style="width: 600px; height: 500px; padding: 1px;
            background:${pageContext.request.contextPath }/images/back1.JPG">
        <legend>
            <span style="color: #0000FF; ">
                <img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14">
                报表统计</span>
        </legend>
        <div id="chartdiv" style="align:center;"></div>
        <s:hidden id="data" value="%{#request.chart}"/>
        <script type="text/javascript">
            var myChart = new FusionCharts("${pageContext.request.contextPath }/fusionCharts/FCF_Column3D.swf", "myChartId", "600", "500");
            var di = document.getElementById("data").value;
            myChart.setDataXML(di);
            myChart.render("chartdiv");
        </script>
    </fieldset>
</center>
</body>
</html>