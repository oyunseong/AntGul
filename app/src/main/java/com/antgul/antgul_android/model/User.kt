package com.antgul.antgul_android.model

/**
 * kotlin data class
 * java getter setter, hashCode, toString, equals, copy ... 자동 오버라이딩.
 * property (프로퍼티)
 * nullable vs notNull
 * var(variable, 가변, 변수), val(value, 불변, 상수, final)
 *
 * default public, open(상속 가능한 클래스)
 *
 * 객체비교 user1 == user2, String 값 비교
 */
data class User(
    var uid: String?= null,
    var email: String? = null,
    var password: String? = null,
    var nickname: String? = null,
    var profileImage: String? = null,
    var postList: List<String>? = null,
    var likeStockList: List<String>? = null,
    var createAt: String? = null,
) {
//    constructor() //java 생성자랑 유사
}