package com.anymind.coinbank.controller;

import com.anymind.coinbank.model.CoinInfoModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CoinController {

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid CoinInfoModel user, BindingResult bindingResult) {

        return new ModelAndView();
    }

    @GetMapping(value="/admin/home")
    public ModelAndView home() {

        return new ModelAndView();
    }
}
