package com.gameplay.app.Controllers;

import com.gameplay.app.DTOS.Requests.*;
import com.gameplay.app.Entities.Role;
import com.gameplay.app.Entities.User;
import com.gameplay.app.Repos.RoleRepo;
import com.gameplay.app.Repos.UserRepo;
import com.gameplay.app.Services.Games.DiceScore;
import com.gameplay.app.Services.Games.PokemonDamageCalculator;
import com.gameplay.app.Services.Games.RPS;
import com.gameplay.app.Services.Games.TowerOfHanoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(value = "/api")

public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepository, RoleRepo roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<String> playDiceScore(@RequestBody SignupDTO signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("/dicescore")
    public ResponseEntity<String> playDiceScore(@RequestBody DiceScoreRequestDTO diceScoreRequestDTO){
        return new ResponseEntity<>(((Integer) DiceScore.diceScore(diceScoreRequestDTO.getArr())).toString(), HttpStatus.OK);
    }

    @PostMapping("/pokemondamage")
    public ResponseEntity<String> playPokemonDamage(@RequestBody PokemonDamageCalcRequestDTO pokemonDamageCalcRequestDTO){
        return new ResponseEntity<>(PokemonDamageCalculator.calcDamage(
                pokemonDamageCalcRequestDTO.getType1(),
                pokemonDamageCalcRequestDTO.getType2(),
                pokemonDamageCalcRequestDTO.getAttack(),
                pokemonDamageCalcRequestDTO.getDefence()
        )+"", HttpStatus.OK);
    }

    @PostMapping("/rockpaperscissor")
    public ResponseEntity<String> playRPS(@RequestBody RPSRequestDTO rpsRequestDTO){
        return new ResponseEntity<>(RPS.rps(rpsRequestDTO.getChoice1(), rpsRequestDTO.getChoice2()), HttpStatus.OK);
    }

    @PostMapping("/towerofhanoi")
    public ResponseEntity<String> playTowerOfHanoi(@RequestBody TowerOfHanoiRequestDTO towerOfHanoiRequestDTO){
        return new ResponseEntity<>(Integer.toString(TowerOfHanoi.hanoi(towerOfHanoiRequestDTO.getNumberOfDisks(),
                towerOfHanoiRequestDTO.getSource(),
                towerOfHanoiRequestDTO.getDest(),
                towerOfHanoiRequestDTO.getAux())), HttpStatus.OK);
    }

    }
