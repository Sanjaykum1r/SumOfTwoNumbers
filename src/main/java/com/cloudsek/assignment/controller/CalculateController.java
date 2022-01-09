package com.cloudsek.assignment.controller;

import com.cloudsek.assignment.model.NumberEquality;
import com.cloudsek.assignment.model.Response;
import com.cloudsek.assignment.repository.StoreVariableToDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;

@RestController
@Slf4j
public class CalculateController {

    private final StoreVariableToDatabase repo;
    private final Queue<NumberEquality> queue;

    public CalculateController(StoreVariableToDatabase repo){
        this.repo=repo;
        queue=new LinkedList<>();
    }

    @GetMapping("/")
    public String StringData(){
        return "Hi from test API";
    }

    @GetMapping("/calculate/{number1}/{number2}")
    public Integer cal(@PathVariable int number1, @PathVariable int number2)
    {
        NumberEquality number=new NumberEquality();
        number.setNumber1(number1);
        number.setNumber2(number2);
        int id= number.hashCode();
        number.setId(id);
        queue.add(number);
        repo.save(number);
        return id;
    }

    @Scheduled(fixedDelay = 10000)
    @Async
    public void calculateSum(){
        log.info("Running the queue ... ");
        if(!queue.isEmpty()) {
            NumberEquality number = queue.poll();
            number.setSum(number.getNumber1() + number.getNumber2());
            repo.save(number);
        }
    }

    @GetMapping("/get_answer/{identifier}")
    public Response result(@PathVariable int identifier){
        boolean existsById=repo.existsById(identifier);
        Response response=new Response();
        if(existsById){
            NumberEquality number=repo.getById(identifier);
            response.setStatusCode(200);
            if (number.getSum() != null) {
                response.setMessage(number.getSum().toString());
            } else {
                response.setMessage("Please Wait");
            }
        }else{
            log.error("Entry not found ...");
            response.setStatusCode(404);
            response.setMessage("Entry Doesn't exist");
        }
        return response;
    }
}
