package com.example.myapp2;

public class Model {

    String id,name,age,email,birth,addtime,updatetime;



    public Model(String id, String name, String age, String email, String birth, String addtime, String updatetime) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.birth = birth;
        this.addtime = addtime;
        this.name = name;
        this.updatetime = updatetime;
    }

    public Model() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getBirth() {
        return birth;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public boolean checkNull(String user, String pass, String repass){
        if(user.equals("")||pass.equals("")||repass.equals("")) {
            return true;
        }
            else
            return false;
    }
}
