package com.example.b_project_jomok_0528.Dto;

public class MemberDTO {
    String member_id, password, name, email, phone;

    //마이페이지에 아이디 이름 가져오기
    public MemberDTO(String member_id, String name) {
        this.member_id = member_id;
        this.name = name;
    }

    // 로그인할때 비밀번호 없이 멤버변수 보낼때
    public MemberDTO(String member_id, String name, String email, String phone) {
        super();
        this.member_id = member_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // 데이터베이스에 멤버변수 추가할때
    public MemberDTO(String member_id, String password, String name, String email, String phone) {
        super();
        this.member_id = member_id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
