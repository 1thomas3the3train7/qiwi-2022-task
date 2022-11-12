package com.example.qiwi2022backend.Utils;


import com.example.qiwi2022backend.DTO.UserDTO;
import com.example.qiwi2022backend.Exception.NotValidRequestException;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {
    private final static Gson gson = new Gson();

    public UserDTO toUserDTO(final String req){
        try {
            return gson.fromJson(req, UserDTO.class);
        } catch (Exception exc){
            throw new NotValidRequestException("Not valid request");
        }
    }
    public UserDTO[] toUsersDTO(final String req){
        if(req == null){
            throw new NotValidRequestException("Not valid request");
        }
        return gson.fromJson(req,UserDTO[].class);
    }
}
