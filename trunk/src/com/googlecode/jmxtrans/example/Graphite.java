package com.googlecode.jmxtrans.example;

import com.googlecode.jmxtrans.model.JmxProcess;
import com.googlecode.jmxtrans.model.Query;
import com.googlecode.jmxtrans.model.Server;
import com.googlecode.jmxtrans.model.output.GraphiteWriter;
import com.googlecode.jmxtrans.util.JmxUtils;

/**
 * This class hits a Graphite server running on port 2003
 * and sends the memory usage data to it for graphing.
 * 
 * @author jon
 */
public class Graphite {

    /** */
    public static void main(String[] args) throws Exception {
        Server server = new Server("w2", "1099");

        Query q = new Query();
        q.setObj("java.lang:type=Memory");
        q.addAttr("HeapMemoryUsage");
        q.addAttr("NonHeapMemoryUsage");
        GraphiteWriter gw = new GraphiteWriter();
        gw.addSetting(GraphiteWriter.HOST, "192.168.192.133");
        gw.addSetting(GraphiteWriter.PORT, 2003);
        q.addOutputWriter(gw);
        server.addQuery(q);

        JmxProcess jmx = new JmxProcess(server);
        JmxUtils.prettyPrintJson(jmx);

//        for (int i = 0; i < 160; i++) {
//            JmxUtils.execute(jmx);
//            Thread.sleep(1000);
//        }
    }

}
