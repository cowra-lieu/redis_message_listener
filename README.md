# 消息格式说明

> 下面两种消息都可以通过 Jackson 绑定到 Java 类型：
> com.btw.demo.redis_message_listener.IcerUpdate

## 可直接更新的消息
```json
{
   "smallUpdate":true,
   "rawDataList":[
      "ARS,USD,10/06/2020,0.01398601",
      "BRL,USD,10/06/2020,0.20384451",
      "USD,ARS,10/06/2020,71.50000000",
      "USD,BRL,10/06/2020,4.90570000"
   ]
}
```

每天FTP会更新两个1kB大小的数据文件，仅有十几个左右的汇率更新，ICER_MONITOR 在解析入库后会把这些数据直接封装在消息中发给 Redis。
监听程序收到消息后，可从消息中直接提取数据更新 Redis 和 6个节点的Ehcache，无需访问数据库。


## 需要读数据库的消息
```json
{
   "smallUpdate":false
}
```
每天FTP会更新两个700 kB左右的数据文件，包含2万多个全量的汇率更新，ICER_MONITOR 在解析入库后会直接发送一个仅含标志位的小消息给 Redis，
监听程序收到消息后，发现 smallUpdate 是 false，知道需要做全量更新，会去读数据库，更新Redis，并让6个节点的Ehcache的失效。

## Redis Topic

见 com.btw.demo.redis_message_listener.RedisConfig 的第35行，就是字符串：
```
icer:update
```
ICER_MONITOR 端也是使用它。