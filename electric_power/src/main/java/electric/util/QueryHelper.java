package electric.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于辅助拼接 HQL 语句的工具类
 *
 * @author near
 */
@SuppressWarnings("all")
public class QueryHelper {

    private StringBuilder fromClause = new StringBuilder("");  // 封装FROM子句
    private StringBuilder whereClause = new StringBuilder("");  // 封装Where子句
    private StringBuilder orderByClause = new StringBuilder("");  // 封装OrderBy子句

    private List<Object> parameters = new ArrayList<Object>();  // 封装参数列表

    /**
     * 生成 From 子句
     *
     * @param clazz 实体类
     * @param alias 别名
     */
    public QueryHelper(Class clazz, String alias) {
        fromClause.append("FROM " + clazz.getSimpleName() + " AS " + alias);
    }

    public QueryHelper() {
    }

    /**
     * 拼接 Where 子句，自定义参数
     *
     * @param condition
     * @return
     */
    public QueryHelper addCondition(String condition) {
        if (whereClause.length() <= 0) {
            whereClause.append(" WHERE " + condition);
        } else {
            whereClause.append(" AND " + condition);
        }

        return this;
    }

    /**
     * 拼接 Where 子句
     *
     * @param condition
     * @param params
     */
    public QueryHelper addCondition(String condition, Object... params) {
        if (whereClause.length() <= 0) {
            whereClause.append(" WHERE " + condition);
        } else {
            whereClause.append(" AND " + condition);
        }
        if (params != null) {
            for (Object p : params) {
                parameters.add(p);
            }
        }

        return this;
    }

    /**
     * 如果第一个参数为true，则拼接 Where 子句
     *
     * @param append
     * @param condition
     * @param params
     */
    public QueryHelper addCondition(boolean append, String condition, Object... params) {
        if (append) {
            addCondition(condition, params);
        }

        return this;
    }

    /**
     * 拼接 OrderBy 子句
     *
     * @param propertyName 参与排序的属性名
     * @param asc          true表示升序，false表示降序
     */
    public QueryHelper addOrderProperty(String propertyName, boolean asc) {
        if (orderByClause.length() <= 0) {
            orderByClause.append(" ORDER BY " + propertyName + (asc ? " ASC" : " DESC"));
        } else {
            orderByClause.append(", " + propertyName + (asc ? " ASC" : " DESC"));
        }

        return this;
    }

    /**
     * 如果第一个参数为true，则拼接 OrderBy 子句
     *
     * @param append
     * @param propertyName
     * @param asc
     */
    public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc) {
        if (append) {
            addOrderProperty(propertyName, asc);
        }

        return this;
    }

    /**
     * 获取生成的用于查询数据列表的 HQL 语句
     *
     * @return
     */
    public String getListQueryHql() {
        return "" + fromClause + whereClause + orderByClause;
    }

    /**
     * 获取生成的用于查询总记录数的 HQL 语句
     *
     * @return
     */
    public String getCountQueryHql() {
        return "SELECT COUNT(*) " + fromClause + whereClause;
    }

    /**
     * 获取 HQL 中的参数值列表
     *
     * @return
     */
    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * 为 parameters 注入数组参数
     *
     * @param params
     */
    public void addParameters(Object[] params) {
        if (params != null && params.length > 0) {
            for (Object obj : params) {
                parameters.add(obj);
            }
        }
    }

}
