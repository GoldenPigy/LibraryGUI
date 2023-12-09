package com.example.library.Service;


import com.example.library.Repository.MemberRepository;

import com.example.library.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository repository;



    public MemberService(@Autowired MemberRepository repository) {
        this.repository = repository;
    }

    public String memberSave(String id , String password, String name){
        if (findById(id)==null) {
            Member member=new Member();
            member.setId(id);
            member.setName(name);
            member.setPassword(password);
            repository.save(member);
            return "register success";
        } else {
            return "same id exists";
        }
    }

    public Member findById(String id) {
        if(repository.findById(id).isPresent())
        return repository.findById(id).get();
        else return null;
    }
    public String memberLogin(String id, String password){
        if(findById(id)==null){
            return "no such member";
        }
        else{
            Member member=  findById(id);
            if(member.getPassword().equals(password)){
                return  "log in success";
            }
            else return "wrong password";
        }
    }

}
