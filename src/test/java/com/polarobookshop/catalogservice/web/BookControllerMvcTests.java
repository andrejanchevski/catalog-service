package com.polarobookshop.catalogservice.web;

import com.polarobookshop.catalogservice.domain.BookNotFoundException;
import com.polarobookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookControllerMvcTests.class)
public class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    JwtDecoder jwtDecoder;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "73737313940";
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);
        mockMvc
                .perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenDeleteBookWithEmployeeRoleThenShouldReturn204() throws Exception{
        var isbn = "7373731394";
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_employee")))
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
