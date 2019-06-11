package first.demo.enums;

import com.google.gson.Gson;

public enum Singleton {
    INSTANCE;
    private Gson gson;

    Singleton(){
        this.gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }
}
