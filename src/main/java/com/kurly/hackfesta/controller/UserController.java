package com.kurly.hackfesta.controller;

import com.kurly.hackfesta.domain.dto.ItemDto;
import com.kurly.hackfesta.domain.dto.ResponseDto;
import com.kurly.hackfesta.domain.dto.UserDto;
import com.kurly.hackfesta.domain.model.User;
import com.kurly.hackfesta.security.TokenProvider;
import com.kurly.hackfesta.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    /*
    * 로그인
    * */
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto,
                        RedirectAttributes redirectAttributes) {
        User user = userService.getByCredentials(userDto.getEmail(), userDto.getPassword(), passwordEncoder);

        if (user != null) {
            String token = tokenProvider.create(user);
            UserDto responseUserDTO = UserDto.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .token(token)
                    .build();

            String email = user.getEmail();
            redirectAttributes.addAttribute("id", email);

            if (user.getRole().equals(0)) {
                return "redirect:/item/{id}";
            }
            return "redirect:/orders/{id}";

        } else {
            return "Login failed";
        }
    }

    /*
    * 메인화면
    * */
    @GetMapping("/items/{id}")
    public ResponseEntity<?> customerMain(@PathVariable("id") String email) {
        ItemDto itemDto = userService.customerMain(email);
        return ResponseEntity.ok().body(itemDto);
    }

    /*
    * 주문 상세내용
    * */
    @GetMapping("/items/detail/{id}")
    public ResponseEntity<?> itemsDetail(@PathVariable("id") Long itemsId) {
        ItemDto itemDto = userService.itemsDetail(itemsId);
        return ResponseEntity.ok().body(itemDto);
    }
}
