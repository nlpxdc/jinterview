package io.cjf.jinterviewback.controller;

import io.cjf.jinterviewback.dao.InterviewMapper;
import io.cjf.jinterviewback.dto.InterviewCreateDTO;
import io.cjf.jinterviewback.dto.InterviewGetDTO;
import io.cjf.jinterviewback.dto.InterviewListDTO;
import io.cjf.jinterviewback.dto.InterviewUpdateDTO;
import io.cjf.jinterviewback.po.Interview;
import io.cjf.jinterviewback.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public Integer create(@RequestBody InterviewCreateDTO interviewCreateDTO) throws Exception {
        Interview interview = new Interview();
        interview.setCompanyName(interviewCreateDTO.getCompanyName());
        interview.setAddress(interviewCreateDTO.getAddress());
        interview.setTime(interviewCreateDTO.getTime());
        interview.setStatus(interviewCreateDTO.getStatus());
        interview.setSuggestion(interviewCreateDTO.getSuggestion());
        interview.setComment(interviewCreateDTO.getComment());

        String sessionId = httpSession.getId();
        User currentUser = (User) httpSession.getAttribute(sessionId);
        if (currentUser == null){
            throw new Exception("user doesn't login");
        }
        Integer userId = currentUser.getUserId();
        interview.setUserId(userId);

        interviewMapper.insert(interview);
        Integer interviewId = interview.getInterviewId();
        return interviewId;
    }

    @PostMapping("/update")
    public void update(@RequestBody InterviewUpdateDTO interviewUpdateDTO) throws Exception {
        Integer interviewId = interviewUpdateDTO.getInterviewId();
        Interview interview = interviewMapper.selectByPrimaryKey(interviewId);
        Integer userIdDB = interview.getUserId();

        String sessionId = httpSession.getId();
        User currentUser = (User) httpSession.getAttribute(sessionId);
        if (currentUser == null){
            throw new Exception("user doesn't login");
        }
        Integer currentUserUserId = currentUser.getUserId();
        if (currentUserUserId != userIdDB){
            throw new Exception("you can't change others interview");
        }

        interview.setStatus(interviewUpdateDTO.getStatus());
        interview.setSuggestion(interviewUpdateDTO.getSuggestion());
        interview.setComment(interviewUpdateDTO.getComment());

        interviewMapper.updateByPrimaryKey(interview);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer interviewId) throws Exception {
        String sessionId = httpSession.getId();
        User currentUser = (User) httpSession.getAttribute(sessionId);
        if (currentUser == null){
            throw new Exception("user doen't login");
        }
        Integer currentUserUserId = currentUser.getUserId();

        Interview interview = interviewMapper.selectByPrimaryKey(interviewId);
        Integer userIdDB = interview.getUserId();

        if (currentUserUserId != userIdDB){
            throw new Exception("you cannot delete other interview");
        }
        interviewMapper.deleteByPrimaryKey(interviewId);
    }
}
