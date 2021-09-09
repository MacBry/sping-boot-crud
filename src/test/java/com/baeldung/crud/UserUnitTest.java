package com.baeldung.crud;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.mac.bry.crud.entities.User;

public class UserUnitTest {
    
    @Test
    public void whenCalledGetName_thenCorrect() {
    	 User user = new User ("a", "b", "c", "d");
        
        assertThat(user.getFirstName()).isEqualTo("Julie");
    }
    
    @Test
    public void whenCalledGetEmail_thenCorrect() {
    	 User user = new User ("a", "b", "c", "d");
        
        assertThat(user.getEmail()).isEqualTo("julie@domain.com");
    }
    
    @Test
    public void whenCalledSetName_thenCorrect() {
    	 User user = new User ("a", "b", "c", "d");
        
        user.setFirstName("John");
        
        assertThat(user.getFirstName()).isEqualTo("John");
    }
    
    @Test
    public void whenCalledSetEmail_thenCorrect() {
    	 User user = new User ("a", "b", "c", "d");
        
        user.setEmail("john@domain.com");
        
        assertThat(user.getEmail()).isEqualTo("john@domain.com");
    }
    
    @Test
    public void whenCalledtoString_thenCorrect() {
    	 User user = new User ("a", "b", "c", "d");
        assertThat(user.toString()).isEqualTo("User{id=0, name=Julie, email=julie@domain.com}");
    }
}
