package com.WebKTX.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class KTX_ErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KTX_ErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String errorPage = "error";
        String titlePage = "Error";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                titlePage = "Không tìm thấy trang";
                errorPage += "/404";
                LOGGER.error("Error 404");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                titlePage = "Lỗi máy chủ";
                errorPage += "/500";
                LOGGER.error("Error 500");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                titlePage = "Không được quyền truy cập";
                errorPage += "/403";
                LOGGER.error("Error 403");
            }
        }
        model.addAttribute("pageTitle", titlePage);
        return errorPage;
    }
}
