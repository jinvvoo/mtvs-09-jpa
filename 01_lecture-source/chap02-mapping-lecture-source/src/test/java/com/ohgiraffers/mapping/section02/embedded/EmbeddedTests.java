package com.ohgiraffers.mapping.section02.embedded;

import com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid.LikeBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
public class EmbeddedTests {


    @Autowired
    private BookRegistService bookRegistService;
    private static Stream<Arguments> getBook() {
        Instant localDate;
        return Stream.of(
                Arguments.of(
                        "자바의정석",
                        "남궁성",
                        "도우출판",
                        LocalDate.now(),
                        30000,
                        0.1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getBook")
    void testCreateEmbededPriceOfBook(String bookTitle, String author, String publisher,
                                      LocalDate publishedDate, int regularPrice, double discountRate) {

        BookRegistDTO bookInfo =
                new BookRegistDTO(bookTitle, author, publisher, publishedDate, regularPrice, discountRate);

        Assertions.assertDoesNotThrow(
                () -> bookRegistService.registBook(bookInfo)
        );

    }

}
