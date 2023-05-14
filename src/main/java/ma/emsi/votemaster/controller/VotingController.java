package ma.emsi.votemaster.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.emsi.votemaster.repository.UserRepository;
import ma.emsi.votemaster.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class VotingController {

    public final Logger logger = Logger.getLogger(String.valueOf(VotingController.class));

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public void redirectView(HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Location","/login");
        httpServletResponse.setStatus(302);
    }

    @GetMapping("/login")
    public String loginAcc(){

        logger.info("Returning login.html file");
        return "login.html";
    }
    @GetMapping("/register")
    public String registerAcc(){
        logger.info("Returning register.html file");
        return "register.html";
    }

    @GetMapping("/login/doLogin/{email}")
    public String doLogin(@PathVariable String email, Model model, HttpSession session) throws Exception {

        logger.info("Getting the citizen from the database");
        Optional<User> opt=userRepository.findByEmail(email);
        User citizen = opt.get();
        logger.info("Putting citizen into session");
        session.setAttribute("citizen",citizen);
        if(Objects.equals(citizen.getRole().toString(),"CANDIDATE")){
            logger.info("Candidate login");
            List<User> candidates = userRepository.findAll();
            List<User> onlyCand = new ArrayList<User>();
            for (int i = 0; i < candidates.size(); i++){
                if (Objects.equals(candidates.get(i).getRole().toString(), "CANDIDATE")){
                    onlyCand.add(candidates.get(i));
                }
            }
            model.addAttribute("candidates",onlyCand);
            return "/totalVotes.html";
        }else{
        if(!citizen.isHasvoted()){
            logger.info("Putting candidates into model");
            List<User> candidates = userRepository.findAll();
            List<User> onlyCand = new ArrayList<User>();
            for (int i = 0; i < candidates.size(); i++){
                if (Objects.equals(candidates.get(i).getRole().toString(), "CANDIDATE")){
                    onlyCand.add(candidates.get(i));
                }
            }
            model.addAttribute("users",onlyCand);
            return "/performVote.html";
        }else{
            return "/alreadyVoted.html";
        }
        }
    }

    @GetMapping("voteFor")
    public String voteFor(@RequestParam("id") Integer id, HttpSession session){

        Optional<User> opt=userRepository.findById(id);
        User citizen = opt.get();


        if (!citizen.isHasvoted()){
            citizen.setHasvoted(true);

            logger.info("voting for candidate " + citizen.getFirstname() + " " + citizen.getLastname());
            citizen.setNumberOfVotes(citizen.getNumberOfVotes()+1);

            userRepository.save(citizen);

            return "voted.html";
        }
        return "alreadyVoted.html";
    }
}
