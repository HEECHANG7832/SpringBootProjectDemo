## 스프링

- TodoList
    - 강의자료 요약
    - https://github.com/steve-developer/fastcampus-springboot-introduction.git
    - 의문점 정리
---

### 스프링 부트 시작하기

대규모 프로젝트 공통적인 기능 제공

XML 구성 요구 없음

java -jar로 실행하는 java 어플리케이션 만들기

Spring 설정이 필요하지 않다

**Servlet Container - Tomcat**

https://start.spring.io

API Test 할 수 있는 크롬 확장 프로그램

**Talend API Tester**


여러가지 모듈 중 **스프링 부트, 스프링 클라우드, 스프링 데이터, 스프링 배치, 스프링 시큐리티**에 중점



- 테스트의 용이성, 느슨한 결합
- IoC, AOP(트랜잭션, 로깅, 시큐리티 등에서 적용)


### JSON
string : value

number : value

boolean : value {}

object : value

array : value []

```json
{
    "phone_number" : "010-1111-1111",
    "age" : 10,
    "isAgree" : false,
    "user" : {
      "email": "steve@gmail.com",
      "password": "1234"
    }
}
```
```json
{
    "user_list" : [
        {
            "account" : "abcd",
            "password" : "1234"
        },
        {
            "account" : "abcd",
            "password" : "1234"
        }
    ]
}

```


### IoC

- java 객체를 new로 생성하여 개발자가 관리 하는 것이 아닌 Spring Container에 맡긴다
- 개발자에서 -> 프레임워크로 객체 관리의 권한이 넘어가 "제어의 역전"



### DI

- 의존성으로 부터 격리시켜 코드 테스트에 용이
- 외부 의존성이 있는 경우 Mock과 같은 기술을 통해 안정적인 테스트 가능
- 코드 확장이나 변경시 영향 줄이기 가능
- 순환참조 막을 수 있다



**AOP**

- 관점 지향 프로그램
- MVC 웹 어플리케이션에는 Web, Business, Data Layer가 있다



@Filter, @Interceptor

- 톰캣에서 한번만 읽을수 있게 막아놨기 때문에
- 암호화된 값은 Aop를 통해서 변환 할 수 있다
- Aop를 통해 암호와 복호와를 해서 내보낼 수 있다

--------------------------------------------

**ObjectMapper**

```java
/*
{
  "name" : "steve",
  "age" : 10,
  "Cars" : [
    {
      "name" : "k5",
      "car_number" : "11가 1111",
      "type" : ""
    },
    {
      "name" : "k5",
      "car_number" : "11가 1111",
      "type" : ""
    }
  ]
}
        
 */
public class User{
    
    private String name;
    private int age;
    private List<Car> cars;
    //getter, setter, toString()
}

public class Car{
    private String name;
    
    @JsonProperty("car_number")
    private String carNumber;
    
    @JsonPRoperty("TYPE")
    private String type;
    //getter, setter
}

public class main{
    public static void main(String args[]) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        User user = new User();
        Car car1 = new Car();
        Car car2 = new Car();
        List<Car> carList = Arrays.asList(car1, car2);
        
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
        
        
        //JSON 내용을 알고 있을때
        JsonNode jsonNode = objectMapper.readTree();
        
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;
        List<Car> _cars = arrayNode.convertValue(arrayNode, new TypeReference<List<Car>>(){});
        
        //값 바꾸기
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());
        
  }
}

```
**Annotation**

@Bean
- new 로 생성한 객체를 bean으로 등록할때
...

**Springboot Valildation**
- null 값에 접근하면 java는 NullPointerException을 발생시킨다
- 따라서 이걸 검사하는 과정을 Validation 이라고 한다
- @NotNull, @NotEmpty, @Past, @Max 등등
- spring-boot-starter-validation


**Exception 처리**
- 에러 페이지
- 에러 코드
