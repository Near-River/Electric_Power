package electric.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author near on 2016/3/16.
 */
public class StringToListUtils {

    public static List<String> listify(String expNameList) {
        List<String> list = new ArrayList<String>();
        if (expNameList != null && expNameList.length() > 0) {
            String[] strs = expNameList.split("#");
            if (strs.length > 0) {
                Collections.addAll(list, strs);
            }
        }
        return list;
    }

}
