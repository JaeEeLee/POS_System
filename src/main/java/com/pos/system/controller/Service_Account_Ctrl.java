package com.pos.system.controller;

import com.pos.system.dto.Service_Account_DTO;
import com.pos.system.dto.Service_Store_DTO;
import com.pos.system.service.IService_Account_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Service_Account Controller
 * REST Design Pattern 적용
 *
 * @author DEADxFOOL
 */
@RequestMapping("/account")
@Controller
public class Service_Account_Ctrl {


    private final IService_Account_Service service;

    @Autowired
    public Service_Account_Ctrl(IService_Account_Service service) {

        this.service = service;
    }


    /**
     * 회원가입 화면 이동
     *
     * @param
     * @return "./view/account/register"
     */
    @GetMapping("/signupform")
    public String signUpForm() {
        return "/WEB-INF/view/account/register.jsp";
    }

    @GetMapping("/idfindform")
    public String idFindForm(){
        return "/WEB-INF/view/account/id-find.jsp";
    }

    @GetMapping("/pwfindform")
    public String pwFindForm(){
        return "/WEB-INF/view/account/pw-find.jsp";
    }


    @PostMapping("/register")
    public String register(Service_Account_DTO dto) {
        Service_Account_DTO udto = service.signUp(dto);
        System.out.println(udto);

        return (udto != null) ? "redirect:/account" : "redirect:/account/signupform";
    }


    /**
     * 아이디 중복 체크 처리
     *
     * @param \String\ service_id 회원이 입력한 아이디값.
     * @return "./view/account/register"
     */
    @PostMapping("/idduplicate")
    @ResponseBody
    public String idDuplicate(String service_id) {
        //System.out.println("실행확인");

        char[] temp = service_id.toCharArray();
        //.AsArray 로 하면은 arraylist타입으로 변경됨
        for (int i = 0; i < temp.length; i++) {
            //전체를 체크해줘야하기 떄문에 마지막에 괄호처리를 해줘야한다.
            if (!((temp[i] >= 'a' && temp[i] <= 'z') ||
                    (temp[i] >= 'A' && temp[i] <= 'Z') ||
                    (temp[i] >= '0' && temp[i] <= '9'))) {
                return "특수문자는 사용할 수 없습니다.";
            }
        }
        int n = service.idDuplicate(service_id);
        //	System.out.println(n);
        return (n == 0) ? "사용가능한 아이디" : "중복된 아이디";
    }

    @PostMapping("/emailduplicate")
    @ResponseBody
    public String emailDuplicate(String service_email){
            int n = service.emailDuplicate(service_email);
            System.out.println("이메일중복검사"+n);
        return (n == 0)? "가능":"중복";
    }

    @PostMapping("/idfind")
    @ResponseBody
    public String idFindCheck(String service_email){
            String email = service.idFind(service_email);
        return (email==null)? "실패" : "" + email;
    }


    /**
     * 로그인 화면 이동
     *
     * @param
     * @return "./view/account/loginform"
     */
    @GetMapping("")
    public String loginForm(HttpSession session) {
        Service_Account_DTO user = (Service_Account_DTO) session.getAttribute("user");
        if (user == null) {
            return "/WEB-INF/view/account/loginform.jsp";
        }
        return "redirect:./";
    }

    /**
     * 로그인 체크 처리
     * Ajax 방식
     *
     * @param \Service_Account_DTO\ dto . 사용자의 아이디와 비밀번호
     * @return dto.getService_type() 서비스의 타입을 세션에 담기위해 리턴값으로 dto의 Service_type을 같이 반환한다.
     */
    @PostMapping("logincheck")
    @ResponseBody
    public String logincheck(Service_Account_DTO dto) {
        Service_Account_DTO udto = service.selectOne(dto);
        System.out.println(udto);

        return (udto == null) ? "실패" : "성공/" + udto.getService_type();
    }

    /**
     * 로그인 처리
     *
     * @param \Service_Account_DTO\ dto , session 서비스 회원의 아이디와 계정타입, 세션
     * @return "redirect:/"
     */
    @PostMapping("")
    public String login(Service_Account_DTO dto, HttpSession session) {
        Service_Account_DTO user = service.login(dto);
        session.setAttribute("user", user);
        System.out.println(user);
        return "redirect:./";
    }

    /**
     * 로그아웃 처리
     *
     * @param \HttpSession 세션
     * @return "redirect:../"
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Service_Account_DTO user = (Service_Account_DTO) session.getAttribute("user");
	      Service_Store_DTO store = (Service_Store_DTO) session.getAttribute("store");
        if (user != null) {
            session.removeAttribute("user");
        }
        
        if(store != null){
	          session.removeAttribute("store");
        }

        return "redirect:../";
    }

    /**
     * 회원상세정보
     *
     * @param \HttpSession 세션
     * @return "redirect:../"
     */
    @GetMapping("/{service_id}")
    public String accountDetail(@PathVariable("service_id") String service_id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Service_Account_DTO user = (Service_Account_DTO) session.getAttribute("user");
        service_id = user.getService_id();
        Service_Account_DTO userDetail = service.accountDetail(service_id);
        System.out.println("유저 디테일 : " + userDetail);
        request.setAttribute("userDetail", userDetail);
        return "/WEB-INF/view/account/account-detail.jsp";
    }

    @PostMapping("/{service_id}/pwpage")
    public String pwPage(@PathVariable("service_id") String service_id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Service_Account_DTO user = (Service_Account_DTO) session.getAttribute("user");
        service_id = user.getService_id();
        return "/WEB-INF/view/account/password-check.jsp";
    }

    @PostMapping("/{service_id}/edit")
    public String modifyAccount(
            @PathVariable("service_id") String service_id,
            HttpSession session,
            HttpServletRequest request
    ) {
        Service_Account_DTO userDetail = (Service_Account_DTO) session.getAttribute("user");
        if (service_id.equalsIgnoreCase(userDetail.getService_id())) {
             userDetail= service.accountDetail(service_id);
            request.setAttribute("userDetail",userDetail);

            return "/WEB-INF/view/account/modify-account.jsp";
        } else {
            return "redirect:/errorpage";
        }
    }

    @PostMapping("/{service_id}/passwordcheck")
    @ResponseBody
    public String passwordCheck(@PathVariable("service_id") String service_id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Service_Account_DTO user = (Service_Account_DTO) session.getAttribute("user");
        service_id = user.getService_id();
        System.out.println(service_id);
        String service_pw = request.getParameter("service_pw");
        String check_id = service.pwCheck(service_id,service_pw);
        return service_id.equalsIgnoreCase(check_id) ? "성공" : "실패";
    }

    @PostMapping("/{service_id}/edit/email")
    public String editEmail(@PathVariable("service_id") String service_id, HttpServletRequest request, HttpServletResponse response){
        String service_email = request.getParameter("service_email");
        Service_Account_DTO user = service.accountDetail(service_id);
        user.setService_email(service_email);
        int n = service.modifyEmail(user);
    return (n>0)?"redirect:/account/{service_id}":"/errorpage";
    }

    @PostMapping("/{service_id}/edit/pw")
    public String editPw(@PathVariable("service_id") String service_id, HttpServletRequest request, HttpServletResponse response){
        String service_pw = request.getParameter("new_pw");
        Service_Account_DTO user = new Service_Account_DTO();
        user.setService_pw(service_pw);
        user.setService_id(service_id);
        int n = service.modifyPw(user);
        System.out.println(n);

        return (n>0)?"redirect:/account/{service_id}":"redirect:/errorpage";
    }









//	/**
//	 * 회원 정보 보기 목록 출력
//	 * @param service_id
//	 * @return dto
//	 *
//	 */
//	@GetMapping("{service_id}")
//	public String account_Detail(@PathVariable("service_id") String service_id, Service_Account_DTO dto, HttpServletRequest req, HttpSession session){
//		// 지금 테스트용이라서 setAttribute 씀.
//		Service_Account_DTO hdto2 = (Service_Account_DTO)session.getAttribute("id");
//		String service_id2 = hdto2.getService_id();
//		session.getAttribute("id")
//		dto.setService_id("winetree");
//		session.setAttribute("id",dto);
//
//
//		if(service_id.equalsIgnoreCase((String)session.getAttribute("id"))){
////		Service_Account_DTO hdto = service.selectOne(dto);
////		req.setAttribute("detail",dto);
////		System.out.println(dto);
////
////		return "./view/account/account-detail";
////
//		}else{
//			return "redirect:/errorpage";
//		}
//	}


//	//테스트용
//	@RequestMapping(value = "/account", method = {RequestMethod.POST, RequestMethod.GET})
//	public String home() {
//		System.out.println("-----------------------------성현이 테스트-----------------------------");
//		Service_Account_DTO dto = new Service_Account_DTO();
//		dto.setService_id("SungLoveDeadpool2");
//		dto.setService_email("Deadpool@naver.com");
//		dto.setService_pw("4321");
//		System.out.println("-----회원가입 리턴값 : " + service.signUp(dto));
//		System.out.println("-----아이디 중복 검사 리턴값 : " + service.idDuplicate("Alan"));
//		System.out.println("-----로그인 리턴값 : " + service.login(dto));
//		System.out.println("-----모든 회원 정보 검색 리턴값 : " +service.selectAll());
//		System.out.println("-----회원정보 수정 리턴값 : " +service.modify(dto));
//		System.out.println("-----회원탈퇴 리턴값 : " +service.resign(dto));
//		System.out.println("-----------------------------성현이 테스트-----------------------------");
//		return "index";
//	}

}
