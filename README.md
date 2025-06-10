# FlightAPI - 航班预订后台服务

一个基于 Spring Boot 3.5.0 构建的航班预订服务后端API项目，支持用户注册登录、航班查询与预定等功能，使用 JWT 进行认证授权，并使用 Spring Data JPA 与 MySQL 数据库进行持久化。

---

## 技术栈

- Java 21
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- JWT (`jjwt` 0.11.5)
- Lombok
- MySQL
- OpenAPI (springdoc-openapi)
- Maven

### API 接口功能概览  

- 用户注册与登录（支持 JWT 鉴权）
- 航班搜索与筛选  
- 航班预订、订单查看  
- 异常集中处理
- 国际化支持（错误信息多语言） 
- Swagger/OpenAPI 文档自动生成(有问题) 

---

## 快速开始（本地开发）

### 克隆项目
```
git clone https://github.com/your-org/flightapi.git
cd flightapi
```
### 本地运行  

确保已经配置好本地 MySQL 数据库，并在 application.yml 中设置正确的数据库连接信息：

```
spring:  
  datasource:  
    url: jdbc:mysql://localhost:3306/flightdb  
    username: your_user  
    password: your_password  
```

运行：
./mvnw spring-boot:run

### 本地 Swagger 文档
- 访问地址：http://localhost:8080/swagger-ui.html(有问题)

### 部署信息（生产环境） 

- 后端已部署在 AWS ECS 上，集群名称：jiayan-flight-api。

- 访问地址（公网）：http://3.27.45.236:8080   
- 测试链接：http://3.27.45.236:8080/api/airports

- Swagger 文档（公网）：http://3.27.45.236:8080/swagger-ui.html(有问题)


### 数据库配置（生产）

- 数据库使用 AWS RDS（MySQL），请在生产环境中填写如下连接信息：

```
spring:
  datasource:
    url: jdbc:mysql://jiayan-flightdb.cj6gcogsidzt.ap-southeast-2.rds.amazonaws.com:3306/flight_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=utf8
    username: rds_user
    password: rds_password
```

 


