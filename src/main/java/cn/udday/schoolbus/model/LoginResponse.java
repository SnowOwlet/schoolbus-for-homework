package cn.udday.schoolbus.model;

public class LoginResponse {
    private boolean isSuper = false;
    private String token = "";

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "isSuper=" + isSuper +
                ", token='" + token + '\'' +
                '}';
    }
}
