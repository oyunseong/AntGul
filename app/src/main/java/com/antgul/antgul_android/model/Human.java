package com.antgul.antgul_android.model;

public class Human {
    private final String name;
    private final int age;
    private final String nickname;

    private Human(Builder builder) {
        name = builder.name;
        age = builder.age;
        nickname = builder.nickname;
    }

    public static class Builder {
        // 필수 인자
        private String name;
        private int age;
        // 선택적 인자는 기본값으로 초기화
        private String nickname = "unknown";

        // 필수 인자만 빌더 생성자에 포함
        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder name(String val) {
            name = val;
            return this;    // 이렇게 하면 . 으로 체인을 이어나갈 수 있다.
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder nickname(String val){
            nickname = val;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }
}

/**
 * 사용 예제
 *
 * Human.Builder builder = new Human.Builder("오윤성",24);
 * builder.nickname("56seong");
 *
 * 또는
 *
 * Human human = new Human
 * .Builder("오윤성",24);   // 필수값입력
 * .nickname("56seong");
 * .build();               // build() 가 객체를 생성해 돌려준다.
 * */
