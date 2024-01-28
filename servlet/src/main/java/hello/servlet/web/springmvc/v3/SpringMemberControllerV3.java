package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */
@Controller
@RequestMapping("/springmvc/v3/members")
// RequestMapping은 URL만 매칭된다. HTTP Method를 구분하려면 아래와 같이 사용할 수 있다.
// @RequestMapping(value = "/new-form", method = RequestMethod.GET)
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    // MovelAndView가 아닌 String으로 return 한다. (유연한 설계가 되어있음)
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    // 스프링은 HTTP 요청 파라미터를 @RequestParam 으로 받을 수 있다.
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
