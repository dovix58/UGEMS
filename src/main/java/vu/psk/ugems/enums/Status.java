package vu.psk.ugems.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    LATE("Late");

    private final String label;

}
