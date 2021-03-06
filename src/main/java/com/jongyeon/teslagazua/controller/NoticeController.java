package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.aop.ControllerLogging;
import com.jongyeon.teslagazua.model.NoticeDto;
import com.jongyeon.teslagazua.model.SessionUser;
import com.jongyeon.teslagazua.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    HttpSession httpSession;

    @ControllerLogging
    @GetMapping("/notice")
    public String getList(Model model){
        SessionUser user= (SessionUser) httpSession.getAttribute("user");
        List<NoticeDto> notices = noticeService.getNoticeList();
        if (user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userEmail",user.getEmail());
        }
        if (notices != null){
            model.addAttribute("nList",notices);
        }
        return "notice";
    }


    @ControllerLogging
    @GetMapping("/notice/{id}")
    public String getDetail(@PathVariable("id") Long id,Model model){
        SessionUser user= (SessionUser) httpSession.getAttribute("user");
        NoticeDto dto=noticeService.getNotice(id);
        if (user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userEmail",user.getEmail());
        }
        if (dto != null){
            model.addAttribute("notice",dto);
        }
        return "noticeDetail";
    }

    @ResponseBody
    @PostMapping("/notice")
    public String addNotice(@RequestBody NoticeDto resource){
        return noticeService.addNotice(resource).toString();
    }


}
