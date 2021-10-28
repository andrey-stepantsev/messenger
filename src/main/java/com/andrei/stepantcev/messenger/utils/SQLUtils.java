package com.andrei.stepantcev.messenger.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import lombok.val;
import org.apache.commons.io.IOUtils;

public class SQLUtils {

    public static String getQuery(final String name) throws IOException {
        val path = "/sql/" + name + ".sql";
        return IOUtils.resourceToString(path, UTF_8);
    }
}