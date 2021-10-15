package com.andrei.stepantcev.messenger.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class SQLUtils {

    public static String getQuery(final String name) throws IOException {
        return IOUtils.resourceToString("/sql/" + name + ".sql", UTF_8);
    }
}