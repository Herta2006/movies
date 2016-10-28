package com.vklindukhov.entity;

//@Entity
//@Table(name = "users")
public class ApplicationUser {
//    @Id
//    @GeneratedValue
    private Long id;
    private String login;
    private String password;

    public ApplicationUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationUser applicationUser = (ApplicationUser) o;

        if (id != null ? !id.equals(applicationUser.id) : applicationUser.id != null) return false;
        return login != null ? login.equals(applicationUser.login) : applicationUser.login == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
