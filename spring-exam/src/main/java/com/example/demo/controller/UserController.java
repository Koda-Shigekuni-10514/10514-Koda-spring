package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
// ---------------------------------------------------------------------------------------------------
    //一覧表示
    @GetMapping("/users")
    public String index(
        @RequestParam(name="keyword",defaultValue = "") String keyword,
        Model model
    ){
        List<User> userList = new ArrayList<>();

        //検索機能の追加
        if(keyword != null && keyword.length() > 0){
            userList = userRepository.findByKeyword("%" +keyword + "%");
        }else{
            userList = userRepository.findAll();
        }


        
        model.addAttribute("userList", userList);
        model.addAttribute("keyword", keyword);

        return "users";
    }
// ---------------------------------------------------------------------------------------------------
    //登録画面
    @GetMapping("/users/add")
    public String create(){
        return "addUser";
    }

    @PostMapping("/users/add")
    public String store(
        @RequestParam(name="name", defaultValue = "") String name,
        @RequestParam(name="email", defaultValue = "") String email,
        @RequestParam(name="password", defaultValue = "") String password,
        Model model
    ){
        User user = new User(name,email,password);
        userRepository.save(user);

        return "redirect:/users";
    }
// --------------------------------------------------------------------------------------------------
    //更新画面
    @GetMapping("/users/{id}/edit")
    public String edit(
        @PathVariable("id") Integer id,
        Model model
    ){
        Optional<User> dbData = userRepository.findById(id);

        if(dbData.isEmpty()){
            return "redirect:/users";
        }
        User user = dbData.get();
        model.addAttribute("user", user);

        return "editUser";
    }

    @PostMapping("/users/{id}/edit")
    public String update(
        @PathVariable("id") Integer id,
        @RequestParam(name="name", defaultValue = "") String name,
        @RequestParam(name="email", defaultValue = "") String email,
        @RequestParam(name="password", defaultValue = "") String password,
        Model model
    ){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isEmpty()){
            User user = userOptional.get();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            userRepository.save(user);
        }
        return "redirect:/users";
    }
// -----------------------------------------------------------------------------------------------------

    // 削除処理
    @PostMapping("/users/{id}/delete")
    public String delete(
        @PathVariable("id") Integer id
    ){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isEmpty()){
            userRepository.deleteById(id);
        }

        return "redirect:/users";
    }
}
