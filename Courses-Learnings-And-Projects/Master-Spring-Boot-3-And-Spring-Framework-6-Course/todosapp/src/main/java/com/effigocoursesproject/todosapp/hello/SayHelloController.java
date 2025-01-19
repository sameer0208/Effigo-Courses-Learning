package com.effigocoursesproject.todosapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello()
    {
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>My New App Uploaded</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>My New App is here</h1>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp()
    {
        return "sayHello";
    }

}
