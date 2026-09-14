package com.marcus.saborfy.module.user.enuns;

import lombok.Getter;

public enum RoleName {
    COOK(1),
    WAITER(2),
    TELLER(3),
    ADMIN(4),
    SYSTEM_ADMIN(6),
    OWNER(5);

    @Getter
    private final int level;

    RoleName(int level) {
        this.level = level;
    }

    public boolean canManager(RoleName outro) {
        return this.level <= outro.level;
    }
}
