package electric.action.systemAction;

import electric.annotation.PopedomLimit;
import electric.base.action.BaseAction;
import electric.entity.SystemDD;
import electric.entity.User;
import electric.util.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author near on 2016/3/17.
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    private String oldPwd;

    private String[] userIDs;

    /*merge 操作的flag*/
    private String viewflag;

    /*用于接收POI创建的Excel数据流*/
    private ByteArrayOutputStream os;

    /*接收上传的Excel文件*/
    private File excel;

    @PopedomLimit(mid = "fa", pid = "0")
    public String index() {
        /* 查询条件：姓名、入职时间 ~ 离职时间、所属单位 */
        // List<UserVo> userList = userService.searchAllUsers(model);
        List<User> userList = userService.searchAllUsersPlus(model);
        request.setAttribute("userList", userList);

        /*关联查询所有单位信息*/
        List<SystemDD> systemDDList = systemDDService.findByKeyword("所属单位");
        request.setAttribute("systemDDList", systemDDList);

        return "index";
    }

    @PopedomLimit(mid = "fb", pid = "fa")
    public String add() {
        this.initSystemDD();

        return "add";
    }

    /**
     * 根据单位名称级联查询其下所有公司 对应的数据字典对象的集合
     *
     * @return JSON 数据
     */
    public String findJctUnit() {
        String jctID = model.getJctID();
        if (jctID != null) {
            List<SystemDD> systemDDList = systemDDService.findByKeyword(jctID);
            ValueStackUtils.pushStack(systemDDList);
        }

        return "findJctUnit";
    }

    /**
     * AJAX 校验用户名是否可用
     */
    public String checkUser() {
        String message = null;
        String loginName = model.getLoginName();
        if (loginName == null || "".equals(loginName)) {
            message = "1";
        } else {
            User user = userService.findByLoginName(model.getLoginName());
            if (user != null) {
                message = "2";
            } else {
                message = "3";
            }
        }
        ValueStackUtils.pushStack(message);

        return "checkUser";
    }

    public String merge() {
        if (model.getUserID() == null) {
            model.setUserID(UUIDUtils.getUUID());
            //若没设置密码，默认密码为 1234
            if (StringUtils.isBlank(model.getPassword())) {
                model.setPassword("1234");
            }
            model.setPassword(DigestUtils.md5DigestAsHex(model.getPassword().getBytes()));
            userService.save(model);
        } else {
            String password = model.getPassword();
            if (oldPwd.equals(password)) {
                model.setPassword(oldPwd);
            } else {
                model.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            }
            User user = userService.findById(model.getUserID());
            model.setDelete(user.isDelete());
            model.setRoles(user.getRoles());
            model.setLoginState(user.getLoginState());

            userService.update(model);
        }

        return "close";
    }

    /*执行批量删除*/
    public String delete() {
        if (userIDs != null) {
            List<User> userList = userService.findByIds(userIDs);
            userService.delete(userList);
        }

        return "delete";
    }

    public String edit() {
        String userId = model.getUserID();
        if (userId != null) {
            this.initSystemDD();

            model = userService.findById(userId);
            ValueStackUtils.pushStack(model);
            String jctUnitName = systemDDService.findByKeywordAndDdCode("所属单位", model.getJctID());
            List<SystemDD> systemDDList = systemDDService.findByKeyword(jctUnitName);
            request.setAttribute("jetUnitList", systemDDList);

            request.setAttribute("viewflag", viewflag);
        }

        return "edit";
    }

    /**
     * 根据数据字典内的数据字段导出Excel表格
     */
    public String exportExcel() throws Exception {
        String belongTo = request.getParameter("belongTo");
        ArrayList<String> exportExcelName = userService.getExportExcelName(belongTo);
        ArrayList<ArrayList<String>> exportExcelData = userService.getExportExcelData(belongTo, model);
        //导出excel
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(exportExcelName, exportExcelData);

        os = new ByteArrayOutputStream();
        excelFileGenerator.exportExcel(os);

        return "exportExcel";
    }

    public String importPage() {
        return "importPage";
    }

    /**
     * 将用户上传的Excel文件数据导入到数据库中
     */
    public String importExcel() {
        try {
            if (excel != null) {
                //从excel中读取数据，并将数据存放到ArrayList中
                ArrayList<String[]> arrayList = GenerateImportExcel.generateUserSql(excel);
                //定义一个错误的集合List<String>，存放错误信息，如果excel文件存在错误，就将错误的集合的在页面中显示
                List<String> errorList = new ArrayList<String>();
                //组织PO需要保存的对象User，每个值进行转换的同时，再进行校验，如果校验出现问题，向errorList中存放记录
                List<User> list = this.userPoList(arrayList, errorList);
                //此时表示excel文件中存在错误
                if (errorList.size() > 0) {
                    request.setAttribute("errorList", errorList);
                } else {
                    userService.saveUserFromExcel(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return "importPage";
    }

    /**
     * 按单位生成人员统计图表
     */
    public String chart() {
        List<Object[]> dataSet = userService.loadChartInfo("所属单位", "jctID");
        ChartUtils.createBarChart(dataSet);
        return "chart";
    }

    /**
     * 按性别比例生成人员统计图表
     */
    public String chartFCF() {
        List<Object[]> dataSet = userService.loadChartInfo("性别", "sexID");

        //组织 XML 的数据
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dataSet.size(); i++) {
            Object[] objects = (Object[]) dataSet.get(i);
            if (i == 0) { //组织第一个值
                String x = "男女比例统计";
                String y = "unit"; //存在FusionChart中的一个问题，Y轴的显示不支持中文，所以我们用英文代替
                builder.append("<graph caption='用户统计报表(").append(objects[0].toString())
                        .append(")' xAxisName='").append(x).append("' bgColor='FFFFDD' yAxisName='")
                        .append(y).append("' showValues='1'  decimals='0' baseFontSize='18'  maxColWidth='60' showNames='1' decimalPrecision='0'> ");
                builder.append("<set name='").append(objects[2].toString())
                        .append("' value='").append(objects[0].toString())
                        .append("' color='AFD8F8'/>");
            }
            if (i == dataSet.size() - 1) { //组织最后一个值
                builder.append("<set name='").append(objects[2].toString())
                        .append("' value='").append(objects[0].toString())
                        .append("' color='F6BD0F'/>")
                        .append("</graph>");
            }
        }
        request.setAttribute("chart", builder);

        return "chartFCF";
    }

    /**
     * 将Excel中的每一行记录转换为一个User对象
     */
    private List<User> userPoList(ArrayList<String[]> arrayList, List<String> errorList) {
        //封装结果集数据
        List<User> list = new ArrayList<User>();
        //遍历arrayList
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String[] o = arrayList.get(i);
                //模板数据(登录名	 密码  用户姓名  性别	 所属单位  联系地址  是否在职  出生日期  职位)
                User User = new User();
                /**登录名*/
                if (StringUtils.isNotBlank(o[0])) {
                    //校验登录名
                    User user = userService.findByLoginName(o[0]);
                    if (user != null) {
                        errorList.add("第" + (i + 2) + "行，第" + (1) + "列，登录名在数据库中已经存在！");
                    } else {
                        User.setLoginName(o[0]);
                    }
                } else {
                    errorList.add("第" + (i + 2) + "行，第" + (1) + "列，登录名不能为空！");
                }
                /**密码*/
                //设置默认密码
                if (StringUtils.isBlank(o[1])) {
                    o[1] = "123";
                }
                //md5加密
                User.setPassword(DigestUtils.md5DigestAsHex(o[1].getBytes()));
                /**用户姓名*/
                if (StringUtils.isNotBlank(o[2])) {
                    User.setUserName(o[2]);
                }
                /**性别*/
                if (StringUtils.isNotBlank(o[3])) {
                    //数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
                    String ddCode = systemDDService.findByKeywordAndDdValue("性别", o[3]);
                    if (StringUtils.isBlank(ddCode)) {
                        errorList.add("第" + (i + 2) + "行，第" + (3 + 1) + "列，性别在数据字典转换中出现异常！");
                    } else {
                        User.setSexID(ddCode);
                    }
                } else {
                    errorList.add("第" + (i + 2) + "行，第" + (3 + 1) + "列，性别不能为空！");
                }
                /**所属单位*/
                if (StringUtils.isNotBlank(o[4])) {
                    //数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
                    String ddCode = systemDDService.findByKeywordAndDdValue("所属单位", o[4]);
                    if (StringUtils.isBlank(ddCode)) {
                        errorList.add("第" + (i + 2) + "行，第" + (4 + 1) + "列，所属单位在数据字典转换中出现异常！");
                    } else {
                        User.setJctID(ddCode);
                    }
                } else {
                    errorList.add("第" + (i + 2) + "行，第" + (4 + 1) + "列，所属单位不能为空！");
                }
                /**联系地址*/
                if (StringUtils.isNotBlank(o[5])) {
                    User.setAddress(o[5]);
                }
                /**是否在职*/
                if (StringUtils.isNotBlank(o[6])) {
                    //数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
                    String ddCode = systemDDService.findByKeywordAndDdValue("是否在职", o[6]);
                    if (StringUtils.isBlank(ddCode)) {
                        errorList.add("第" + (i + 2) + "行，第" + (6 + 1) + "列，是否在职在数据字典转换中出现异常！");
                    } else {
                        User.setIsDuty(ddCode);
                    }
                } else {
                    errorList.add("第" + (i + 2) + "行，第" + (6 + 1) + "列，是否在职不能为空！");
                }
                /**出生日期*/
                if (StringUtils.isNotBlank(o[7])) {
                    Date date = DateUtils.stringToDate(o[7]);
                    User.setBirthday(date);
                }
                /**职位*/
                if (StringUtils.isNotBlank(o[8])) {
                    //数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
                    String ddCode = systemDDService.findByKeywordAndDdValue("职位", o[8]);
                    if (StringUtils.isBlank(ddCode)) {
                        errorList.add("第" + (i + 2) + "行，第" + (8 + 1) + "列，职位在数据字典转换中出现异常！");
                    } else {
                        User.setPostID(ddCode);
                    }
                } else {
                    errorList.add("第" + (i + 2) + "行，第" + (8 + 1) + "列，职位不能为空！");
                }
                list.add(User);
            }
        }
        return list;
    }

    private void initSystemDD() {
        List<SystemDD> jctList = systemDDService.findByKeyword("所属单位");
        List<SystemDD> isDutyList = systemDDService.findByKeyword("是否在职");
        List<SystemDD> sexList = systemDDService.findByKeyword("性别");
        List<SystemDD> postList = systemDDService.findByKeyword("职位");
        request.setAttribute("jctList", jctList);
        request.setAttribute("isDutyList", isDutyList);
        request.setAttribute("sexList", sexList);
        request.setAttribute("postList", postList);
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public void setUserIDs(String[] userIDs) {
        this.userIDs = userIDs;
    }

    public void setViewflag(String viewflag) {
        this.viewflag = viewflag;
    }

    public String getFileName() {
        String fileName = "用户信息(" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ").xls";
        try {
            fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(os.toByteArray());
    }

    public void setExcel(File excel) {
        this.excel = excel;
    }
}
