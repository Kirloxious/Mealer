package com.example.mealer24.Utilities;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    public static final String CUISINIER_ROLE = "cuisinier";
    public static final String ADMIN_ROLE = "admin";
    public static final String CLIENT_ROLE = "client";
    public static final String DB_USER_PATH = "Users";
    public static final String DB_CUISINIER_PATH = "Cuisiniers";
    public static final String DB_ADMIN_PATH = "Admin";
    public static final String DB_CLIENT_PATH = "Clients";
    public static final String INTENT_EXTRA_ROLE = "Role";
    public static final String DB_REPAS_PATH = "mesRepas";
    public static final String DB_ORDERS_PATH = "Orders";


    /**
     * Transforms a list of strings to a valid path.
     * Example:
     *  getPathFrom("I", "see", "you"),
     *  returns: "I/see/you"
     * @param paths
     * @return
     */
    public static String getPathFrom(String... paths) {
        String ret = "";
        for(int i = 0; i < paths.length - 1; ++i) {
            ret += paths[i];
            ret += "/";
        }
        return ret + paths[paths.length - 1];
    }


    public static String transformRoleToDbPath(String role) {
        if(role.equalsIgnoreCase(Utils.CUISINIER_ROLE)) return Utils.DB_CUISINIER_PATH;
        if(role.equalsIgnoreCase(Utils.CLIENT_ROLE)) return Utils.DB_CLIENT_PATH;
        if(role.equalsIgnoreCase(Utils.ADMIN_ROLE)) return Utils.DB_ADMIN_PATH;
        throw new RuntimeException("Unknown role: [Utils.java] __FUNC__ : transformRoleToDbPath");
    }


    /**
     * Gets an account database reference from given role and email
     * @param role role of the account (Client, Cuisinier, Admin)
     * @param email email of the account to search in database
     * @return The Firebase database instance reference of the given account
     */
    public static DatabaseReference getAccountDatabaseReference(String role, String email){
        UTF8Encoder encodedEmail = new UTF8Encoder(email);
        String encodedEmailAsString = encodedEmail.getEncodedString();
        String Role = transformRoleToDbPath(role);
        String user_path = getPathFrom(DB_USER_PATH, Role, encodedEmailAsString);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(user_path);
        return ref;
    }

    public static String getEncodedEmailAsString(String email){
        UTF8Encoder encodedEmail = new UTF8Encoder(email);
        return encodedEmail.getEncodedString();
    }


    private Utils() {}
}
