## LOGIN API 정의서


### 회원 생성
### ------------------------------------------------------
* @api        -> {post} /create-account (Create User Account)
* 
* @apiVersion  -> 1.0.0
* @apiName     -> createAccount
* @apiGroup    -> User
* 
* @apiParam    -> {String} id, User unique ID.
* @apiParam    -> {String} password, User password.
* @apiParam    -> {Number} role, Users role(customer, driver).
* 
* @apiSuccess  -> {Boolean} ok, Success (true/false).
* @apiSuccess  -> {String} error, Message. 
### ------------------------------------------------------


### 로그인 및 JWT 생성
### ------------------------------------------------------
* @api        -> {post} / login (Authentication)
* 
* @apiVersion  -> 1.0.0
* @apiName     -> login
* @apiGroup    -> User
* 
* @apiParam    -> {String} id, User unique ID.
* @apiParam    -> {String} password, User password.
* 
* @apiSuccess  -> {Boolean} ok, Success (true/false).
* @apiSuccess  -> {String} error, Message. 
* @apiSuccess  -> {String} token, JWT. 
### ------------------------------------------------------ 


### 로그인 사용자 정보 가져오기
### ------------------------------------------------------
* @api        -> {get} / userProfile (User Information)
* 
* @apiVersion  -> 1.0.0
* @apiName     -> userProfile
* @apiGroup    -> User
* 
* @apiParam    -> {Number} id, User unique Id.
* 
* @apiSuccess  -> {Boolean} ok, Success (true/false).
* @apiSuccess  -> {String} error, Message. 
* @apiSuccess  -> {Object} user, Object return { "email" : "test@gmail.com", "password" : "1234", "role" : 1, "verified" : true }. 
### ------------------------------------------------------


### 헤더 JWT 정보 가져오기
### ------------------------------------------------------
* @api        -> {get} / me (Header JWT User Information)
* 
* @apiVersion  -> 1.0.0
* @apiName     -> me
* @apiGroup    -> User
* 
* @apiSuccess  -> {Boolean} ok, Success (true/false).
* @apiSuccess  -> {String} error, Message. 
* @apiSuccess  -> {Object} user, Object return { "email" : "test@gmail.com", "password" : "1234", "role" : 1, "verified" : true }. 
### ------------------------------------------------------



