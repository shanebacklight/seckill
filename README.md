# seckill
- Responsible for backend engineering with Spring: countdown before the seckill activity (purchase merchants in a short time); avoid overselling and redundant purchase on the same item.
- Used Mybatis to implement the interface at DAO Layer, namely CRUD operation.
- Integrated log4j and Spring AOP to intercept each http request at SpringMVC controller Layer.
- Combined two cache refreshing strategy on Redis, namely timeout eviction and initiative update, to ensure the eventually consistency between cache and database.
- Adopted the transaction compensation pattern with RocketMQ to ensure high concurrency and transaction consistency involving order generation and inventory subtraction.
- Tested the concurrency performance with JMeter. <br>
*Tech:* SpringMVC, Spring, Mybatis, Redis
