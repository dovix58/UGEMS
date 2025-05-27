package vu.psk.ugems.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileRole {
    OWNER("Owner"),
    MEMBER("Member");

    private final String label;
}
