# Factory Agent - Sensor Data Collector

## 🌟 소개

**Factory Agent**는 사용자가 정의한 센서로부터 주기적으로 데이터를 수집하여 원격 서버로 전송하는 Java 기반의 커맨드 라인 애플리케이션입니다. 사용자는 에이전트를 통해 서버에 로그인/회원가입하고, 모니터링할 센서(시스템 명령어 기반)를 동적으로 추가, 수정, 삭제할 수 있습니다.

이 에이전트는 `org.primitive` 패키지 내에 개발되었으며, "Primitive_number_one" 팀/개발자의 프로젝트로 보입니다.

## ✨ 주요 기능

* **사용자 인증:**
    * 서버를 통한 로그인 및 회원가입 기능 제공.
* **동적 센서 관리 (CLI 기반):**
    * **센서 추가:** 새로운 센서의 이름과 값 수집을 위한 시스템 명령어를 정의하여 서버에 등록합니다.
    * **센서 수정:** 기존 센서의 이름 또는 명령어 변경이 가능합니다.
    * **센서 삭제:** 특정 센서를 모니터링 목록에서 제거합니다.
* **주기적 데이터 수집:**
    * 에이전트 실행 시, 등록된 모든 센서의 명령어를 실행하여 현재 값을 수집합니다.
* **데이터 서버 전송:**
    * 수집된 센서 값들을 주기적으로(기본 10초 간격) 지정된 서버로 전송합니다.
* **커맨드 라인 인터페이스:**
    * 사용자는 간단한 콘솔 메뉴를 통해 에이전트 실행 및 환경 설정을 제어할 수 있습니다.

## ⚙️ 기술 스택

* **언어:** Java
* **네트워킹:** Retrofit2 (백엔드 API 통신), Gson (JSON 직렬화/역직렬화)
* **유틸리티:** Lombok (보일러플레이트 코드 감소)
* **실행 환경:** Java Runtime Environment (JRE)

## 🚀 작동 방식

1.  **인증:**
    * 애플리케이션 시작 시, 사용자는 ID와 PW를 입력하여 서버에 로그인하거나 새 계정을 등록합니다. (`/login`, `/sign_up` 엔드포인트 사용)
2.  **센서 설정 (`setting_agent`):**
    * 사용자는 CLI를 통해 모니터링할 센서를 관리할 수 있습니다.
    * 각 센서는 `이름`과 실제 데이터 수집을 위한 `시스템 명령어`로 정의됩니다.
    * 센서 정보는 서버와 동기화됩니다. (`GET /sensors/{ID}`, `POST /sensors`, `PATCH /sensors`, `DELETE /sensors/{index}` 엔드포인트 사용)
3.  **데이터 수집 및 전송 (`Factory_Agent.execute`):**
    * 에이전트가 "실행" 모드로 전환되면, `Factory_Agent`는 다음 작업을 무한 반복합니다:
        1.  `Sensors` 객체를 통해 등록된 모든 센서의 `getValue()` 메소드를 호출합니다. 이 메소드는 각 센서에 정의된 시스템 명령어를 실행하여 현재 값을 가져옵니다. (내부적으로 `Cmd` 클래스 사용 추정)
        2.  수집된 센서 이름과 값(`SensorValue[]`)을 사용자 `ID`와 함께 서버의 `/sensors_value` 엔드포인트로 전송합니다.
        3.  기본 10초 동안 대기한 후, 1단계부터 다시 시작합니다.

## 🛠️ 설정 및 사용법

### 사전 준비 사항

* Java Development Kit (JDK) 설치 (버전은 프로젝트 호환성에 따라)
* `org.primitive.SensorRelates.Cmd` 클래스: 이 클래스는 각 `Sensor` 객체의 `command` 문자열을 실제 시스템 명령어로 실행하고 결과를 반환하는 역할을 합니다. **(제공된 코드에는 포함되어 있지 않으므로, 별도 구현 또는 제공 필요)**

### 컴파일 및 실행

1.  프로젝트 소스 코드를 컴파일합니다. (IDE 사용 또는 `javac` 직접 사용)
2.  `org.primitive.Main` 클래스를 실행합니다.
    ```bash
    java org.primitive.Main
    ```

### 구성

* **백엔드 서버 URL:**
    * `org.primitive.Network.URL_holder.java` 파일 내의 `URL` 상수를 통해 백엔드 서버의 주소를 설정합니다.
    * 현재 설정: `http://140.245.76.30:10001/`
* **센서 명령어 실행 환경:**
    * `Sensor`에 정의된 명령어들이 실행될 수 있는 환경이어야 합니다 (예: 필요한 실행 파일의 PATH 설정, 권한 등).

### 초기 사용 흐름

1.  애플리케이션 실행 시, "1. 로그인" 또는 "2. 회원등록"을 선택합니다.
2.  로그인/회원가입 성공 후, 메인 메뉴가 나타납니다:
    * "1. Agent 실행": `Factory_Agent`를 시작하여 주기적인 센서 데이터 수집 및 전송을 개시합니다.
    * "2. 환경 설정": 센서를 추가, 수정, 또는 삭제할 수 있는 `setting_agent` 메뉴로 진입합니다.

## 📡 API 상호작용 (클라이언트 측)

본 에이전트가 호출하는 백엔드 API 엔드포인트는 `org.primitive.Network.RetrofitAPI` 인터페이스에 정의되어 있습니다:

* `POST /sensors_value`: 수집된 센서 값들을 전송합니다. (Request Body: `SensorValueDTO`)
* `POST /login`: 사용자 로그인을 요청합니다. (Request Body: `LoginVO`)
* `POST /sign_up`: 신규 사용자 등록을 요청합니다. (Request Body: `SignUpVO`)
* `POST /sensors`: 새로운 센서를 등록합니다. (Request Body: `SensorDTO`)
* `GET /sensors/{ID}`: 특정 사용자의 모든 센서 목록을 조회합니다.
* `PATCH /sensors`: 기존 센서 정보를 업데이트합니다. (Request Body: `Sensor`)
* `DELETE /sensors/{index}`: 특정 인덱스의 센서를 삭제합니다.

## 📦 프로젝트 구조

주요 패키지 및 클래스 구성은 다음과 같습니다:

* **`org.primitive`**: 루트 패키지
    * `Main.java`: 애플리케이션 진입점, 사용자 CLI 상호작용 처리.
    * `Factory_Agent.java`: 센서 데이터 수집 및 전송 로직을 담당하는 핵심 에이전트 클래스.
* **`org.primitive.Network`**: 네트워크 통신 관련 클래스
    * `Callretrofit.java`: Retrofit API 호출을 캡슐화한 클래스.
    * `RetrofitAPI.java`: Retrofit 서비스 인터페이스 (API 엔드포인트 정의).
    * `RetrofitClient.java`: Retrofit 인스턴스 생성 및 설정.
    * `URL_holder.java`: 백엔드 서버 URL 관리.
    * `Env.java`: 환경 구분 상수 (FACTORY, CLIENT).
    * `LoginStatus.java`: 로그인/회원가입 결과 상태 코드.
    * **`VO` (sub-package)**: 데이터 전송 객체 (DTO/VO)
        * `LoginVO.java`
        * `SensorDTO.java`
        * `SensorValueDTO.java`
        * `SignUpVO.java`
* **`org.primitive.SensorRelates`**: 센서 관련 로직 및 데이터 모델
    * `Sensor.java`: 단일 센서 객체 (index, name, command, value retrieval).
    * `Sensors.java`: 여러 `Sensor` 객체의 컬렉션을 관리하고, 서버와 동기화.
    * `SensorValue.java`: 센서 이름과 측정값을 담는 DTO.
    * `Cmd.java` (가상): 시스템 명령어 실행을 담당할 것으로 예상되는 클래스 (별도 구현 필요).

---

이 README가 Factory Agent 프로젝트를 이해하고 사용하는 데 도움이 되기를 바랍니다!
