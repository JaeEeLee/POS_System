package com.pos.system.controller;

import com.pos.system.dto.Service_Account_DTO;
import com.pos.system.service.IService_Account_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Service_Account Controller
 * REST Design Pattern 적용
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



	@GetMapping("/signupform")
	public String signUpForm(){

		return "./view/account/register";
	}

	@PostMapping("register")
	public String register(Service_Account_DTO dto){
		Service_Account_DTO udto = service.signUp(dto);
		System.out.println(udto);

			return (udto !=null)? "redirect:/account": "redirect:/account/signupform";
	}


	//아이디 중복체크
	@PostMapping("idduplicate")
	@ResponseBody
	public String idDuplicate(String service_id) {
		//System.out.println("실행확인");

		char[] temp = service_id.toCharArray();
		//.AsArray 로 하면은 arraylist타입으로 변경됨
		for (int i=0; i<temp.length; i++) {
			//전체를 체크해줘야하기 떄문에 마지막에 괄호처리를 해줘야한다.
			if(!((temp[i]>='a' && temp[i] <='z') ||
					(temp[i]>='A' && temp[i] <='Z') ||
					(temp[i]>='0' && temp[i] <='9'))) {
				return "특수문자는 사용할 수 없습니다.";
			}
		}
		int n = service.idDuplicate(service_id);
	//	System.out.println(n);
		return (n==0)?"사용가능한 아이디":"중복된 아이디";
	}




	//로그인

	@GetMapping("")
	public String loginForm(){

		return "./view/account/loginform";
	}

	@PostMapping("logincheck")
	@ResponseBody
	public String logincheck(Service_Account_DTO dto){
		Service_Account_DTO udto = service.selectOne(dto);
		System.out.println(udto);

		return(udto==null)?"실패":"성공/"+udto.getService_type();
}

	@PostMapping("")
	public String login(Service_Account_DTO dto, HttpSession session){
		Service_Account_DTO udto = service.login(dto);
		session.setAttribute("id",udto);
		System.out.println(udto);
		return "redirect:/";
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
//		Service_Account_DTO hdto = service.selectOne(dto);
//		req.setAttribute("detail",dto);
//		System.out.println(dto);
//
//		return "./view/account/account-detail";
//
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
