package com.marcus.saborfy.module.user.enuns;

import lombok.Getter;

public enum RoleName {
    WAITER(1),
    TELLER(2),
    ADMIN(3),
    SYSTEM_ADMIN(5),
    OWNER(4);

    @Getter
    private final int level;

    RoleName(int level) {
        this.level = level;
    }

    public boolean canManager(RoleName outro) {
        return this.level <= outro.level;
    }
}
