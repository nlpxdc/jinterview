package io.cjf.jinterviewback.controller;

import io.cjf.jinterviewback.dao.InterviewMapper;
import io.cjf.jinterviewback.dto.InterviewGetDTO;
import io.cjf.jinterviewback.dto.InterviewListDTO;
import io.cjf.jinterviewback.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private InterviewMapper interviewMapper;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/getRecent")
    public List<InterviewListDTO> getRecent(@RequestParam(required = false) Long fromTimestamp,
                                            @RequestParam(required = false, defaultValue = "false") Boolean self){
        Date fromTime = null;
        if (fromTimestamp != null){
            fromTime = new Date(fromTimestamp);
        }
        Integer userId = null;
        if (self){
            String sessionId = httpSession.getId();
            User currentUser = (User) httpSession.getAttribute(sessionId);
            userId = currentUser.getUserId();
        }
        List<InterviewListDTO> interviewListDTOS = interviewMapper.selectRecent(fromTime, userId);
        return interviewListDTOS;
    }

    @GetMapping("/getById")
    public InterviewGetDTO getById(@RequestParam Integer interviewId){
        InterviewGetDTO interviewGetDTO = interviewMapper.selectById(interviewId);
        return interviewGetDTO;
    }
}
