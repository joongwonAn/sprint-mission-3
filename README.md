## 1) Lombob 애너테이션

- `@Getter` : 모든 필드에 대한 Getter 메서드 자동 생성
- `@Setter` : 모든 필드에 대한 Setter 메서드 자동 생성
- `@NoArgConstructor` : 기본 생성자 자동 생성
- `@AllArgConstructor` : 모든 필드를 초기화하는 생성자 자동 생성
- `@RequiredArgsConstructor` : `final` 또는 `@NonNull`이 붙은 필드만 포함하는 생성자 자동 생성

## 2) 클래스 필드 타입 : `Instant`

- `Instant`는 Java 8의 `java.time` 패키지에 포함된 클래스
- UTC 기준의 날짜/시간을 나타냄 (1970-01-01T00:00:00Z 이후 몇 초인지로 내부 저장)
- `Instant.now()` → 현재 시간을 `Instant` 타입으로 반환
- `toEpochMilli()`나 `toEpochSecond()`로 `long`값도 얻을 수 있음
- `ZonedDateTime`, `LocalDateTime` 같은 다른 시간 객체로도 쉽게 변환 가능

### 자주 쓰이는 Instant 클래스 메서드

- `minusSeconds(long seconds)`
    - 현재 시간에서 지정한 초만큼 과거로 이동한 시간 객체를 반환
    - `Instant.now().minusSeconds(60*5)` → 5분 전
- `plusSeconds(long seconds)`
    - 현재 시간에서 지정한 초만큼 미래로 이동한 시간 객체를 반환
    - `Instant.now().plusSeconds(60*5)` → 1분 후
- `isBefore(Instant other)`
    - 현재 Instant가 other보다 이전인지 판단 (true/false 반환)
    
    ```java
    Instant earlier = Instant.now().minusSeconds(10);
    Instant now = Instant.now();
    earlier.isBefore(now); //  true
    ```
    
- `isAfter(Instant other)`
    - 현재 Instant가 other보다 이후인지 판단 (true/false 반환)

## 3) 코드 짜는 법

1. 목표가 무엇인지 정확히 알기
2. 목표를 달성하기 위해 어떤 정보가 필요한지 정의
3. 그 정보를 얻기 위한 repository 접근 방법 고민

## 4) Java Stream API

### Stram API란?

- 컬렉션 프레임워크(List, Set 등)에 대해 “데이터 흐름”을 만들고, 
그 흐름에 여러 연산(filter, map, forEach 등)을 연결해서 데이터를 처리하는 방식

### Stream API 기본 구조

1. Stream 생성
2. 중간 연산(가공) : filter, map, sorted 등으로 데이터를 변환 (여러 개 연결 가능)
3. 최종 연산(결과) : forEach(), collect, count 등으로 결과 도출 (최종 연산 후 Stream은 재사용 불가)

### 자주 쓰이는 Stream API 문법

| **메서드** | **설명** | **예시 코드** |
| --- | --- | --- |
| **stream()** | Stream 객체 생성 | `list.stream()` |
| **filter()** | 조건에 맞는 데이터만 추출 | `.filter(x -> x > 10)` |
| **map()** | 각 요소를 변환 | `.map(x -> x * 2)` |
| **sorted()** | 정렬 | `.sorted()` |
| **anyMatch()** | 조건에 맞는 요소가 하나라도 있으면 true | `.anyMatch(x -> x == 3)` |
| **forEach()** | 각 요소에 작업 수행 **(최종 연산)** | `.forEach(System.out::println)` |
| **collect()** | 결과를 컬렉션 등으로 변환 **(최종 연산)** | `.collect(Collectors.toList())` |
| **count()** | 요소 개수 세기 **(최종 연산)** | `.count()` |
| **findFirst()** | 첫 번째 요소 찾기 **(최종 연산)** | `.findFirst()` |

## 5) Optional

- 데이터 조회, 외부 API 호출 등 null 가능성이 있는 상황에서 null-safe한 처리를 위해 사용하는 Wrapper 클래스
- Optional<String>은 null 또는 String 값을 담음

### Optional 생성 방법

| `Optional.of(value)` | value가 null이면 예외 발생 |
| --- | --- |
| `Optional.ofNullable(value)` | value가 null이든 아니든 감쌈 |
| `Optional.empty()` | 빈 Optional 반환 |

### 자주 쓰는 메서드

| `isPresent()` | 값이 있는지 여부 |
| --- | --- |
| `ifPresent(Consumer)` | 값이 있으면 실행 |
| `orElse(T)`  | 값이 없으면 기본값 반환 |
| `orElseGet(Supplier)` | 값이 없으면 Supplier 실행 결과 반환 |
| `orElseThrow()` | 값 없으면 예외 던짐 |
| `map(Function)` | 값이 있으면 변환 |
| `filter(Predicate)` | 조건 만족시 유지  |

### 실무에서 Optional 주의사항 (하지 말아야 할 것)

| 필드에 `Optional` 사용
(`Optional<String> name`) | Jackson 직렬화 문제, 자바 Bean 표준 위반 |
| --- | --- |
| `Optional`을 `null`로 만들기 | 의미 X(빈 Optional로 만들어야 함) |
| `isPresent()` 후 `get()` 호출 | 그냥 `if != null` 과 다를 바 없음
`ifPresent` 또는 `map`으로 처리 |

### 스프링 부트에서의 활용법

- Repository 계층 : 조회 결과를 Optional로 반환
    
    ```java
    public interface UserRepository extends JpaRepository<User, Long> {
        // null-safe 조회
        Optional<User> findByEmail(String email);
    }
    
    @Service
    public class UserService {
        public UserDto getUserByEmail(String email) {
            return userRepository.findByEmail(email)
                    .map(user -> new UserDto(user.getName(), user.getEmail()))
                    .orElseThrow(() -> new UserNotFoundException());
        }
    }
    ```
    
- Service 계층 : orElse(), orElseThrow()로 안전한 값 처리
- Controller 계층 : Optional.map()으로 응답 변환
    
    ```java
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    ```
    
- 설정 관리 : 환경 변수 안전하게 읽기
    
    ```java
    @Value("${app.api.timeout:5000}") // 기본값 5000
    private int timeout;
    
    // 설정값이 없을 경우 대비
    int safeTimeout = Optional.ofNullable(env.getProperty("app.api.timeout"))
                              .map(Integer::parseInt)
                              .orElse(5000);
    ```
