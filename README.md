# zjstudio-demo
经典代码案例


1. 设计模式使用案例
2. 动态数据源切换案例；
3. redis 管道、lua、redisson等高级技术应用案例；
4. 手写seata源码<br>
<参考周瑜视频：https://www.bilibili.com/video/BV1oA411Q7dr/?spm_id_from=333.337.search-card.all.click&vd_source=2899ffeb8d81bd4f0c27ec974227060c>
5. saas租户；
6. 手写spring源码；
7. elasticsearch使用案例；
8. rocketmq使用案例；
9. mqtt使用案例；
10. mybatis使用案例；
11. git命令使用案例；
12. kafka使用案例；
13. sentinel熔断限流使用案例；
14. seata 分布式事务集成案例（使用Netty通信）；
15. postgresql 集成案例；
16. nacos 集成案例；
17. 导入并分析 jvm.hrof 数据案例；
18. canal实现mysql数据同步到redis、mysql、es等数据源案例；
19. dubbo集成案例；
20. spring常用注解、事件、接口、设计模式集成案例；
21. ThreadPool 案例；
    - 周瑜老师手写的分布式事务案例【lbtransaction-zhouyu】中，使用 threadLocal 控制所有数据库连接是创建原生connection还是自定义connection。① 对标记 @Lbtransactional 自定义分布式事务注解的方法，使用 LbTransactionAspect 切面进行创建 threadLocal 标记该方法使用自定义分布式事务；② 通过 LbDataSourceAspect 切面，对所有使用到 javax.sql.DataSource.getConnection 获取数据库连接的方法，判断 threadLocal 是否存在；③ 如果 threadLocal 不存在，则直接使用原生db连接 Connection；如果存在，则创建自定义分布式事务的db连接 LbConnection
    - 
22. ThreadLocal 用法案例；
23. 集成netty案例；
24. 各种 Lock 的使用方法案例；
25. 多版本并发控制（MVCC）；
26. 手写数据库连接池；
27. 使用代码监测数据库性能指标；