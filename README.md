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
- 使用 BCrypt 进行密码哈希加密
- 航班搜索与筛选 （支持分页和排序）
- 支持单程和往返航班
- 航班预订并关联用户信息、订单查看  
- 输入校验（基于 Bean Validation）
- 分层架构（DTO、控制器、服务、仓库）
- 全局异常处理
- 国际化支持（错误信息多语言） 
- Swagger/OpenAPI 文档自动生成(有问题) 

---

## 快速开始（本地开发）

### 克隆项目
```
git clone https://github.com/zjiayan-cd/flight-api.git 
cd flight-api
```
### 本地运行  

确保已经配置好本地 MySQL 数据库，并在 application-local.yml 中设置正确的数据库连接信息：

```
spring:  
  datasource:  
    url: jdbc:mysql://localhost:3306/flightapi?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true  
    username: your_user  
    password: your_password  
```

运行：
./mvnw spring-boot:run

### 本地 Swagger 文档
- 访问地址：http://localhost:8080/swagger-ui.html(有问题)

### 部署信息（生产环境） 

- 后端已部署在 AWS ECS 上，集群名称：jiayan-flight-api。

- 访问地址（公网）：http://16.176.135.170:8080   
- 测试链接：http://16.176.135.170:8080/api/airports

- Swagger 文档（公网）：http://16.176.135.170:8080/swagger-ui.html(有问题)


### 数据库配置（生产）

- 数据库使用 AWS RDS（MySQL），请在application-prod.yml中填写如下连接信息：

```
spring:
  datasource:
    url: jdbc:mysql://jiayan-flightdb.cj6gcogsidzt.ap-southeast-2.rds.amazonaws.com:3306/flight_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=utf8
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true

server:
  port: 8080
  address: 0.0.0.0
```

### AWS Task Definition 环境变量配置：
- SPRING_PROFILES_ACTIVE: prod
- SPRING_DATASOURCE_USERNAME: admin
- SPRING_DATASOURCE_PASSWORD: xxxxxx(请填写正确的数据库密码)

### 认证  
- 登录成功后返回 JWT 令牌。
- 预订相关的接口需要携带令牌。
- 令牌通过 JwtAuthenticationFilter 进行验证。
- 令牌应存放在请求头 Authorization: Bearer <token> 中。
- 用户密码使用 BCryptPasswordEncoder 安全加密存储。

### 国际化
- 根据请求头中的 Accept-Language 使用 Spring 的 MessageSource 进行解析。
- 默认语言为英文（en）。
```
src/main/resources/messages_en.properties
src/main/resources/messages_zh.properties
```


