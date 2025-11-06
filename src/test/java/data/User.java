package data;
import org.testng.annotations.DataProvider;
import org.testng.reporters.AbstractXmlReporter;

import java.util.Objects;

public class User {

    private final String username;
    private final String password;

    //Constructor privado solo para la clase interna Builder
    private User(String username,String password){

        this.username= username;
        this.password=password;

        if (this.username==null||this.password==null){
            throw new IllegalArgumentException("username or password cannot be null");
        }
    }
    public String getUsername(){
        return  username;
    }
    public String getPassword(){
        return password;
    }
//clase estática para crear objetos user de forma fluida

    public static class Builder{
        private String username;
        private String password;

        public Builder withUsername(String username){
            this.username=username;
            return this;
        }
        public Builder withPassword(String password){
            this.password=password;
            return this;
        }
        //Metodo que crea y devuelve la instancia inmutable de User

        public User build(){
            return new User(username,password);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }


}





