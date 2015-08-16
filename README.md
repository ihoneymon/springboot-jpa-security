Spring Data JPA and Spring Security Sample project
==================================================

Spring Data JPA와 Spring Security 를 연계시켜본 예제 프로젝트

# 1. 구성 설명
* User: 스프링시큐리티의 UserDetails 인터페이스를 구현
* Authority: 스프링시큐리티의 GrantedAuthority 인터페이스를 구현한 enum 타입
  - 애플리케이션에 대한 사용자권한은 애플리케이션 설계단계에서 정의하는 편...일겁니다.
   	권한은 엔티티...로 관리하려면 할 수도 있겠지만(저는 그렇게 해보진 않았음)
  - 이 프로젝트에서는 ADMIN, PROJECT_MANAGER, USER의 권한을 부여
  - 권한
    + ADMIN: 애플리케이션 관리자 권한
    + PROJECT_MANAGER: 프로젝트 관리자, 프로젝트 CRUD, 팀 CRUD 담당
    + USER: 일반적인 사용자권한
* WebSecurityConfig: 웹스프링시큐리티에 관한 설정클래스

# 2. 구현해야할 것들
* 각 엔티티들에 대한 Repository
* 트랜잭션 처리를 위한 Service
* 화면영역을 처리할 Controller
	- Controller 구현에 따라서 WebSecurityConfig#configure() 에 권한설정을 추가