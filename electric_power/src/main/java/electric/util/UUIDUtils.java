package electric.util;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 *
 * @author Near
 * @version 1.0
 */
public class UUIDUtils {
    /**
     * 生成32位的随机字符串
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
