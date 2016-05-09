package electric.util;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author near on 2016/3/16.
 */
public class ValueStackUtils {

    public static void putStack(String name, Object object) {
        ActionContext.getContext().getValueStack().set(name, object);
    }

    public static void pushStack(Object object) {
        ActionContext.getContext().getValueStack().push(object);
    }

}
