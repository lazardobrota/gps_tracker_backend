package gps.tracker.backend.endpoints;

public class Endpoints {
    public static final String apiBase = "api/v1";

    public static class User {
        public static final String base = apiBase + "/users";

        public static final String getAll = base;
        public static final String getOne = base + "/{id}";
        public static final String create = base;
        public static final String update = base + "/{id}";
        public static final String passwordReset = base + "/password-reset/{id}";
    }
}
