package pro_test;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;

import javax.xml.namespace.QName;

/**
 * 测试AXIS webservice
 *
 * @author near on 2016/3/17.
 */
public class TestAxis {

    @Test
    public void test() {
        String xmlStr = "学历";
        String url = "http://localhost:8080/services/systemDDService";
        String method = "findSystemDDByKeyword";
        //返回结果
        String webObject = null;
        try {
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);
            /**
             * 在创建QName对象时，QName类的构造方法的第一个参数表示 WSDL 文件的命名空间名，
             * 也就是<wsdl:definitions>元素的targetNamespace属性值, method指定方法名
             */
            QName opAddEntry = new QName("http://serviceImpl.electric", method);
            /** 参数如果有多个，可以继续往后面增加，不用指定参数的名称 */
            Object[] opAddEntryArgs = new Object[]{xmlStr};

            /**
             * invokeBlocking方法有三个参数，
             *   其中第一个参数的类型是QName对象，表示要调用的方法名；
             *   第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
             *   第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。
             *
             *   注意： 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}
             *      如果被调用的WebService方法没有返回值，应使用RPCServiceClient类的invokeRobust方法，
             *      该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同
             */
            Class[] classes = new Class[]{String.class};
            //调用服务器端发布服务的方法，并返回数据
            webObject = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
            System.out.println(webObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
