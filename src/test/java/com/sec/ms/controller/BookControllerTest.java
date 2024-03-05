/*
package com.sec.ms.controller;

import com.sec.ms.entity.Book;
import com.sec.ms.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService mockBookService;

    @Test
    void testFindAll() throws Exception {
        // Setup
        // Configure BookService.findAll(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final List<Book> books = List.of(book);
        when(mockBookService.findAll()).thenReturn(books);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindAll_BookServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBookService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure BookService.findById(...).
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setPrice(new BigDecimal("0.00"));
        book1.setPublishDate(LocalDate.of(2020, 1, 1));
        final Optional<Book> book = Optional.of(book1);
        when(mockBookService.findById(0L)).thenReturn(book);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindById_BookServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockBookService.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("", response.getContentAsString());
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        // Configure BookService.save(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setPrice(new BigDecimal("0.00"));
        book1.setPublishDate(LocalDate.of(2020, 1, 1));
        when(mockBookService.save(book1)).thenReturn(book);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/books")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        // Configure BookService.save(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setPrice(new BigDecimal("0.00"));
        book1.setPublishDate(LocalDate.of(2020, 1, 1));
        when(mockBookService.save(book1)).thenReturn(book);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/books")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/books/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockBookService).deleteById(0L);
    }

    @Test
    void testFindByTitle() throws Exception {
        // Setup
        // Configure BookService.findByTitle(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final List<Book> books = List.of(book);
        when(mockBookService.findByTitle("title")).thenReturn(books);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/title/{title}", "title")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindByTitle_BookServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBookService.findByTitle("title")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/title/{title}", "title")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    void testFindByPublishedDateAfter() throws Exception {
        // Setup
        // Configure BookService.findByPublishedDateAfter(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final List<Book> books = List.of(book);
        when(mockBookService.findByPublishedDateAfter(LocalDate.of(2020, 1, 1))).thenReturn(books);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/date-after/{date}", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindByPublishedDateAfter_BookServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBookService.findByPublishedDateAfter(LocalDate.of(2020, 1, 1))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/date-after/{date}", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }
}
*/
/*
package com.sec.ms.controller;

import com.sec.ms.entity.Book;
import com.sec.ms.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService mockBookService;

    @Test
    void testFindAll() throws Exception {
        // Setup
        // Configure BookService.findAll(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final List<Book> books = List.of(book);
        when(mockBookService.findAll()).thenReturn(books);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindAll_BookServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBookService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure BookService.findById(...).
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setPrice(new BigDecimal("0.00"));
        book1.setPublishDate(LocalDate.of(2020, 1, 1));
        final Optional<Book> book = Optional.of(book1);
        when(mockBookService.findById(0L)).thenReturn(book);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindById_BookServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockBookService.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("", response.getContentAsString());
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        // Configure BookService.save(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setPrice(new BigDecimal("0.00"));
        book1.setPublishDate(LocalDate.of(2020, 1, 1));
        when(mockBookService.save(book1)).thenReturn(book);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/books")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        // Configure BookService.save(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setPrice(new BigDecimal("0.00"));
        book1.setPublishDate(LocalDate.of(2020, 1, 1));
        when(mockBookService.save(book1)).thenReturn(book);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/books")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/books/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockBookService).deleteById(0L);
    }

    @Test
    void testFindByTitle() throws Exception {
        // Setup
        // Configure BookService.findByTitle(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final List<Book> books = List.of(book);
        when(mockBookService.findByTitle("title")).thenReturn(books);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/title/{title}", "title")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindByTitle_BookServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBookService.findByTitle("title")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/title/{title}", "title")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    void testFindByPublishedDateAfter() throws Exception {
        // Setup
        // Configure BookService.findByPublishedDateAfter(...).
        final Book book = new Book();
        book.setId(0L);
        book.setTitle("title");
        book.setPrice(new BigDecimal("0.00"));
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        final List<Book> books = List.of(book);
        when(mockBookService.findByPublishedDateAfter(LocalDate.of(2020, 1, 1))).thenReturn(books);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/date-after/{date}", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testFindByPublishedDateAfter_BookServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBookService.findByPublishedDateAfter(LocalDate.of(2020, 1, 1))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/books/find/date-after/{date}", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }
}
*/
