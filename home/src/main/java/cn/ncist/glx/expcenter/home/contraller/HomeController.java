package cn.ncist.glx.expcenter.home.contraller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {



    @RequestMapping("/")
    public String defa(Model model){
        model.addAttribute("path","/index.html");
        return "template";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("path","/index.html");
        return "template";
    }

    @RequestMapping("/center")
    public String center(Model model){
        model.addAttribute("path","/center/info.html");
        return "template";
    }
    @RequestMapping("/center/info")
    public String centerInfo(Model model){
        model.addAttribute("path","/center/info.html");
        return "template";
    }

    @RequestMapping("/center/photo")
    public String centerPhoto(Model model){
        model.addAttribute("path","/center/photo.html");
        return "template";
    }

    @RequestMapping("/center/contact")
    public String centerContact(Model model){
        model.addAttribute("path","/center/contact.html");
        return "template";
    }

    @RequestMapping("/teaching")
    public String teaching(Model model){
        model.addAttribute("path","/teaching/course.html");
        return "template";
    }

    @RequestMapping("/teaching/course")
    public String teachingCourse(Model model){
        model.addAttribute("path","/teaching/course.html");
        return "template";
    }

    @RequestMapping("/teaching/plan")
    public String teachingPlan(Model model){
        model.addAttribute("path","/teaching/plan.html");
        return "template";
    }

    @RequestMapping("/teaching/activity")
    public String teachingActivity(Model model){
        model.addAttribute("path","/teaching/activity.html");
        return "template";
    }

    @RequestMapping("/platform")
    public String platform(Model model){
        model.addAttribute("path","/platform/info.html");
        return "template";
    }

    @RequestMapping("/platform/info")
    public String platformInfo(Model model){
        model.addAttribute("path","/platform/info.html");
        return "template";
    }

    @RequestMapping("/platform/nav")
    public String platformNav(Model model){
        model.addAttribute("path","/platform/nav.html");
        return "template";
    }

    @RequestMapping("/construction")
    public String construction(Model model){
        model.addAttribute("path","/construction.html");
        return "template";
    }

    @RequestMapping("/notice")
    public String notice(Model model){
        model.addAttribute("path","/notice/center.html");
        return "template";
    }

    @RequestMapping("/notice/center")
    public String noticeCenter(Model model){
        model.addAttribute("path","/notice/center.html");
        return "template";
    }

    @RequestMapping("/notice/lost")
    public String noticeLost(Model model){
        model.addAttribute("path","/notice/lost.html");
        return "template";
    }

    @RequestMapping("/noticeInfo")
    public String noticeInfo (int id ,Model model){
        model.addAttribute("path","/noticeInfo.html?id="+id);
        return "template";
    }

    @RequestMapping("/system")
    public String system (Model model){
        model.addAttribute("path","/system.html");
        return "template";
    }

    @RequestMapping("/download")
    public String download (Model model){
        model.addAttribute("path","/download.html");
        return "template";
    }
}
