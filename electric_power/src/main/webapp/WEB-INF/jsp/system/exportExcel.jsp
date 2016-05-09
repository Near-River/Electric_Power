<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>导出设置</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        //        function Add() {
        //            allRoles = document.getElementById("colname1");
        //            selectRoles = document.getElementById("colname2");
        //
        //            for (var i = 0; i < allRoles.options.length; i++) {
        //                if (allRoles.options[i].selected == true) {
        //                    selectRoles.options.add(new Option(allRoles.options[i].text, allRoles.options[i].value));
        //                    allRoles.remove(i);
        //                    i--;
        //                }
        //            }
        //        }
        //
        //        function Remove() {
        //            allRoles = document.getElementById("colname1");
        //            selectRoles = document.getElementById("colname2");
        //
        //            for (var i = 0; i < selectRoles.options.length; i++) {
        //                if (selectRoles.options[i].selected == true) {
        //                    allRoles.options.add(new Option(selectRoles.options[i].text, selectRoles.options[i].value));
        //                    selectRoles.remove(i);
        //                    i--;
        //                }
        //            }
        //        }
        //
        //        function upcol() {
        //            var rightcol = document.getElementById("colname2");
        //            var selectflag = 0;
        //
        //            for (var i = 0; i < rightcol.options.length; i++) {
        //                if (rightcol.options[i].selected == true && i != 0) {
        //                    var temptext = rightcol.options[i].text;
        //                    var tempvalue = rightcol.options[i].value;
        //
        //                    rightcol.options[i].value = rightcol.options[i - 1].value;
        //                    rightcol.options[i].text = rightcol.options[i - 1].text;
        //                    rightcol.options[i - 1].value = tempvalue;
        //                    rightcol.options[i - 1].text = temptext;
        //
        //                    selectflag = i - 1;
        //                    break; //这个标志表明目前只能一次移一行，不支持多选
        //                }
        //            }
        //            for (var i = 0; i < rightcol.options.length; i++) {
        //                rightcol.options[i].selected = false;
        //            }
        //            rightcol.options[selectflag].selected = true;
        //        }
        //
        //        function downcol() {
        //            var rightcol = document.getElementById("colname2");
        //            var selectflag = 0;
        //
        //            for (var i = 0; i < rightcol.options.length; i++) {
        //                if (rightcol.options[i].selected == true && i != rightcol.options.length - 1) {
        //                    var temptext = rightcol.options[i].text;
        //                    var tempvalue = rightcol.options[i].value;
        //
        //                    rightcol.options[i].value = rightcol.options[i + 1].value;
        //                    rightcol.options[i].text = rightcol.options[i + 1].text;
        //                    rightcol.options[i + 1].value = tempvalue;
        //                    rightcol.options[i + 1].text = temptext;
        //
        //                    selectflag = i + 1;
        //                    break; //这个标志表明目前只能一次移一行，不支持多选
        //                }
        //            }
        //            if (selectflag != 0) { //如果此标志为0，说明光标已经移到最下边，没有发生向下交换的行动
        //                for (var i = 0; i < rightcol.options.length; i++) {
        //                    rightcol.options[i].selected = false;
        //                }
        //                rightcol.options[selectflag].selected = true;
        //            }
        //        }

        function Add() {
            $("#colname1").find("option:selected").appendTo($("#colname2"));
        }

        function Remove() {
            $("#colname2").find("option:selected").appendTo($("#colname1"));
        }

        function upcol() {
            //获取选中的右侧option元素
            var rightcol = $("#colname2").find("option:selected");
            //option的第一个元素无法上移，rightcol.get(0)表示选中的option对象，rightcol.get(0).index表示option对象的位置索引
            if (rightcol.get(0).index != 0) {
                rightcol.each(function () {
                    //$(this).prev().before($(this));  //在当前选中对象的前面插入该对象
                    $(this).insertBefore($(this).prev());  //等同
                });
            }
        }

        function downcol() {
            //选择所有的对象
            var allrightcol = $("#colname2 option");
            //选择被选中的对象
            var rightcol = $("#colname2").find("option:selected");
            //option的最后一个元素无法下移
            if (rightcol.get(rightcol.length - 1).index != allrightcol.length - 1) {
                //循环选中的对象
                for (i = rightcol.length - 1; i >= 0; i--) {
                    //获取选中的对象
                    var item = $(rightcol.get(i));
                    item.insertAfter(item.next());  //将选中的对象插入到下一个对象的后面
                    //item.next().after(item);  //等同
                }
            }
        }


        function setValue() {
            var rightcol = document.getElementById("colname2");
            var leftcol = document.getElementById("colname1");

            var m;
            var selectid = "", selectname = "", noselectid = "", noselectname = "";
            for (m = 0; m < rightcol.options.length; m++) {
                if (m == rightcol.options.length - 1) {
                    selectid += rightcol.options[m].value;
                    selectname += rightcol.options[m].text;
                } else {
                    selectid += rightcol.options[m].value + "#";
                    selectname += rightcol.options[m].text + "#";
                }
            }
            for (m = 0; m < leftcol.options.length; m++) {
                if (m == leftcol.options.length - 1) {
                    noselectid += leftcol.options[m].value;
                    noselectname += leftcol.options[m].text;
                } else {
                    noselectid += leftcol.options[m].value + "#";
                    noselectname += leftcol.options[m].text + "#";
                }
            }
            document.Form1.expNameList.value = selectname;
            document.Form1.expFieldName.value = selectid;
            document.Form1.noExpNameList.value = noselectname;
            document.Form1.noExpFieldName.value = noselectid;
        }

        function checksubmit() {
            setValue();
            if (document.Form1.expNameList.value == "" || document.Form1.expFieldName.value == "") {
                alert("请至少选择一列作为导出列");
                return;
            }
            $("#Form1").attr('action', '${pageContext.request.contextPath}/system/exportExcel_saveExcelFields.action').submit();
        }
    </script>
</head>

<body onload="setValue()">
<FORM id="Form1" name="Form1" method="POST">
    <br>
    <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
            <td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
                <span style="font-family: 宋体; font-size: x-small; "><strong>导出字段设置</strong></span>
            </td>
        </tr>
        <tr height=10>
            <td></td>
        </tr>
        <tr>
            <td width="100%">
                <table border="0" width="100%" cellspacing="3" cellpadding="0">
                    <tr height=10>
                        <TD width="1%"></TD>
                        <TD width="30%" class="DropShadow" align="left"
                            background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img
                                src="${pageContext.request.contextPath }/images/yin.gif" width="15">未导出字段列表
                        </TD>
                        <td width="16%">
                        <TD width="34%" class="DropShadow" align="left"
                            background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img
                                src="${pageContext.request.contextPath }/images/yin.gif" width="15">导出字段列表
                        </TD>
                        <td width="19%">
                    </tr>
                    <tr>
                        <td width="1%"></td>
                        <td width="84%" colspan="4">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="30%" rowspan="4">
                                        <s:select list="%{#attr.excludeMap}" size="15" name="colname1" multiple="true"
                                                  cssStyle="width:200px" id="colname1"
                                                  ondblclick="Add('colname1','colname2','colname')"/>

                                    </td>
                                    <td width="15%"></td>
                                    <td width="35%" rowspan="4" id="colnameDiv">
                                        <s:select list="%{#attr.includeMap}" size="15" name="colname2" multiple="true"
                                                  cssStyle="width:200px" id="colname2"
                                                  ondblclick="Add('colname1','colname2','colname')"/>

                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="90" align="center">
                                        <input name="DoAddb" type="button" value=">>" onClick="Add()"
                                               class=btn_mouseout onmouseover="this.className='btn_mouseover'"
                                               onmouseout="this.className='btn_mouseout'"
                                               style="width: 30px; font-size:12px; color:black; height:22px">
                                    </td>
                                    <td width="120" align="center">
                                        <input name="doup" type="button" value="向上" onClick="upcol()" class=btn_mouseout
                                               onmouseover="this.className='btn_mouseover'"
                                               onmouseout="this.className='btn_mouseout'"
                                               style="width: 50px; font-size:12px; color:black; height:22px">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="90" align="center">
                                        <input name="DoDelb" type="button" value="<<" onClick="Remove()"
                                               class=btn_mouseout onmouseover="this.className='btn_mouseover'"
                                               onmouseout="this.className='btn_mouseout'"
                                               style="width: 30px; font-size:12px; color:black; height:22px">
                                    </td>
                                    <td width="120" align="center">
                                        <input name="dodown" type="button" value="向下" onClick="downcol()"
                                               class=btn_mouseout onmouseover="this.className='btn_mouseover'"
                                               onmouseout="this.className='btn_mouseout'"
                                               style="width: 50px; font-size:12px; color:black; height:22px">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="73">
                                        <input type="hidden" name="belongTo" id="belongTo" value="${model.belongTo}">
                                        <input type="hidden" name="expNameList" id="expNameList"/>
                                        <input type="hidden" name="expFieldName" id="expFieldName"/>
                                        <input type="hidden" name="noExpNameList" id="noExpNameList"/>
                                        <input type="hidden" name="noExpFieldName" id="noExpFieldName"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr height=10>
            <td></td>
        </tr>
        <tr height=20>
            <td background="${pageContext.request.contextPath }/images/b-info.gif"></td>
        </tr>
        <tr height=10>
            <td></td>
        </tr>
        <tr>
            <td align="center">
                <INPUT type="button" name="save" id="save" value="保存" onclick="checksubmit()"
                       style="width: 60px; font-size:12px; color:black; height:22px">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input style="width: 60px; font-size:12px; color:black; height:22px" type="reset" value="关闭" id="Reset1"
                       name="Reset1" onclick="window.close();">
            </td>
        </tr>
    </table>
</FORM>
</body>
</html>
