/*
 * package com.iiht.estock.controller;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.iiht.estock.model.User; import com.iiht.estock.security.JwtUtils;
 * 
 * import io.swagger.annotations.Api; import
 * io.swagger.annotations.ApiOperation;
 * 
 * @RestController
 * 
 * @Api(value = "EstockMarketing Online store - Company") public class
 * UserController {
 * 
 * @Autowired JwtUtils jwtUtils;
 * 
 * @ApiOperation(value = "Login with a new User")
 * 
 * @PostMapping("/user") public User login(@Valid @RequestBody User user) {
 * 
 * String token = jwtUtils.getJWTToken(user.getUserName());
 * user.setToken(token); return user;
 * 
 * }
 * 
 * }
 */