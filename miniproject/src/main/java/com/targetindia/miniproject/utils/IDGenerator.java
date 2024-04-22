package com.targetindia.miniproject.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public final class IDGenerator {
    private IDGenerator() {}

    private static int CURRENT_ID = 0;
    private static final String FILENAME = "__seed__";

    static {
        try (
                FileInputStream file = new FileInputStream(FILENAME);
                DataInputStream in = new DataInputStream(file);
                ) {
            CURRENT_ID = in.readInt();
        } catch (IOException e) {
            log.warn("Error while reading seed file for ID generation.", e);
        }
    }

    public static int generate() {
        CURRENT_ID++;
        try (
                FileOutputStream file = new FileOutputStream(FILENAME);
                DataOutputStream out = new DataOutputStream(file);
                ) {
            out.writeInt(CURRENT_ID);
        } catch (IOException e) {
            log.warn("Error while writing seed file for ID generation.", e);
        }
        return CURRENT_ID;
    }
}
