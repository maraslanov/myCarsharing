package app.web.controller;

import app.persistance.entity.User;
import app.web.service.User.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Api("api list")//описание api для сваггера
@RequiredArgsConstructor
public class UserTestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "user test operation")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<String> getUserByLoginAndPswd(@RequestParam("login") String login, @RequestParam("password") String pswd) {
        ResponseEntity<String> response = null;
        //если не все необходмиые параметры заполнены - не стоит авторизовывать
        if (StringUtils.isNotBlank(login) && StringUtils.isNotBlank(pswd)) {
            User user = userService.findUserByUsernameAndPassword(login, pswd);
            if (user != null) {
                //возвращается успешная обработка запроса
                response = new ResponseEntity<>("поздравляю! вы авторизлованы", HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("иди работай негр", HttpStatus.UNAUTHORIZED);
            }
        } else {
            response = new ResponseEntity<>("нужно ввести логин и пароль", HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
}
