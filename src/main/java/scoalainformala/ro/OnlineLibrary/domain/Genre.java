package scoalainformala.ro.OnlineLibrary.domain;

import java.util.Random;

public enum Genre {
    AUTOBIOGRAPHY,
    FANTASY,
    MYSTERY,
    FAMILY,
    ADVENTURE,
    COMING_OF_AGE,
    ROMANCE,
    HISTORICAL,
    SELF_IMPROVEMENT,
    MAGIC,
    GENERAL,
    MANUAL,
    LITERATURE,
    THRILLER,
    CHILDREN,
    WAR,
    SEXOLOGY,
    FICTION;

    public static Genre getRandomGenre() {
        return values()[new Random().nextInt(values().length)];
    }
}
