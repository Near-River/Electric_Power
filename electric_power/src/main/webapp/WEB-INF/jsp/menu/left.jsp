<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>左侧菜单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script language="JavaScript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath }/script/jquery-ztree-2.5.js"></script>
    <%--<script language="JavaScript" src="${pageContext.request.contextPath }/script/menuData.js"></script>--%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript">
        var menu = {
            setting: {
                isSimpleData: true,
                treeNodeKey: "mid",
                treeNodeParentKey: "pid",
                showLine: true,
                root: {
                    isRoot: true,
                    nodes: []
                }
            },
            loadMenuTree: function () {
                $.post('${pageContext.request.contextPath }/system/menuManage_showMenu.action', null, function (data) {
                    $("#menuTree").zTree(menu.setting, data);
                });
            }
        };

        $(function () {
            menu.loadMenuTree();
        });
    </script>
</head>
<body style="margin: 0">
<TABLE border=0 width="20">
    <TR>
        <TD width=340px align=center valign=top>
            <div class="zTreeDemoBackground">
                <ul id="menuTree" class="tree"></ul>
            </div>
        </TD>
    </TR>
</TABLE>
</body>
</html>

