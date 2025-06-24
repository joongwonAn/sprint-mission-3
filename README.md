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
