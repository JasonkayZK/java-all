package io.github.jasonkayzk;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalDemo {
    //Canal服务地址
    private static final String SERVER_ADDRESS = "localhost";

    //Canal Server 服务端口号
    private static final Integer PORT = 11111;

    //目的地，其实Canal Service内部有一个队列,和配置文件中一致即可，参考【修改instance.properties】图中
    private static final String DESTINATION = "example";

    //用户名和密码，但是目前不支持，只能为空
    private static final String USERNAME = "";

    //用户名和密码，但是目前不支持，只能为空
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress(SERVER_ADDRESS, PORT), DESTINATION, USERNAME, PASSWORD);
        canalConnector.connect();
        //订阅所有消息
        canalConnector.subscribe(".*\\..*");
        //恢复到之前同步的那个位置
        canalConnector.rollback();

        for (; ; ) {
            //获取指定数量的数据，但是不做确认标记，下一次取还会取到这些信息
            Message message = canalConnector.getWithoutAck(100);
            //获取消息id
            long batchId = message.getId();
            if (batchId != -1) {
                System.out.println("msgId -> " + batchId);
                printEnity(message.getEntries());
                //提交确认
                //canalConnector.ack(batchId);
                //处理失败，回滚数据
                //canalConnector.rollback(batchId);
            }
        }
    }

    private static void printEnity(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA) {
                continue;
            }
            try {
                CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                    System.out.println(rowChange.getEventType());
                    switch (rowChange.getEventType()) {
                        //如果希望监听多种事件，可以手动增加case
                        case INSERT:
                            String tableName = entry.getHeader().getTableName();
                            //测试users表进行映射处
                            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                            System.out.println(afterColumnsList);
                            break;
                        case UPDATE:
                            List<CanalEntry.Column> afterColumnsList2 = rowData.getAfterColumnsList();
                            System.out.println("新插入的数据是：" + afterColumnsList2);
                            break;
                        case DELETE:
                            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                            System.out.println("被删除的数据是：" + beforeColumnsList);
                            break;
                        default:
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
    }

}