package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.model.SessionUser;
import com.jongyeon.teslagazua.model.StockDto;
import com.jongyeon.teslagazua.service.YahooApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    YahooApiService yahooApiService;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        SessionUser user= (SessionUser) httpSession.getAttribute("user");
        if (user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "home";
    }

}
