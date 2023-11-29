package com.square.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    private String name;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm signUpForm = (SignUpForm)errors;
        if (accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email","invalid.email", new Object[]{signUpForm.getEmail()}, "Invalid Email" );
             
        }
        if(accountRepository.existsByNickName(signUpForm.getNickname())) {
            errors.rejectValue("nickname","invalid.nickname", new Object[]{signUpForm.getNickname()}, "Invalid nickName" );
        }
    }


}
