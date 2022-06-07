package com.will.bookstoreapi.auth.appUser;

public enum AppUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write");

    private final String permission;

    AppUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
      return permission;
    }

}
