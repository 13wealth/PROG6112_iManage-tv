package com.streaming_company;

public class inputValidation 
{
     /**
     * Regular expression validation for email assisted by ChatGPT
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) 
    {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(emailRegex);
    }
}






/**
* References:
* OpenAI. (2025, July 31). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
* 
*/ 
