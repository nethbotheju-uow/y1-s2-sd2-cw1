public class Person {
    private String name;
    private String surename;
    private String email;

    public Person(String name, String surename, String email){
        this.name = name;
        this.surename = surename;
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setSurename(String surename){
        this.surename = surename;
    }

    public String getSurename(){
        return surename;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void printPersonInfo(){
        System.out.println("Name: " + name);
        System.out.println("Surename: " + surename);
        System.out.println("Email: " + email);
    }
}
