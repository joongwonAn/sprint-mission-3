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

메서드	설명	예시 코드
stream()	Stream 객체 생성	list.stream()
filter()	조건에 맞는 데이터만 추출	.filter(x -> x > 10)
map()	각 요소를 변환	.map(x -> x * 2)
sorted()	정렬	.sorted()
anyMatch()	조건에 맞는 요소가 하나라도 있으면 true	.anyMatch(x -> x == 3)
forEach()	각 요소에 작업 수행 (최종 연산)	.forEach(System.out::println)
collect()	결과를 컬렉션 등으로 변환 (최종 연산)	.collect(Collectors.toList())
count()	요소 개수 세기 (최종 연산)	.count()
findFirst()	첫 번째 요소 찾기 (최종 연산)	.findFirst()
