package io.github.wotjd243.aladin.common;

import io.github.wotjd243.aladin.book.application.dto.RegisteredBookDto;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class HttpSessionUtil {
    public static final String REGISTERED_BOOK_SESSION_KEY = "registeredBook";

    public static List<RegisteredBookDto> getSessionRegisteredBook(HttpSession session) {
        if (hasSession(session)) {
            return (List<RegisteredBookDto>) session.getAttribute(REGISTERED_BOOK_SESSION_KEY);
        }
        return new ArrayList<>();
    }

    public static void setSessionRegisteredBook(HttpSession session, List<RegisteredBookDto> registeredBookDtos) {
        session.setAttribute(REGISTERED_BOOK_SESSION_KEY, registeredBookDtos);
    }

    public static boolean hasSession(HttpSession session) {
        return !ObjectUtils.isEmpty(session.getAttribute(REGISTERED_BOOK_SESSION_KEY));
    }
}
