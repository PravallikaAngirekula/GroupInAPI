package com.vns.groupin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vns.groupin.entity.Category;
import com.vns.groupin.entity.City;
import com.vns.groupin.entity.Country;
import com.vns.groupin.entity.OtpLogger;
import com.vns.groupin.entity.State;
import com.vns.groupin.entity.UserLogin;
import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.entity.Users;
import com.vns.groupin.model.Cities;
import com.vns.groupin.model.States;
import com.vns.groupin.model.UserData;
import com.vns.groupin.model.UserData2;
import com.vns.groupin.repository.UserGet;
import com.vns.groupin.response.CategoryResponse;
import com.vns.groupin.response.CityResponse;
import com.vns.groupin.response.CountryResponse;
import com.vns.groupin.response.LoginResponse;
import com.vns.groupin.response.OtpLoggerResponse;
import com.vns.groupin.response.StateResponse;
import com.vns.groupin.response.UserRegResponse;
import com.vns.groupin.response.UserUpdateResponse;
import com.vns.groupin.service.CategoryService;
import com.vns.groupin.service.CityService;
import com.vns.groupin.service.CountryService;
import com.vns.groupin.service.OtpLoggerService;
import com.vns.groupin.service.StateService;
import com.vns.groupin.service.UserLoginService;
import com.vns.groupin.service.UserRegService;
import com.vns.groupin.service.UsersService;
import com.vns.groupin.util.JwtUtil;

@RestController
//@RequestMapping("/")
public class UserRegController {
	@Autowired
	private UserRegService userRegService;

	@Autowired
	private OtpLoggerService otpLogService;

	@Autowired
	private CityService cityService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserLoginService loginService;

	@Autowired
	private UsersService userService;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("home")
	public String getHome() {
		return "welcome to groupIn";
	}

	@GetMapping("/all")
	public List<UserRegistration> getAllUsers() {
		return userRegService.getAllUsers();
	}

	@GetMapping("/getProfile/{userId}")
	public UserRegistration getUserDetails(@PathVariable Long userId) {
		return userRegService.getByUserId(userId);
	}

	// registration by mobile number
	@PostMapping("/register/1.0")
	public ResponseEntity<UserRegResponse> saveUserReg(@RequestBody UserRegistration user,
			HttpServletRequest httprequest) {
		UserRegResponse response = new UserRegResponse();
		try {
			UserRegistration userExists = userRegService.findByMobileNumber(user.getMobileNumber());
			String mobile = user.getMobileNumber();
			if (userExists != null) {
				Random random1 = new Random();
				String num = String.format("%4d", random1.nextInt(10000));
				System.out.println("random number:" + num);
				user.setMblNumOtp(num);
				// to replace the otp in logger table if user already exist in db
				OtpLogger loggerExists = otpLogService.findByPhoneNumber(user.getMobileNumber());
				if (loggerExists != null) {
					loggerExists.setMblNumOtp(num);
					otpLogService.saveMobileNumber(loggerExists);
				}
				response.setStatus(true);
				response.setOtp(num);
				response.setStatus(false);
				response.setMessage("User with MobileNumber Already Exists");
			} else {
				UserRegistration result = new UserRegistration();
				Random random = new Random();
				String number = String.format("%4d", random.nextInt(10000));
				System.out.println("random number:" + number);
				user.setMblNumOtp(number);
				result = userRegService.saveUser(user);
				String otpresp = result.getMblNumOtp();
				response.setOtp(otpresp);
				response.setStatus(true);
				response.setMessage("Otp Sent to Given Mobile Number");

//				 to save generated otp in logger table based on mobile number in user reg table
				if (result != null) {
					OtpLogger ol = new OtpLogger();
					ol.setMblNumOtp(user.getMblNumOtp());
					String mblNum = user.getMobileNumber();
					ol.setCountryCode(user.getCountryCode());
					ol.setMobileNumber(mblNum);

					OtpLogger ol1 = otpLogService.saveMobileNumber(ol);
					if (ol1 == null) {
						response.setOtp(otpresp);
						response.setStatus(true);
						response.setMessage("Otp Saving Failed");
					}
				}
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return new ResponseEntity<UserRegResponse>(response, HttpStatus.CREATED);
	}

	// validate otp
	@PostMapping("/mobileNumberValidation/1.0")
	public ResponseEntity<OtpLoggerResponse> validateRegUser(@RequestBody OtpLogger logger,
			HttpServletRequest httprequest) {
		OtpLoggerResponse response = new OtpLoggerResponse();
		try {

			OtpLogger result = otpLogService.findByMobileNumber(logger.getMblNumOtp(), logger.getMobileNumber());
			if (result != null) {
				String jwtToken = jwtUtil.generateToken(logger.getMobileNumber(), logger.getMblNumOtp());

//				String  strMobileNum = logger.getMobileNumber().substring(3, logger.getMobileNumber().length());
//				UserRegistration user = userRegService.findMobileByMobileNumber(strMobileNum);

				UserRegistration user = userRegService.findMobileByMobileNumber(logger.getMobileNumber());
				Long id1 = user.getUserId();
				Boolean profile = user.getProfileUpdate();
				System.out.println(profile);
				// to store mobile number and jwt token in users table --start

				String mbl = logger.getMobileNumber();
				String code = logger.getCountryCode();
				String totalNum = code + mbl;

				Users usersExists1 = userService.findByUserName(totalNum);

				if (usersExists1 != null) {
					saveValidateRegUser(true, totalNum, jwtToken);
					user.setProfileUpdate(profile);
					usersExists1.setPassword(jwtToken);
//					userService.saveMobileNumber(usersExists);
					response.setUserExists(true);
					String host1 = "@groupin.app";
					String total = totalNum + host1;
//					user.setUserId(id1);
					response.setJID(total);
					System.out.println("Mobile Number already present in users table");
				} else {
					Users usr = new Users();

					saveValidateRegUser(false, totalNum, jwtToken);
					usr.setUserName(totalNum);
					user.setProfileUpdate(profile);
					usr.setId(id1);
					usr.setPassword(jwtToken);
//					userService.saveMobileNumber(usr);
					response.setUserExists(false);
					// saving --- end
					String host1 = "@groupin.app";
					String total = totalNum + host1;
					response.setJID(total);
				}

				response.setToken(jwtToken);
				response.setStatus(true);
				response.setProfileUpdation(profile);
				response.setMessage("otp valid");

			} else {
				response.setStatus(false);
				response.setMessage("otp not valid");
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return new ResponseEntity<OtpLoggerResponse>(response, HttpStatus.OK);
	}

	private String saveValidateRegUser(boolean userExists, String mblNum, String token) {
		UserData userData = new UserData();
		UserData2 userData2 = new UserData2();
		String url = null;
		String statToken = "YWRtaW5AZ3JvdXBpbi5hcHA6YWRtaW5AMTIz";
		if (userExists == true) {
			userData2.setHost("groupin.app");
			userData2.setNewpass(token);
			userData2.setUser(mblNum);
			url = "http://13.233.215.155:5280/api/change_password";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			headers.add("X-Admin", "true");

			headers.add("Authorization", "Basic " + statToken);

			HttpEntity<UserData2> entity = new HttpEntity<UserData2>(userData2, headers);
			String str = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			System.out.println(str);
			return str;
		} else {
			userData.setHost("groupin.app");
			userData.setPassword(token);
			userData.setUser(mblNum);
			url = "http://13.233.215.155:5280/api/register";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			headers.add("X-Admin", "true");

			headers.add("Authorization", "Basic " + statToken);

			HttpEntity<UserData> entity = new HttpEntity<UserData>(userData, headers);
			String str = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			System.out.println(str);
			return str;

		}

	}

// update user profile
	@PostMapping("/InsertProfile/1.0")
	public ResponseEntity<UserUpdateResponse> updateUserDetails(@RequestBody UserRegistration user,
			Authentication authentication, HttpServletRequest httprequest) {
		UserUpdateResponse resp = new UserUpdateResponse();
		try {
			UserRegistration userExists = userRegService.findPhoneNumber(user.getMobileNumber());

			String mbl = user.getMobileNumber();
			String tokenMblNum = authentication.getName();

			Long uId = user.getUserId();

			if (user != null) {
				if (tokenMblNum.equals(mbl)) {
					if (user.getUserId() != null)
						userExists.setUserId(user.getUserId());
					if (user.getCategoryId() != null)
						userExists.setCategoryId(user.getCategoryId());
					if (user.getCityId() != null)
						userExists.setCityId(user.getCityId());
					if (user.getEmailId() != null)
						userExists.setEmailId(user.getEmailId());
					if (user.getProfileName() != null) {
						userExists.setProfileName(user.getProfileName());
					} else {
						userExists.setProfileName("profile Name");
					}
					if (user.getDescription() != null) {
						userExists.setDescription(user.getDescription());
					} else {
						userExists.setDescription("Description");
					}

//				if(user.isProfileCompleted() ==  false) {
//					userExists.setProfileCompleted(true);
//			
//				}else {
//					userExists.setProfileCompleted(user.isProfileCompleted());
//				}
//				

					if (user.getProfileUpdate() == false) {
						userExists.setProfileUpdate(true);

					} else {
						userExists.setProfileUpdate(user.getProfileUpdate());
					}

					if (user.getStateId() != null)
						userExists.setStateId(user.getStateId());
					if (user.getUserType() != null)
						userExists.setUserType(user.getUserType());
					if (user.getCountryCode() != null)
						userExists.setCountryCode(user.getCountryCode());

					UserRegistration result = userRegService.updateUserDetails(userExists);
					resp.setMessage("User Profile Updated Sucessfully");
					resp.setUserReg(result);
					resp.setStatus(true);

				}
			}

		} catch (Exception e) {
			resp.setMessage("User Profile Updataion Failed");
			resp.setStatus(false);
			resp.setMessage(e.getMessage());
		}
		return new ResponseEntity<UserUpdateResponse>(resp, HttpStatus.OK);
	}

//	==================================================================================================================================
	// categories

	@GetMapping("/allCategories")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/getCategory/{categoryId}")
	public Category getCategory(@PathVariable int categoryId) {
		return categoryService.getByCategoryId(categoryId);
	}

	@PostMapping("/categories")
	public ResponseEntity<CategoryResponse> saveCategories(@RequestBody Category category,
			HttpServletRequest httprequest) {
		CategoryResponse response = new CategoryResponse();

		try {
			Category categoryExists = categoryService.findByCategoryName(category.getCategoryName());

			if (categoryExists != null) {
//				return "Category Already Exists";
				response.setMessage("Category Already Exists");
				response.setStatus(false);

			} else {
				Category result = new Category();
				result = categoryService.saveCategory(category);
				response.setCategory(result);
				response.setStatus(true);
				response.setMessage("Category Added Sucessfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<CategoryResponse>(response, HttpStatus.CREATED);
	}

//============================================================================

	// get All countries
	@GetMapping("/getCountries")
	public List<Country> getAllCountries() {
		return countryService.getCountriesList();
	}

// Get state By Country

	@PostMapping("/getStates")
	public ResponseEntity<StateResponse> getStatesByCountry(@RequestBody Country country) {
		StateResponse resp = new StateResponse();
		List<States> states = new ArrayList<States>();
		List<State> statesList = stateService.findByCountryCode(country.getCountryCode());
		if (statesList != null && statesList.size() > 0) {
			statesList.forEach(e -> {
				States state = new States();
				state.setStateId(e.getStateId());
				state.setStateName(e.getStateName());
				states.add(state);
			});

			resp.setStates(states);
		}
		return new ResponseEntity<StateResponse>(resp, HttpStatus.OK);
	}

	// get Cities By State

	@PostMapping("/getCities")
	public ResponseEntity<CityResponse> getCitiesByState(@RequestBody State state) {
		CityResponse resp = new CityResponse();
		List<Cities> cities = new ArrayList<Cities>();
		List<City> cityList = cityService.findByStateId(state.getStateId());
		if (cityList != null && cityList.size() > 0) {
			cityList.forEach(e -> {
				Cities citi = new Cities();
				citi.setCityId(e.getCityId());
				citi.setCityName(e.getCityName());
				cities.add(citi);
			});

			resp.setCities(cities);
		}
		return new ResponseEntity<CityResponse>(resp, HttpStatus.OK);
	}

//===========================================================================================================================
//	String username = logger.getMobileNumber() + "@groupin.app";
//	usr.setUserName(username);

//	 HttpHeaders headers = new HttpHeaders();
//      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//      HttpEntity<Users> entity = new HttpEntity<Users>(usersExists,headers);
//      
//      String url ="http://13.233.215.155:5280/api/register";
//    
//      return restTemplate.postForEntity(
//         url, HttpMethod.POST, entity, response.class).getBody();
}
