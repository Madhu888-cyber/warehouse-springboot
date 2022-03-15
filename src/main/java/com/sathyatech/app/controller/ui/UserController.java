package com.sathyatech.app.controller.ui;

import java.security.Principal;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathyatech.app.config.EmailConfig;
import com.sathyatech.app.model.Role;
import com.sathyatech.app.model.Uom;
import com.sathyatech.app.model.User;
import com.sathyatech.app.repo.RoleRepository;
import com.sathyatech.app.service.IUserService;
import com.sathyatech.app.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private EmailConfig email;

	@Autowired
	private IUserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserValidator validator;

	@GetMapping(value={"/","/login"})
	public String showLogin(){
		return "Login";
	}
	
	@GetMapping("/activeChk")
	public String checkActive(Principal principal,ModelMap map){
		String page="";
		String name = principal.getName();
		User user = userService.findUserByEmail(name);

		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//System.out.println("========================================================================");
		//System.out.println(LocalDate.now().minusDays(30)+"******************");
		//System.out.println(LocalDate.now().minusDays(30).toString());
		//System.out.println(LocalDate.now().toString().compareTo(sdf.format(user.getLastLogin())));
		//long day30 = 30l * 24 * 60 * 60 * 1000;
		//if(LocalDate.now().toString().compareTo(sdf.format(user.getLastLogin())) > 0){
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime thirtyDaysAgo = now.plusDays(-30);
		if(user.getLastLogin().toInstant().isBefore(thirtyDaysAgo.toInstant())){
	
			 page = "AccountExpire";
		}
		else{
			if(user.getActive() == 0){
				page = "Inactive";
			}
			else{
				map.addAttribute("uom", new Uom());
				page = "redirect:uom/register";
			}
		}
		return page;
	}

	@GetMapping("/register")
	public String showRegister(ModelMap map){
		map.addAttribute("user", new User());
		return "Register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user,@RequestParam(value="rolesData",required=false)List<String> data,Errors errors,ModelMap map){
		//System.out.println(data+"***********************");
		validator.validate(user, errors);
		if(!errors.hasErrors()){

			User u=userService.findUserByEmail(user.getUserEmail());
			if(u!=null && u.getUserId()!=0){
				map.addAttribute("message", "User email already exist...");
			}
			else if(data!=null && data.size()>0){
				for(String roleName:data){
					Role role=roleRepository.findByRoleName(roleName);
					user.getRoles().add(role);
				}
				
				String message="Welcome to User :"+user.getUserName() 
						+" Your Login Id is: "+user.getUserEmail()
						+" Your Password is: "+user.getPassword()
						+" Your Roles are: "+user.getRoles();
				userService.saveUser(user);
				System.out.println(message);
				
				boolean flag=email.sendEmail(user.getUserEmail(), "Application User Registration", message, null);
				if(flag==true){
					System.out.println("SuccesSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
				}
				else{
					System.out.println("falseSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
				}
				map.addAttribute("user", new User());
				map.addAttribute("message", "User Registered...");
			}
		}
		return "Register";
	}

	@GetMapping("/AccessDenied")
	public String showDenied(){
		return "AccessDenied";
	}
}
