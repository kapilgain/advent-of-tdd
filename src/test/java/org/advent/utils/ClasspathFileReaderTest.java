package org.advent.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClasspathFileReaderTest {

    @Test
    void throwsRuntimeExceptionWhenReadingFileNotExists() {
        assertThrows(RuntimeException.class, () -> new ClasspathFileReader().readAllLines(
                "non-existent-file.txt"
        ));
    }

    @Test
    void readsLinesIntoListFromFile() {
        var underTest = new ClasspathFileReader().readAllLines(".gitignore");
        assertEquals(5, underTest.size());
        assertEquals("# https://adventofcode.com/about#faq_copying", underTest.getFirst());
    }

}
